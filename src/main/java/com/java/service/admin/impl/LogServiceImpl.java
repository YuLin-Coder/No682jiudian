package com.java.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.admin.LogMapper;
import com.java.po.admin.Log;
import com.java.service.admin.LogService;
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logDao;

	public int add(Log log) {
		// TODO Auto-generated method stub
		return logDao.add(log);
	}

	public List<Log> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return logDao.findList(queryMap);
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return logDao.getTotal(queryMap);
	}

	public int delete(String ids) {
		// TODO Auto-generated method stub
		return logDao.delete(ids);
	}

	public int add(String content) {
		// TODO Auto-generated method stub
		Log log = new Log();
		log.setContent(content);
		log.setCreateTime(new Date());
		return logDao.add(log);
	}
	
	

}
