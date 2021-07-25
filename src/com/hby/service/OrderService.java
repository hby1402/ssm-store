package com.hby.service;

import java.util.List;

import com.hby.pojo.Order;
import com.hby.pojo.User;

public interface OrderService {

	public List<Order> getOrderByUserid(int userId);
	
	public void saveOrder(String orderInfo,Double countMoney,User user);
}
