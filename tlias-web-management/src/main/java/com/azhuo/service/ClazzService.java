package com.azhuo.service;

import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import com.azhuo.pojo.PageResult;

public interface ClazzService {
    /**
     * 分页查询班级
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
    /**
     * 添加班级
     */
    void save(Clazz clazz);
    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void update(Clazz clazz);
}
