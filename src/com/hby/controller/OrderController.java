package com.hby.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hby.pojo.Article;
import com.hby.pojo.Order;
import com.hby.pojo.Shopcar;
import com.hby.pojo.User;
import com.hby.service.ArticleService;
import com.hby.service.OrderService;
import com.hby.service.ShopCartService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShopCartService shopCartService;
	
	@Autowired
	private ArticleService articleService;
	
	//结算明细
	@RequestMapping("/order.action")
	public String order(String orderInfo,Model model){
		String[] shopcarids = orderInfo.split(",");
		System.out.println("orderInfo的值为："+orderInfo);
		System.out.println("shopcarids的值为："+Arrays.toString(shopcarids));
		List<Shopcar> shopcarList = new ArrayList<Shopcar>();
		
		Double totalMoney = 0.00;
		
		for (String shopcarid : shopcarids) {
			Shopcar shopcar = shopCartService.findShopCartByid(Integer.parseInt(shopcarid));
			
			Article article = articleService.findArticleById(shopcar.getArticleid());
			totalMoney += shopcar.getBuynum()*article.getPrice()*article.getDiscount();
			
			shopcar.setArticle(article);
			
			shopcarList.add(shopcar);
			
		}
		
		model.addAttribute("shopCars",shopcarList);
		model.addAttribute("totalMoney",totalMoney);
		model.addAttribute("orderInfo",orderInfo);
		return "order";
	}
	
	//确认结算信息
	@RequestMapping("/saveOrder.action")
	public String savaOrder(String orderInfo,Double countMoney,HttpSession session){
		User user = (User)session.getAttribute("session_user");
		
		orderService.saveOrder(orderInfo, countMoney, user);
		
		return "redirect:/ShowShopCarItem.action";
	}
	
	//显示订单
	@RequestMapping("/showOrder.action")
	public String showOrder(HttpSession session,Model model){
		User user = (User) session.getAttribute("session_user");
		
		List<Order> list = orderService.getOrderByUserid(user.getId());
		
		model.addAttribute("orders",list);
		
		return "orderList";
	}
	
}
