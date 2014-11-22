package com.myCode.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.DBCollection;
import com.myCode.entity.Blog;
import com.myCode.entity.Comment;
import com.myCode.entity.Counter;

public class BlogDao{
	
	private MongoTemplate mongoTemplate;
	private MongoOperations mongoOps;
	
	private static final Logger logger = Logger.getLogger(BlogDao.class);

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		this.mongoOps = (MongoOperations)mongoTemplate;
	}
	
	@Transactional(rollbackFor= Exception.class)
	public void addNewBlog(Blog blog)
	{
		logger.info("addNewBlog : Inserting new blog for User : "+blog.getUserName());
		blog.setId(getNextSequenceId("blogSequence"));
		logger.info("addNewBlog : Sequence of new blog : "+blog.getId());
		mongoOps.insert(blog);
		logger.info("addNewBlog : New blog inserted");
	}
	
	private long getNextSequenceId(String collectionName)
	{
		Query query = new Query(Criteria.where("_id").is(collectionName));
		Update update = new Update();
		update.inc("sequence", 1);
		
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		
		Counter seqId = mongoOps.findAndModify(query, update, options,Counter.class);
//		if (seqId == null) {
//			throw new SequenceException("Unable to get sequence id for key : " + key);
//		  }
		
		return seqId.getSequence();
	}
	
	@Transactional(rollbackFor= Exception.class)
	public List<Blog> getBlogListByUser(String username)
	{
		logger.info("getBlogListByUser : Getting Blog List for user : "+username);
		Query query = new Query();
		query.addCriteria(Criteria.where("user").is(username));
		logger.info("getBlogListByUser : Query created : "+query);
		
		List<Blog> blogList = mongoOps.find(query,Blog.class);
		logger.info("getBlogListByUser : Fetched "+blogList.size()+ " blogs for user : "+username);
		
		return blogList;
	}
	
	@Transactional(rollbackFor= Exception.class)
	public List<String> getUserList()
	{
		DBCollection user = mongoOps.getCollection("blogs");
		List<String> blogUserList = user.distinct("user");
		logger.info("getUserList : Fetched "+blogUserList.size()+" users : "+blogUserList.toString());
		
		return blogUserList;
	}
	
	@Transactional(rollbackFor= Exception.class)
	public void addNewComment(Comment comment)
	{
		logger.info("addNewComment : Inserting new commeny for Blog Id : "+comment.getBlogId()+" of user : "+comment.getUserName());
		comment.setId(getNextSequenceId("commentSequence"));
		logger.info("addNewComment : Sequence of new comment : "+comment.getId());
		mongoOps.insert(comment);
		logger.info("addNewComment : New comment inserted");
	}
	
	@Transactional(rollbackFor= Exception.class)
	public List<Comment> getCommentsByBlogId(long blogId)
	{
		logger.info("getCommentsByBlogId : Getting Comment List for blog : "+blogId);
		Query query = new Query();
		query.addCriteria(Criteria.where("blogid").is(blogId));
		logger.info("getCommentsByBlogId : Query created : "+query);
		
		List<Comment> commentList = mongoOps.find(query,Comment.class);
		logger.info("getCommentsByBlogId : Fetched "+commentList.size()+ " comments for blog : "+blogId);
		
		return commentList;
	}
	
	@Transactional(rollbackFor= Exception.class)
	public Blog getBlogById(long blogId)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(blogId));
		
		Blog currBlog = mongoOps.findOne(query, Blog.class);
		
		return currBlog;
	}

	

}
