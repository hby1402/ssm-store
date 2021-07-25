package com.hby.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hby.mapper.ArticleMapper;
import com.hby.mapper.OrderMapper;
import com.hby.mapper.OrderitemMapper;
import com.hby.mapper.ShopcarMapper;
import com.hby.pojo.Article;
import com.hby.pojo.Order;
import com.hby.pojo.OrderExample;
import com.hby.pojo.OrderExample.Criteria;
import com.hby.pojo.Orderitem;
import com.hby.pojo.OrderitemExample;
import com.hby.pojo.Shopcar;
import com.hby.pojo.User;
import com.hby.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderitemMapper orderitemMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ShopcarMapper shopcarMapper;
	
	@Override
	public List<Order> getOrderByUserid(int userId) {
		OrderExample oe = new OrderExample();
		Criteria criteria = oe.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<Order> orderList = orderMapper.selectByExample(oe);
		
		for (Order order : orderList) {
			OrderitemExample orderitemExample = new OrderitemExample();
			com.hby.pojo.OrderitemExample.Criteria criteria2 = orderitemExample.createCriteria();
			criteria2.andOrderIdEqualTo(order.getId());
			List<Orderitem> orderitemList = orderitemMapper.selectByExample(orderitemExample);
			
			for (Orderitem orderitem : orderitemList) {
				Article article = articleMapper.selectByPrimaryKey(orderitem.getArticleId());
				orderitem.setArticle(article);
			}
			
			order.setOrderItemList(orderitemList);
		}
		
		
		return orderList;
	}

	@Override
	public void saveOrder(String orderInfo, Double countMoney, User user) {
		Order order = new Order();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		order.setOrderCode(sdf.format(date));
		order.setAmount(countMoney);
		order.setCreateDate(date);
		order.setUserId(user.getId());
		orderMapper.insert(order);
		
		String[] shopcarids = orderInfo.split(",");
		for (String shopcarid : shopcarids) {
			Shopcar shopcar = shopcarMapper.selectByPrimaryKey(Integer.parseInt(shopcarid));
			
			Orderitem orderitem = new Orderitem();
			orderitem.setOrderId(order.getId());
			orderitem.setArticleId(shopcar.getArticleid());
			orderitem.setOrderNum(shopcar.getBuynum());
			
			orderitemMapper.insert(orderitem);
			
			shopcarMapper.deleteByPrimaryKey(Integer.parseInt(shopcarid));
		}
	}

}
