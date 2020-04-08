package com.semifir.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.dto.BookSearchDTO;
import com.semifir.models.elastic.BookElastic;
import com.semifir.models.mongo.BookMongo;
import com.semifir.repositories.elastic.BookElasticRepository;
import com.semifir.repositories.mongo.BookMongoRepository;

@Service
public class BookService {

	@Autowired
	private BookElasticRepository elasticRepo;
	@Autowired
	private BookMongoRepository mongoRepository;
	
	public Iterable<BookMongo> search(BookSearchDTO search){
		List<String> ids = new ArrayList<>();
		for (BookElastic be: this.elasticRepo.search(search)) {
			ids.add(be.getId());
		}
		return this.mongoRepository.findAllById(ids);
	}
	
	public BookMongo save(BookMongo entity) {
		entity = this.mongoRepository.save(entity);
		this.elasticRepo.save(
				new BookElastic(
						entity.getId(),
						entity.getTitle(),
						entity.getAuthor(),
						entity.getShortDescription(),
						entity.getLongDescription(),
						entity.getPages(),
						entity.getCategories()));
		return entity;
	}
}
