package com.sermo.components.biz.model;

import java.util.List;

/**
 * @author sermo
 * @version 2016年6月24日 
 */
public class Pager<T> {
	
	public static final int DEFAULT_PAGE = 1;
	
	public static final int DEFAULT_ROW = 20;
	
	/*
	 * 当前页 
	 */
	private int page = DEFAULT_PAGE;
	
	/**
	 * 每页多少行
	 */
	private int row = DEFAULT_ROW;
	
	/**
	 * 总页数
	 */
	private int total;
	
	/**
	 * 总个数
	 */
	private long count;
	
	private List<T> results;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.total = (int) (count % row == 0 ? count / row : count / row + 1);
		this.count = count;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	
	public long getStart() {
		return (page - 1) * row;
	}
}
