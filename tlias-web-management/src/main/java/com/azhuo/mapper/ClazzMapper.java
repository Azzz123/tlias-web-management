package com.azhuo.mapper;

import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);
}
