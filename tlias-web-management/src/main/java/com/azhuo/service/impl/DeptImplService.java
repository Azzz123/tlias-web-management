package com.azhuo.service.impl;

import com.azhuo.mapper.DeptMapper;
import com.azhuo.pojo.Dept;
import com.azhuo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    // 新增部门
    @Override
    public void add(Dept dept) {
        // 补全基础属性，creatTime和updateTime
        // 但是数据库的create_time和update_time是自动填充的，所以这里不需要设置
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 调用
        deptMapper.insert(dept);
    }

    // 查询部门详情
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


}
