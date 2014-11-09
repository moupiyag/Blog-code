package com.myCode.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class Comment {

	@Id
	private String id;
	
	@Field("blogid")
	private String blogId;
	
	@Field("user")
	private String userName;
	
	@Field("date")
	private String commentDate;
	
	@Field("comment")
	private String commentBody;
	
}
