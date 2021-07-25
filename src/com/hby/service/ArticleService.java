package com.hby.service;

import java.util.List;

import com.hby.pojo.Article;

public interface ArticleService {
	public List<Article> getAllArticle();
	
	public Article findArticleById(int id);
	
	public List<Article> findArticleByKeyword(String keyword);
}
