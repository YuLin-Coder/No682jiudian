package com.java.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.po.Account;
@Repository
public interface AccountMapper {
	
	public int add(Account account);
	public int edit(Account account);
	public int delete(Long id);
	public List<Account> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public Account find(Long id);
	public Account findByName(String name);
	public List<Account> findAll();

}
