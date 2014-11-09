package com.myCode.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myCode.dao.BlogDao;
import com.myCode.entity.Blog;

@Service
public class BlogService {
	
	private BlogDao blogDao;
	
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

}
