package com.myCode.model;

import java.util.ArrayList;

import com.myCode.entity.Blog;
import com.myCode.entity.Comment;


public class BlogWithCommentsModel extends Blog{
	
	ArrayList<Comment> commentList = null;
	
	public BlogWithCommentsModel()
	{
		commentList = new ArrayList<Comment>();
	}
	
	public BlogWithCommentsModel(Blog blog)
	{
		super(blog);
		commentList = new ArrayList<Comment>();	
	}
	
	

	public ArrayList<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}
	
	public String toString()
	{
		return "Blog id : "+ this.getId()+" title : "+this.getBlogTitle()+" Body : "+this.getBlogBody();
	}

}
