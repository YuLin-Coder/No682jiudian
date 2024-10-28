package com.java.util;

import org.springframework.stereotype.Component;

/**
 * 拦截 前端 传值 实现 分页
 * @author llq
 *
 */
@Component
public class Page {
	private int page = 1;
	
	private int rows;
	
	private int offset;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getOffset() {
		this.offset = (page - 1) * rows;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
