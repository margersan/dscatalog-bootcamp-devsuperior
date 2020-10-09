package com.exactorysolutions.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exactorysolutions.dscatalog.dto.CategoryDTO;
import com.exactorysolutions.dscatalog.entities.Category;
import com.exactorysolutions.dscatalog.repositories.CategoryRepository;
import com.exactorysolutions.dscatalog.services.exceptions.DatabaseException;
import com.exactorysolutions.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired //gerenciamento de injeção de dependencias automático do SpingBoot
	private CategoryRepository repository;
	
	/* Antes do DTO, serviços estava entregando a controladora REST os dados direto da Entidade Categoria,
	 * Mas pela definição da aplicação em camadas isto não é o correto a se fazer, então estamos trocando o retorno de 
	 * Categoria para CategoriaDTO
	 * O problema que o método repository.findAll() retorna o objeto Categoria, então faz-se necessário instanciar CategoryDTO a cada membro da 
	 * lista Category retornado pela findAll.
	 * O professor ensinou de duas formas - uma de uma forma mais didática e a segunda com uma função lambda usando Map;
	 * Segue o método antes das alterações:
	 * @Transactional(readOnly = true)
	 * public List<Category> findAll() {
	 *    return repository.findAll();
	 * }
	 */
	
	@Transactional(readOnly = true) //garante que as transações não fiquem aberta e serão encerradas na camada view / readOnly não "locka" a tabela
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		//Expressão lambda (bem legal)
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		/* Função explicando de forma didática que foi substituída por uma expressão lambda
		 * List<CategoryDTO> listDTO = new ArrayList<>();
		 * for (Category cat : list) {
		 *     listDTO.add(new CategoryDTO(cat));
		 * }
		 * return listDTO;
		 */
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id); //repository.findById retorna um Optional para tratar os Nulos
		//Category entity = obj.get(); //para acessar o dado usa-se o método get
		//obj.orElseThrow - permite, caso não tenha a categoria, enviar uma exception
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) { //ID não encontrado na base de dados
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) { //quebra de integridade referencial
			throw new DatabaseException("Integrity Violation");
		}
	}
}
