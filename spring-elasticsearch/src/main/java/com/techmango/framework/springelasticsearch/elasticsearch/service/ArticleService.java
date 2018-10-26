package com.techmango.framework.springelasticsearch.elasticsearch.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techmango.framework.springelasticsearch.elasticsearch.model.Article;


public interface ArticleService {
    
	Article save(Article article);

    Optional<Article> findOne(Long id);

    Iterable<Article> findAll();

    Page<Article> findByAuthorName(String name, Pageable pageable);

    List<Article> findByTitle(String title);

	/*Queries*/
	Page<Article> findByAuthorNameWithMatchQuery(String searchParam);

	Page<Article> findByNameWithMultiMatchQuery(String searchParam);

	Page<Article> findByNameWithMatchPhraseQuery(String searchParam);

	Page<Article> findByNameWithMatchPhrasePrefixQuery(String searchParam);

	Page<Article> findByNameWithMatchAllQuery(String searchParam);

	Page<Article> findByNameWithCommonTermsQuery(String searchParam);

	Page<Article> findByNameWithTermLevelQueries(List<String> searchParams, String searchParam2);


}
