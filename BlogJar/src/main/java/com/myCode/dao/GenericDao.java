package com.myCode.dao;

import java.io.Serializable;

import com.myCode.model.ResultPage;

public interface GenericDao<T,ID extends Serializable> {
	
	void create(T entity);
	void create(T entity,ID id);
	
	T findById(ID id);
	void update(T entity);
	void delete(T entity);
	ResultPage<T> findByFilter(String q,String orders,int pageNumber,int pageSize);
}
