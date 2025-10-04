package com.azhuo.service;

import com.azhuo.pojo.Emp;
import com.azhuo.pojo.PageResult;

public interface EmpService {
    /**
     * 分页查询员工
     * @param page 页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageResult<Emp> page(Integer page, Integer pageSize);
}
