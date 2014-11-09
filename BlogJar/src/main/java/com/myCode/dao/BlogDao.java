package com.myCode.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.transaction.annotation.Transactional;

import com.myCode.entity.Blog;
import com.myCode.entity.Counter;

public class BlogDao{
	
	private MongoTemplate mongoTemplate;
	private MongoOperations mongoOps;

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
		blog.setId(getNextSequenceId("blogSequence"));
		mongoOps.insert(blog);
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

}
