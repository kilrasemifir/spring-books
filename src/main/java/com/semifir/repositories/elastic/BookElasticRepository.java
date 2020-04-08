package com.semifir.repositories.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.semifir.models.elastic.BookElastic;

public interface BookElasticRepository extends ElasticsearchCrudRepository<BookElastic, String>, BookElasticRepositoryCustom{

}
