package com.myCode.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "blogs")
public class Blog {
	
	@Id
	private long id;
	
	@Field("user")
	private String userName;
	
	@Field("title")
	private String blogTitle;
	
	@Field("body")
	private String blogBody;
	
	@Field("date")
	private String blogDate;
	
	@Field("tags")
	private ArrayList<String> tagList;
	
	public Blog(){
		
	}
	
	public Blog(Blog blog)
	{
		this.id = blog.getId();
		this.blogTitle = blog.getBlogTitle();
		this.blogBody = blog.getBlogBody();
		this.userName = blog.getUserName();
		this.blogDate = blog.getBlogDate();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogBody() {
		return blogBody;
	}

	public void setBlogBody(String blogBody) {
		this.blogBody = blogBody;
	}

	public String getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(String blogDate) {
		this.blogDate = blogDate;
	}

	public ArrayList<String> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}
	
	
	
	

}
