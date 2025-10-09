package com.azhuo.service.impl;

import com.azhuo.exception.DeptHavingEmpException;
import com.azhuo.mapper.DeptMapper;
import com.azhuo.mapper.EmpMapper;
import com.azhuo.pojo.Dept;
import com.azhuo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private final DeptMapper deptMapper;
    @Autowired
    private final EmpMapper empMapper;
    // 构造方法注入部门映射器
    public DeptServiceImpl(DeptMapper deptMapper, EmpMapper empMapper) {
        this.deptMapper = deptMapper;
        this.empMapper = empMapper;
    }
    // 查询全部部门数据
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

     // 删除部门
    @Override
    @Transactional
    public void delete(Integer id) {
//    如果部门下有员工，则不允许删除该部门
//    给前端提示错误信息：对不起，当前部门下有员工，不能直接删除！
//    否则，删除部门
        // 检查部门下是否有员工
        int count = empMapper.countByDeptId(id);
        if (count > 0) {
            // 有员工，不允许删除
            throw new DeptHavingEmpException("对不起，当前部门下有员工，不能直接删除！");
        }
        // 没有员工，允许删除
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
