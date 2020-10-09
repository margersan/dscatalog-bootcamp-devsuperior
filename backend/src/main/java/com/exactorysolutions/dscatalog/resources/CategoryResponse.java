package com.exactorysolutions.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exactorysolutions.dscatalog.dto.CategoryDTO;
import com.exactorysolutions.dscatalog.services.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryResponse {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		/* Teste da Categories sem o banco de dados implementado
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(1L, "Eletronics"));
		*/	
		//return ResponseEntity.ok().body(list); -- As duas formas ok().body(list) e ok(list) funcionam
		
		//No curso o Nélio criou uma lista List<Category> list = service.findAll()
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

}
