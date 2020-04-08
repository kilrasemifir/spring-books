package com.semifir.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.mongo.BookMongo;

public interface BookMongoRepository extends MongoRepository<BookMongo, String>{

}
