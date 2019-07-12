package com.boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
	
}
