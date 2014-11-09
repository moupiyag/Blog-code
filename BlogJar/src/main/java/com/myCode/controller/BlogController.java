package com.myCode.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myCode.entity.Blog;
import com.myCode.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/newblog", method=RequestMethod.GET)
	public ModelAndView showNewBlogPage(){
		ModelAndView model = new ModelAndView("newBlogPage");
		model.getModelMap().addAttribute("blog", new Blog());
		return model;
	}
	
	@RequestMapping(value = "/newblog.do", method=RequestMethod.POST)
	public ModelAndView createNewBlog(@ModelAttribute("blog") Blog blog,HttpServletRequest request){
		
		ModelAndView model = new ModelAndView("BlogList");
		HttpSession session = request.getSession(false);
		String currUser = (String)session.getAttribute("currusername");
		if(StringUtils.isBlank(currUser))
		{
			model.getModelMap().addAttribute("msg1", "current user is null");
			System.out.println("user is null "+currUser);
		}
			
		blogService.insertNewBlog(blog,currUser);
		model.getModelMap().addAttribute("msg1", "New Blog added successfully");
		
		return model;
	}
}
