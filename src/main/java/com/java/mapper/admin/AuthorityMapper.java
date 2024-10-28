package com.java.mapper.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.po.admin.Authority;

@Repository
public interface AuthorityMapper {
	public int add(Authority authority);

	public int deleteByRoleId(Long roleId);

	public List<Authority> findListByRoleId(Long roleId);
}
