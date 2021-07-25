package com.hby.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hby.pojo.Article;
import com.hby.pojo.Articletype;
import com.hby.pojo.SecondArticleType;
import com.hby.service.ArticleService;
import com.hby.service.ArticleTypeService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleTypeService articleTypeService;
	private static final int pageSize = 8;

	// 跳转到主页
	@RequestMapping("/")
	public String showAllArticle() {
		return "redirect:/index";
	}
	
	//分页操作 /@RequestParam(value = "code") String code
	@RequestMapping("/index")
	public String showArticleByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			Model model,String keyword,HttpSession session) {

		//跳过第0页
		if(pageNum!=0)
			PageHelper.startPage(pageNum, pageSize);
		else PageHelper.startPage(pageNum+1, pageSize);
		PageInfo<Article> pageInfo;


		if(keyword != null&&keyword != "") {
			List<Article> articleByKeyword = articleService.findArticleByKeyword(keyword);
			pageInfo = new PageInfo<>(articleByKeyword);
			model.addAttribute("keyword", keyword);
			model.addAttribute("articles", articleByKeyword);
		}else {
			List<Article> list = articleService.getAllArticle();
			pageInfo = new PageInfo<>(list);
			model.addAttribute("articles", list);
			System.out.println("list size :" + pageInfo.getPages());
			
		}

		List<Articletype> firstArticleType = articleTypeService.getFirstArticleType();
		/*for(int index=0; index<firstArticleType.size(); index++) {
			String code = firstArticleType.get(index).getCode();
			List<SecondArticleType> secondArticleType = articleTypeService.getSecondArticleType(code);
			firstArticleType.get(index).setSecondArticleType(secondArticleType);
		}*/
		
		model.addAttribute("pageInfo", pageInfo);
		session.setAttribute("firstArticleTypes", firstArticleType);
//		model.addAttribute("firstArticleTypes", firstArticleType);
//		model.addAttribute("secondArticleTypes", secondArticleType);

		
		return "list";
	}
	
}
