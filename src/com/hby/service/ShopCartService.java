package com.hby.service;

import java.util.List;

import com.hby.pojo.Shopcar;

public interface ShopCartService {
	public List<Shopcar> findShopCartById(int userId);
	
	public void updateCart(Shopcar shopcar);
	
	public void deleteCartItem(int id);
	
	public void addCartItem(Shopcar shopcar);
	
	public Shopcar findShopCartByid(int id);
}
