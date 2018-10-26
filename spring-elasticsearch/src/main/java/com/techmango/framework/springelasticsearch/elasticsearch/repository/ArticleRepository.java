package com.techmango.framework.springelasticsearch.elasticsearch.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.techmango.framework.springelasticsearch.elasticsearch.model.Article;


@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long>, CustomElasticSearchRepository{

	Page<Article> findByAuthorsName(String name, Pageable pageable);
	
	Optional<Article> findById(Long id);
	
	List<Article> findBytitle(String title);
	
 }
