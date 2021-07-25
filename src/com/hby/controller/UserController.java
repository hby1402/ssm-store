package com.hby.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hby.pojo.User;
import com.hby.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
//	private HttpSession session;
	
	//登录界面
	@RequestMapping("/login")
	public String userLoginPage() {
		return "login";
	}
	
	//登录验证
	@RequestMapping("/loginform")
	public String userLogin(User user,HttpSession session) {
		User loginUser = userService.login(user);
		
		if(loginUser!=null) {
			session.setAttribute("session_user", loginUser);
			return "redirect:/index";
		}
		session.setAttribute("message", "用户名或账号错误！");
		return "login";
	}
	
	//退出登录
	@RequestMapping("/loginout")
	public String userLoginout(User user,HttpSession session) {
		session.removeAttribute("session_user");
		return "redirect:/index";
	}
	
	//注册模块
	@RequestMapping("register")
	public String userRegisterPage() {
		return "register";
	}
	@RequestMapping("registerform")
	public String userRegister(User user,HttpSession session) {
		if(user!=null) {
			userService.createUser(user);
			
			return "redirect:/login";
		}
		session.setAttribute("message", "注册失败");
		return "register";
	}
	
}
