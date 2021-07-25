package com.hby.service;

import java.util.List;

import com.hby.pojo.Articletype;
import com.hby.pojo.SecondArticleType;

public interface ArticleTypeService {
	public List<Articletype> getFirstArticleType();
	
	public List<SecondArticleType> getSecondArticleType(String code);
}
