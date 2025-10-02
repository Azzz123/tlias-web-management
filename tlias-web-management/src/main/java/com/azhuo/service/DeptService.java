package com.azhuo.service;

import com.azhuo.pojo.Dept;

import java.util.List;

public interface DeptService {
    // 查询全部部门数据
    List<Dept> findAll();
    // 删除部门
    void delete(Integer id);
}
