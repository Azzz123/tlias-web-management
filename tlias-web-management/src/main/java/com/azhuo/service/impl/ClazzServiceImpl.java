package com.azhuo.service.impl;

import com.azhuo.exception.DataRelationViolationException;
import com.azhuo.mapper.ClazzMapper;
import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import com.azhuo.pojo.PageResult;
import com.azhuo.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private final ClazzMapper clazzMapper;
    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }
    /**
     * 分页查询班级
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 将 startPage 调用直接放入 try-with-resources 语句
        //    这样就捕获了它返回的 AutoCloseable 对象 page，并确保它最后会被 close()
        try (Page<Object> page = PageHelper.startPage(
                clazzQueryParam.getPage(),
                clazzQueryParam.getPageSize()
        )) {
            // 2. 在 try 块内正常执行查询
            List<Clazz> rows = clazzMapper.list(clazzQueryParam);
            // 3. 班级状态，设置为：未开班、已结课、在读中 这三种状态
            rows.forEach(clazz -> {
                if (clazz.getBeginDate().isAfter(LocalDate.now())) {
                    clazz.setStatus("未开班");
                } else if (clazz.getEndDate().isBefore(LocalDate.now())) {
                    clazz.setStatus("已结课");
                } else {
                    clazz.setStatus("在读中");
                }
            });
            // 4. 使用 page 对象获取总数，使用查询结果 rows 获取数据列表
            //    PageHelper 拦截器在执行完查询后，会自动把总记录数回填到 try() 中创建的 page 对象里
            return new PageResult<>(page.getTotal(), rows);
        }
    }
    /**
     * 添加班级
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }
    /**
     * 根据ID查询班级
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 修改班级
     */
    @Override
    @Transactional
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

     /**
      * 根据ID删除班级
      */
    @Override
    public void deleteById(Integer id) {
        // 若student表中存在该班级的学生记录，不允许删除
        if (clazzMapper.countStudentByClazzId(id) > 0) {
            throw new DataRelationViolationException("对不起, 该班级下有学生, 不能直接删除");
        }
        // 若不存在学生记录，允许删除
        clazzMapper.deleteById(id);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
