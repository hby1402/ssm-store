package com.hby.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hby.mapper.ArticleMapper;
import com.hby.pojo.Article;
import com.hby.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	//获取所有商品
	@Override
	public List<Article> getAllArticle() {
		return articleMapper.selectByExample(null);
	}

	//根据id查询商品
	@Override
	public Article findArticleById(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	//根据关键词查询商品名（模糊查询）
	@Override
	public List<Article> findArticleByKeyword(String keyword) {
		List<Article> list = articleMapper.selectByKeyword(keyword);
		return list;
	}

}
