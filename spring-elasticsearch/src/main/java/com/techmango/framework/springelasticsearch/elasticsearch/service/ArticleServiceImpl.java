package com.techmango.framework.springelasticsearch.elasticsearch.service;


import java.util.List;
import java.util.Optional;

import org.elasticsearch.common.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.techmango.framework.springelasticsearch.elasticsearch.model.Article;
import com.techmango.framework.springelasticsearch.elasticsearch.repository.ArticleRepository;


@Service
public class ArticleServiceImpl implements ArticleService{

	private ArticleRepository articleRepository;
	
	@Inject
	public ArticleServiceImpl(@NonNull ArticleRepository articleRepository) {
		this.articleRepository=articleRepository;
	}
	
	@Override
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Optional<Article> findOne(Long id) {
		return articleRepository.findById(id);
	}
	
	@Override
	public List<Article> findByTitle(String title) {
		return articleRepository.findBytitle(title);
	}

	@Override
	public Iterable<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Page<Article> findByAuthorName(String name, Pageable pageable) {
		return articleRepository.findByAuthorsName(name, pageable);
		
	}
	
	@Override
	public Page<Article> findByAuthorNameWithMatchQuery(String searchParam) {
		String fieldName = "authors.name";
		return articleRepository.searchByMatchQuery(fieldName, searchParam, Article.class);
	}
	
	@Override
	public Page<Article> findByNameWithMultiMatchQuery(String searchParam) {
		String[] fieldNames = {"authors.name","title"};
		return articleRepository.searchByMultiMatchQuery(fieldNames, searchParam, Article.class);
	}

	@Override
	public Page<Article> findByNameWithMatchPhraseQuery(String searchParam) {
		String fieldName = "title";
		return articleRepository.searchByMatchPhraseQuery(fieldName, searchParam, Article.class);
	}

	@Override
	public Page<Article> findByNameWithMatchPhrasePrefixQuery(String searchParam) {
		String fieldName = "authors.name";
		return articleRepository.searchByMatchPhrasePrefixQuery(fieldName, searchParam, Article.class);
	}

	@Override
	public Page<Article> findByNameWithMatchAllQuery(String searchParam) {
		return articleRepository.searchByMatchAllQuery(Article.class);
	}

	@Override
	public Page<Article> findByNameWithCommonTermsQuery(String searchParam) {
		String fieldName = "authors.name";
		return articleRepository.searchByCommonTermsQuery(fieldName, searchParam, Article.class);
	}
	
	@Override
	public Page<Article> findByNameWithTermLevelQueries(List<String> searchParams, String searchParam ) {
		String fieldName = "title";
		/*return articleRepository.searchByTermQuery(fieldName, searchParam, Article.class);
		return articleRepository.searchByTermsQuery(fieldName, searchParams, Article.class);
			String greaterThanValue = "5";
			String lesserThanValue = "20";
			return articleRepository.searchByRangeQuery(fieldName, searchParam, greaterThanValue, lesserThanValue, Article.class);*/
			String regexParam = "a.*";
			return articleRepository.searchByRegexpQuery(fieldName, regexParam, Article.class);
			/*case "fuzzyQuery":
			return articleRepository.searchByFuzzyQuery(fieldName, searchParam, Article.class);
		case "wildCardQuery":
			return articleRepository.searchByWildCardQuery(fieldName, searchParam, Article.class);
		}
		return null;*/
	}

}
