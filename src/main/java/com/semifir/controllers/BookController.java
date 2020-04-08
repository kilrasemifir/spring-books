package com.semifir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semifir.dto.BookSearchDTO;
import com.semifir.models.mongo.BookMongo;
import com.semifir.services.BookService;

@RequestMapping("books")
@RestController
@CrossOrigin
public class BookController {

	@Autowired
	private BookService service;
	
	@PostMapping("search")
	public Iterable<BookMongo> search(@RequestBody BookSearchDTO search){
		return this.service.search(search);
	}
	
	
}
