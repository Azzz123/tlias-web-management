package com.azhuo.service;

import com.azhuo.pojo.Emp;
import com.azhuo.pojo.EmpQueryParam;
import com.azhuo.pojo.PageResult;

import java.util.List;


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

    /**
     * 新增员工
     */
    void save(Emp emp);

    /**
     * 批量删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工详情
     */
    Emp getInfo(Integer id);
}
