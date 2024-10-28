package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.AccountMapper;
import com.java.po.Account;
import com.java.service.AccountService;
import com.java.util.MD5Util;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountDao;

	public int add(Account account) {
		// TODO Auto-generated method stub
		account.setPassword(MD5Util.md5Password(account.getPassword()));// 密码加密
		return accountDao.add(account);
	}

	public int edit(Account account) {
		// TODO Auto-generated method stub
		return accountDao.edit(account);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return accountDao.delete(id);
	}

	public List<Account> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return accountDao.findList(queryMap);
	}

	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return accountDao.getTotal(queryMap);
	}

	public Account find(Long id) {
		// TODO Auto-generated method stub
		return accountDao.find(id);
	}

	public Account findByName(String name) {
		// TODO Auto-generated method stub
		return accountDao.findByName(name);
	}

	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDao.findAll();
	}

}
