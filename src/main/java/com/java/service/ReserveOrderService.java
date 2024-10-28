package com.java.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.po.ReserveOrder;

@Service
public interface ReserveOrderService {
	public int add(ReserveOrder ReserveOrder);

	public int edit(ReserveOrder ReserveOrder);

	public int edits(ReserveOrder ReserveOrder);

	public int delete(Long id);
	public int updatestatus(Long id);

	public List<ReserveOrder> findList(Map<String, Object> queryMap);

	public Integer getTotal(Map<String, Object> queryMap);

	public ReserveOrder find(Long id);
}
