package com.java.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.admin.UserMapper;
import com.java.po.admin.User;
import com.java.service.admin.UserService;
/**
 * user�û�serviceimpl
 * @author llq
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;
	
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}

	public int edit(User user) {
		// TODO Auto-generated method stub
		return userDao.edit(user);
	}

	public int delete(String ids) {
		// TODO Auto-generated method stub
		return userDao.delete(ids);
	}

	public List<User> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.findList(queryMap);
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.getTotal(queryMap);
	}

	public int editPassword(User user) {
		// TODO Auto-generated method stub
		return userDao.editPassword(user);
	}

}
