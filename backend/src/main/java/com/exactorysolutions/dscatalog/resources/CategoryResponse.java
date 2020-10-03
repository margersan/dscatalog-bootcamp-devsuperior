package com.exactorysolutions.dscatalog.resources;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exactorysolutions.dscatalog.entities.Category;


@RestController
@RequestMapping("/categories")
public class CategoryResponse {
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(1L, "Eletronics"));
		//return ResponseEntity.ok().body(list);
		return ResponseEntity.ok(list);
	}

}
