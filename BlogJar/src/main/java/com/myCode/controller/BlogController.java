package com.myCode.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myCode.constant.ViewManagerConstant;
import com.myCode.entity.Blog;
import com.myCode.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/newblog", method=RequestMethod.GET)
	public ModelAndView showNewBlogPage(){
		ModelAndView model = new ModelAndView(ViewManagerConstant.NEWBLOG);
		model.getModelMap().addAttribute("blog", new Blog());
		return model;
	}
	
	@RequestMapping(value = "/newblog.do", method=RequestMethod.POST)
	public ModelAndView createNewBlog(@ModelAttribute("blog") Blog blog,HttpServletRequest request){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.BLOGLIST);
		HttpSession session = request.getSession(false);
		String currUser = (String)session.getAttribute("currusername");
		
		blogService.insertNewBlog(blog,currUser);
		model.getModelMap().addAttribute("msg1", "New Blog added successfully");
		
		List<Blog> blogList = blogService.getBlogListByUserName(currUser);
		model.getModelMap().addAttribute("blogList", blogList);
		
		return model;
	}
	
	@RequestMapping(value = "/myblogs", method=RequestMethod.GET)
	public ModelAndView showMyBlogs(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.BLOGLIST);
		HttpSession session = request.getSession(false);
		String currUser = (String)session.getAttribute("currusername");
		
		//model.getModelMap().addAttribute("msg1", "New Blog added successfully");
		
		List<Blog> blogList = blogService.getBlogListByUserName(currUser);
		model.getModelMap().addAttribute("blogList", blogList);
		
		return model;
	}
	
	@RequestMapping(value = "/blogs", method=RequestMethod.GET)
	public ModelAndView showAllUsers(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.USERLIST);
//		HttpSession session = request.getSession(false);
	//	String currUser = (String)session.getAttribute("currusername");
		
		//model.getModelMap().addAttribute("msg1", "New Blog added successfully");
		
		List<String> blogUserList = blogService.getAllUsers();
		model.getModelMap().addAttribute("blogUserList", blogUserList);
		
		return model;
	}
}
