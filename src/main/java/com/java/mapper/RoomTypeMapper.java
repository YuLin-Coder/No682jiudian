package com.java.mapper;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.po.RoomType;

@Repository
public interface RoomTypeMapper {
	public int add(RoomType roomType);
	public int edit(RoomType roomType);
	public int delete(Long id);
	public int record();// 总记录数
	public List<RoomType> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public List<RoomType> findAll();
	public RoomType find(Long id);
	public int updateNum(RoomType roomType);
}
