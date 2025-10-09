package com.azhuo.service;

import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import com.azhuo.pojo.PageResult;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);
}
