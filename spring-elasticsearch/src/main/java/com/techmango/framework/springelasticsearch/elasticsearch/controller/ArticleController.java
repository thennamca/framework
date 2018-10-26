package com.techmango.framework.springelasticsearch.elasticsearch.controller;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.framework.springelasticsearch.elasticsearch.model.Article;
import com.techmango.framework.springelasticsearch.elasticsearch.service.ArticleService;


@RestController
@RequestMapping("/es")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@PostMapping
	@RequestMapping(value="/save")
	public ResponseEntity<Article> saveArticles(@RequestBody Article article){
		if(Objects.nonNull(article)) {
			articleService.save(article);
		}
		return new ResponseEntity<>(article, HttpStatus.CREATED);
	}
	
	@GetMapping
	@RequestMapping(value="/article")
	public ResponseEntity<List<Article>> getAnArticle(@RequestParam("title") String id){
		return new ResponseEntity<>(articleService.findByTitle(id), HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping(value="/find")
	public ResponseEntity<Page<Article>> findByAuthorsName(String name, Pageable pageable){
		return new ResponseEntity<>(articleService.findByAuthorName(name, pageable), HttpStatus.CREATED);
	}
	
	@GetMapping
	@RequestMapping(value="/matchQuery")
	public ResponseEntity<Page<Article>> finByMatchQuery(@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByAuthorNameWithMatchQuery(searchParam), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value="/multiMatchQuery")
	public ResponseEntity<Page<Article>> multiMatchQuery(@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByNameWithMultiMatchQuery(searchParam), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value="/matchPhraseQuery")
	public ResponseEntity<Page<Article>> matchPhraseQuery(@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByNameWithMatchPhraseQuery(searchParam), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value="/matchPhrasePrefixQuery")
	public ResponseEntity<Page<Article>> matchPhrasePrefixQuery(@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByNameWithMatchPhrasePrefixQuery(searchParam), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value="/matchAllQuery")
	public ResponseEntity<Page<Article>> matchAllQuery(@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByNameWithMatchAllQuery(searchParam), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value="/commonTermsQuery")
	public ResponseEntity<Page<Article>> commonTermsQuery(@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByNameWithCommonTermsQuery(searchParam), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value="/termLevelQuery")
	public ResponseEntity<Page<Article>> termLevelQueries(@RequestParam("searchParams") List<String> searchParams,@RequestParam("searchParam") String searchParam){
		return new ResponseEntity<>(articleService.findByNameWithTermLevelQueries(searchParams, searchParam), HttpStatus.OK);
	}

}
