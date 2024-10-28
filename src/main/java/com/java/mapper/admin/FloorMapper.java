package com.java.mapper.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.po.admin.Floor;

@Repository
public interface FloorMapper {
	public int add(Floor floor);
	public int edit(Floor floor);
	public int delete(Long id);
	public List<Floor> findList(Map<String, Object> queryMap);
	public List<Floor> findAll();
	public Integer getTotal(Map<String, Object> queryMap);
}
