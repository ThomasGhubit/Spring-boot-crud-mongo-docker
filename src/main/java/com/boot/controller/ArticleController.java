package com.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Article;
import com.boot.repository.ArticleRepository;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	//@GetMapping("/articles")
	@RequestMapping(value = "articles", method = RequestMethod.GET)
    public List<Article> getAllArticles() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return articleRepository.findAll(sortByCreatedAtDesc);
    }

    //@PostMapping("/articles")
	@RequestMapping(value = "articles", method = RequestMethod.POST)
    public Article createArticle(@Valid @RequestBody Article article) {
        // article.setCompleted(false);
        return articleRepository.save(article);
    }

    //@GetMapping(value="/articles/{id}")
	@RequestMapping(value = "articles/{id}", method = RequestMethod.GET)
    public ResponseEntity<Article> getTodoById(@PathVariable("id") String id) {
        return articleRepository.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    //@PutMapping(value="/articles/{id}")
	@RequestMapping(value = "articles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Article> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody Article article) {
        return articleRepository.findById(id)
                .map(articleData -> {
                	articleData.setName(article.getName());
                	articleData.setAuthor(article.getAuthor());
                	Article updatedArticle = articleRepository.save(articleData);
                    return ResponseEntity.ok().body(updatedArticle);
                }).orElse(ResponseEntity.notFound().build());
    }

    //@DeleteMapping(value="/articles/{id}")
	@RequestMapping(value = "articles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteArticle(@PathVariable("id") String id) {
        return articleRepository.findById(id)
                .map(article -> {
                	articleRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
