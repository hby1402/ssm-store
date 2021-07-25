package com.hby.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hby.mapper.ArticletypeMapper;
import com.hby.pojo.Articletype;
import com.hby.pojo.SecondArticleType;
import com.hby.service.ArticleTypeService;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

	@Autowired
	private ArticletypeMapper articletypeMapper;
	
	//第一级标题
	@Override
	public List<Articletype> getFirstArticleType() {
//		//创建查询条件
//		ArticletypeExample example = new ArticletypeExample();
		
		List<Articletype> firstCodeLength = articletypeMapper.selectByCodeLength(null);
		return firstCodeLength;
	}

	//第二级标题
	@Override
	public List<SecondArticleType> getSecondArticleType(String code) {
		List<SecondArticleType> secondCodeLength = articletypeMapper.selectBySecondCodeLength(code);
		return secondCodeLength;
	}

}
