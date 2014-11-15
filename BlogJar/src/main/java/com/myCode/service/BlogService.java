package com.myCode.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myCode.dao.BlogDao;
import com.myCode.entity.Blog;

@Service
public class BlogService {
	
	private BlogDao blogDao;
	private static final Logger logger = Logger.getLogger(BlogService.class);
	
	@Autowired
	public void setBlogDao(BlogDao blogDao)
	{
		this.blogDao = blogDao;
	}
	
	public void insertNewBlog(Blog blog, String currUser)
	{
		blog.setUserName(currUser);
		blog.setBlogDate(new Date().toString());
		blogDao.addNewBlog(blog);
	}
	
	public List<Blog> getBlogListByUserName(String username)
	{
		List<Blog> blogList = blogDao.getBlogListByUser(username);
		
		return blogList;
	}
	
	public List<String> getAllUsers()
	{
		List<String> userList =  blogDao.getUserList();
		logger.info("getAllUsers : Got "+userList.size()+" users : "+userList.toString());
		
		return userList;
	}

}
