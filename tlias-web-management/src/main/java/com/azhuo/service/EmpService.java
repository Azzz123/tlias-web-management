package com.azhuo.service;

import com.azhuo.pojo.Emp;
import com.azhuo.pojo.EmpQueryParam;
import com.azhuo.pojo.PageResult;


public interface EmpService {
    /**
     * 分页查询员工
     */
    /*PageResult<Emp> page(Integer page,
                         Integer pageSize,
                         String name,
                         Integer gender,
                         LocalDate begin,
                         LocalDate end);*/

    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
