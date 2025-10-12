package com.azhuo.mapper;

import com.azhuo.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    void insert(OperateLog log);

    /**
     * 分页查询操作日志
     */
    List<OperateLog> list();
}
