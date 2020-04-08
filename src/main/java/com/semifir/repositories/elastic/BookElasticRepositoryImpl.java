package com.semifir.repositories.elastic;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.semifir.dto.BookSearchDTO;
import com.semifir.models.elastic.BookElastic;

@Repository
public class BookElasticRepositoryImpl implements BookElasticRepositoryCustom {
	@Autowired
	private ElasticsearchRestTemplate template;
	
	public Iterable<BookElastic> search(BookSearchDTO search){
		
		QueryBuilder qtitle = matchQuery("title", search.getTitle()).boost(2f);
		QueryBuilder qAuthor = matchQuery("author", search.getTitle());
		
		
		SearchQuery query = new NativeSearchQueryBuilder()
				.withQuery(boolQuery()
						.must(matchAllQuery())
						.should(qtitle)
						.should(qAuthor))
				.build();
		return this.template.queryForList(query, BookElastic.class);
	}
	
}
