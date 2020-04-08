package com.semifir.models.elastic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Score;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "livres", createIndex = false, type = "_doc")
@NoArgsConstructor
public class BookElastic {

	@Id
	private String id;
	@Score
	private float _score;
	private String title;
	private List<String> author;
	private String shortDescription;
	private String longDescription;
	private int pages;
	private List<String> categories = new ArrayList<>();
	
	public BookElastic(String id, String title, List<String> author, String shortDescription,
			String longDescription, int pages, List<String> categories) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.pages = pages;
		this.categories = categories;
	}
	
	
	
}
