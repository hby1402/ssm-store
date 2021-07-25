package com.hby.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hby.mapper.ArticleMapper;
import com.hby.mapper.ShopcarMapper;
import com.hby.pojo.Article;
import com.hby.pojo.Shopcar;
import com.hby.pojo.ShopcarExample;
import com.hby.pojo.ShopcarExample.Criteria;
import com.hby.service.ShopCartService;

@Service
public class ShopCartServiceImpl implements ShopCartService {

	@Autowired
	private ShopcarMapper shopcarMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public List<Shopcar> findShopCartById(int userId) {
		ShopcarExample shopcarExample = new ShopcarExample();
		Criteria criteria = shopcarExample.createCriteria();
		criteria.andUseridEqualTo(userId);
		List<Shopcar> list = shopcarMapper.selectByExample(shopcarExample);
		
		for (Shopcar shopcar : list) {
			Article article = articleMapper.selectByPrimaryKey(shopcar.getArticleid());
			shopcar.setArticle(article);
		}
		
		return list;
	}

	@Override
	public void updateCart(Shopcar shopcar) {
		
		shopcarMapper.updateByPrimaryKey(shopcar);
		
	}

	@Override
	public void deleteCartItem(int id) {

		shopcarMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void addCartItem(Shopcar shopcar) {
		
		shopcarMapper.insert(shopcar);
		
	}

	@Override
	public Shopcar findShopCartByid(int id) {
		Shopcar shopcar = shopcarMapper.selectByPrimaryKey(id);
		
		return shopcar;
	}

}
