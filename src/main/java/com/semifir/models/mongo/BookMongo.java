package com.semifir.models.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class BookMongo {
	@Id
	private String id;
	private String title;
	private List<String> author;
	private String shortDescription;
	private String longDescription;
	private int pages;
	private List<String> categories = new ArrayList<>();
}
