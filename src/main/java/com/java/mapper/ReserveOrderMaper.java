package com.java.mapper;
import java.util.List;
import java.util.Map;

/**
 * Ԥ������dao
 */
import org.springframework.stereotype.Repository;

import com.java.po.ReserveOrder;

@Repository
public interface ReserveOrderMaper {
	public int add(ReserveOrder reserveOrder);
	public int edit(ReserveOrder reserveOrder);
	public int edits(ReserveOrder reserveOrder);
	public int delete(Long id);
	public int updatestatus(Long id);
	public List<ReserveOrder> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public ReserveOrder find(Long id);
}
