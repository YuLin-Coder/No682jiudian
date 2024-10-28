package com.java.mapper.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.po.admin.User;

@Repository
public interface UserMapper {
	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int editPassword(User user);
	public int delete(String ids);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
