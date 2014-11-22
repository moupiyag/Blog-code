package com.myCode.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myCode.constant.ViewManagerConstant;
import com.myCode.dao.BlogDao;
import com.myCode.entity.Blog;
import com.myCode.entity.Comment;
import com.myCode.model.BlogWithCommentsModel;
import com.myCode.service.BlogService;
import org.apache.log4j.Logger;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	private static final Logger logger = Logger.getLogger(BlogDao.class);
	
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
		
		List<Blog> blogList = blogService.getBlogListByUserName(currUser);
		model.getModelMap().addAttribute("blogList", blogList);
		
		return model;
	}
	
	@RequestMapping(value = "/blogs", method=RequestMethod.GET)
	public ModelAndView showAllUsers(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.USERLIST);
		
		List<String> blogUserList = blogService.getAllUsers();
		model.getModelMap().addAttribute("blogUserList", blogUserList);
		
		return model;
	}
	
	@RequestMapping(value = "/showblogs", method=RequestMethod.GET)
	public ModelAndView showBlogsForUser(@RequestParam("forUser") String userName){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.BLOGLIST);
		
		List<Blog> blogList = blogService.getBlogListByUserName(userName);
		model.getModelMap().addAttribute("blogList", blogList);
		model.getModelMap().addAttribute("user", userName);
		
		return model;
	}
	
	@RequestMapping(value = "/showblog.do", method=RequestMethod.GET)
	public ModelAndView showBlogWithComments(@RequestParam("blog") long blogId){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.BLOGCOMMENTS);
		
		BlogWithCommentsModel blogObject = blogService.getBlogWithComments(blogId);
		logger.info("BlogWithCommentsModel blogObject fetched : "+blogObject.toString());
		model.getModelMap().addAttribute("blogObject", blogObject);
		Comment comment = new Comment();
		comment.setBlogId(blogId);
		model.getModelMap().addAttribute("comment", comment);
		
		return model;
	}
	
	@RequestMapping(value = "/newcomment.do", method=RequestMethod.POST)
	public ModelAndView addNewComment(@ModelAttribute("comment") Comment newComment,HttpServletRequest request){
		
		ModelAndView model = new ModelAndView(ViewManagerConstant.BLOGCOMMENTS);
		
		HttpSession session = request.getSession(false);
		String currUser = (String)session.getAttribute("currusername");
		long blogId = newComment.getBlogId();
		
		blogService.addNewComment(newComment,currUser);
		
		BlogWithCommentsModel blogObject = blogService.getBlogWithComments(blogId);
		model.getModelMap().addAttribute("blogObject", blogObject);
		Comment comment = new Comment();
		comment.setBlogId(blogId);
		model.getModelMap().addAttribute("comment", comment);
		
		return model;
	}
	
	
	
}
