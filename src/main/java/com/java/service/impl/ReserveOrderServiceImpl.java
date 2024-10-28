package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.ReserveOrderMaper;
import com.java.po.ReserveOrder;
import com.java.service.ReserveOrderService;

@Service
public class ReserveOrderServiceImpl implements ReserveOrderService {

    @Autowired
    private ReserveOrderMaper ReserveOrderDao;

    @Override
    public int add(ReserveOrder ReserveOrder) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.add(ReserveOrder);
    }

    @Override
    public int edit(ReserveOrder ReserveOrder) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.edit(ReserveOrder);
    }

    @Override
    public int edits(ReserveOrder ReserveOrder) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.edits(ReserveOrder);
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.delete(id);
    }

    @Override
    public int updatestatus(Long id) {
        return ReserveOrderDao.updatestatus(id);
    }

    @Override
    public List<ReserveOrder> findList(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.findList(queryMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.getTotal(queryMap);
    }

    @Override
    public ReserveOrder find(Long id) {
        // TODO Auto-generated method stub
        return ReserveOrderDao.find(id);
    }

}
