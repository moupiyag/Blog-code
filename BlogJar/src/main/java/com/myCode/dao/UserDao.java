package com.myCode.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.myCode.entity.Users;

@Transactional
public class UserDao {
	
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
	public void createNewUser(Users user)
	{
		mongoOps.insert(user);
	}
	
	public Users getUserDetails(String username)
	{
		Users user = mongoOps.findOne(new Query(Criteria.where("username").is(username)),Users.class );
		System.out.println("UserDao : getUserDetails : "+user);
		return user;
	}

}
