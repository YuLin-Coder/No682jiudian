package com.java.mapper.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.po.admin.Log;


@Repository
public interface LogMapper {
	public int add(Log log);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
