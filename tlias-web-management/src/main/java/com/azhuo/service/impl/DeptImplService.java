package com.azhuo.service.impl;

import com.azhuo.mapper.DeptMapper;
import com.azhuo.pojo.Dept;
import com.azhuo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptImplService implements DeptService {
    @Autowired
    private final DeptMapper deptMapper;
    // 构造方法注入部门映射器
    public DeptImplService(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }
    // 查询全部部门数据
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

     // 删除部门
    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

}
