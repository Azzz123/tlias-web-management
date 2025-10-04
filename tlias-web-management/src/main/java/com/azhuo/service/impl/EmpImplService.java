package com.azhuo.service.impl;

import com.azhuo.mapper.EmpMapper;
import com.azhuo.pojo.Emp;
import com.azhuo.pojo.PageResult;
import com.azhuo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpImplService implements EmpService {
    /**
     * 分页查询员工
     */
    @Autowired
    private final EmpMapper empMapper;
    public EmpImplService(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 1.计算总数
        Long total = empMapper.count();
        // 2.计算起始索引
        Integer start = (page - 1) * pageSize;
        // 3.查询每页数据
        List<Emp> rows = empMapper.list(start, pageSize);
        // 4.封装分页结果
        return new PageResult<>(total, rows);
    }
}
