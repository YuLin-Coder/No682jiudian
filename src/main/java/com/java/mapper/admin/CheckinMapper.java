package com.java.mapper.admin;
import java.util.List;
import java.util.Map;

/**
 * ��סdao
 */
import org.springframework.stereotype.Repository;

import com.java.po.admin.Checkin;

@Repository
public interface CheckinMapper {
	public int add(Checkin checkin);
	public int edit(Checkin checkin);
	public int delete(Long id);
	public List<Checkin> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public Checkin find(Long id);
	public List<Map> getStatsByMonth();
	public List<Map> getStatsByDay();
}
