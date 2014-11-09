package com.myCode.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultPage<T> {
	
	private List<T> results = new LinkedList<T>();
	private long count;
	
	public ResultPage(){
	}
	
	public ResultPage(List<T> results, long count){
		this.count = count;
		this.results = results;
	}

	@XmlElement
	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	@XmlElement
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	@Override
	public String toString()
	{
		return "ResultPage [ results = "+results+" , count = "+count+"]";
	}

}
