package com.hby.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hby.pojo.Article;
import com.hby.pojo.Shopcar;
import com.hby.pojo.User;
import com.hby.service.ArticleService;
import com.hby.service.ShopCartService;

@Controller
public class CartController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ShopCartService shopCartService;

	// 购物车页面
	@RequestMapping("/ShowShopCarItem.action")
	public String showCartItem(HttpSession session, Model model) {
		User user = (User) session.getAttribute("session_user");
		
		List<Shopcar> list = shopCartService.findShopCartById(user.getId());

		int num = 0;
		Double totalMoney = 0.00;

		for (Shopcar shopcar : list) {
			num += shopcar.getBuynum();
			totalMoney += shopcar.getBuynum() * shopcar.getArticle().getPrice() * shopcar.getArticle().getDiscount();
		}

		model.addAttribute("num", num);
		model.addAttribute("totalMoney", totalMoney);
		model.addAttribute("shopCars", list);
		return "shopCar";
	}
	
	//修改购物车
	@RequestMapping("updateCar.action")
	public String updateCart(HttpSession session, int id, int buynum, int articleid) {
		
		User user = (User) session.getAttribute("session_user");
		
		Shopcar shopcar = new Shopcar();
		shopcar.setId(id);
		shopcar.setBuynum(buynum);
		shopcar.setUserid(user.getId());
		shopcar.setArticleid(articleid);
		
		shopCartService.updateCart(shopcar);
		
		return "redirect:/ShowShopCarItem.action";
	}
	
	//删除购物车
	@RequestMapping("deleteCar.action")
	public String deleteCartItem(int id) {
		
		shopCartService.deleteCartItem(id);
		
		return "redirect:/ShowShopCarItem.action";
	}
	
	//商品详情页面
	@RequestMapping("/item")
	public String itemInfo(int id,Model model) {
		Article article = articleService.findArticleById(id);
		model.addAttribute("article",article);
		return "item";
	}

	//购买
	@RequestMapping("/buy.action")
	public String buy(int id,int buyNum,HttpSession session) {
		User user = (User) session.getAttribute("session_user");
		List<Shopcar> list = shopCartService.findShopCartById(user.getId());
		Shopcar shopcar = new Shopcar();
		shopcar.setBuynum(buyNum);
		shopcar.setArticleid(id);
		shopcar.setUserid(user.getId());
		
		for (Shopcar shopcar1 : list) {
			if(shopcar1.getArticleid()==shopcar.getArticleid()){
				shopcar.setId(shopcar1.getId());
				System.out.println("shopcar.getBuynum:"+shopcar.getBuynum()+","+shopcar1.getBuynum());
				shopcar.setBuynum(shopcar.getBuynum()+shopcar1.getBuynum());
				shopCartService.updateCart(shopcar);
				return "redirect:/ShowShopCarItem.action";
			}
		}
		
		shopCartService.addCartItem(shopcar);
		
		return "redirect:/ShowShopCarItem.action";
	}
	
}
