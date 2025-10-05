package com.azhuo.mapper;

import com.azhuo.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 员工工作经历操作接口
@Mapper
public interface EmpExprMapper {
    void insertBatch(List<EmpExpr> exprList);
}
