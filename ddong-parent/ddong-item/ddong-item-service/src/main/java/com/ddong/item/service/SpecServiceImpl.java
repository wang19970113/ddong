package com.ddong.item.service;

import com.ddong.item.mapper.SpecMapper;
import com.ddong.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecMapper specMapper;
    @Override
    public Specification querySpecByCid(Long cid) {
        return specMapper.selectByPrimaryKey(cid);
    }
}
