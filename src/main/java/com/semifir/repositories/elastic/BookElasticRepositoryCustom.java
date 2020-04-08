package com.semifir.repositories.elastic;

import com.semifir.dto.BookSearchDTO;
import com.semifir.models.elastic.BookElastic;

public interface BookElasticRepositoryCustom {

	public Iterable<BookElastic> search(BookSearchDTO search);
}
