package com.exactorysolutions.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exactorysolutions.dscatalog.entities.Category;
import com.exactorysolutions.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired //gerenciamento de injeção de dependencias automático do SpingBoot
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //garante que as transações não fiquem aberta e serão encerradas na camada view / readOnly não "locka" a tabela
	public List<Category> findAll() {
		return repository.findAll();
	}

}
