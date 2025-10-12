package com.azhuo.service.impl;

import com.azhuo.mapper.OperateLogMapper;
import com.azhuo.pojo.OperateLog;
import com.azhuo.pojo.PageResult;
import com.azhuo.service.OperateLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private final OperateLogMapper operateLogMapper;
    public OperateLogServiceImpl(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        // 1. 将 startPage 调用直接放入 try-with-resources 语句
        //    这样就捕获了它返回的 AutoCloseable 对象 page，并确保它最后会被 close()
        try (Page<OperateLog> pages = PageHelper.startPage(
                page,
                pageSize
        )) {
            // 2. 在 try 块内正常执行查询
            List<OperateLog> rows = operateLogMapper.list();
            // 3. 使用 page 对象获取总数，使用查询结果 rows 获取数据列表
            //    PageHelper 拦截器在执行完查询后，会自动把总记录数回填到 try() 中创建的 page 对象里
            return new PageResult<>(pages.getTotal(), rows);
        }
    }
}
