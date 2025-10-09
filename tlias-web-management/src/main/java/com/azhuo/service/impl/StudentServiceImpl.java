package com.azhuo.service.impl;

import com.azhuo.mapper.StudentMapper;
import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;
import com.azhuo.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentMapper studentMapper;
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * 分页查询学生
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 将 startPage 调用直接放入 try-with-resources 语句
        //    这样就捕获了它返回的 AutoCloseable 对象 page，并确保它最后会被 close()
        try (Page<Object> page = PageHelper.startPage(
                studentQueryParam.getPage(),
                studentQueryParam.getPageSize()
        )) {
            // 2. 在 try 块内正常执行查询
            List<Student> rows = studentMapper.page(studentQueryParam);
            // 3. 使用 page 对象获取总数，使用查询结果 rows 获取数据列表
            //    PageHelper 拦截器在执行完查询后，会自动把总记录数回填到 try() 中创建的 page 对象里
            return new PageResult<>(page.getTotal(), rows);
        }
    }
    /**
     * 添加学员
     */
    @Override
    public void add(Student student) {
        // 1. 设置创建时间和更新时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        // 2. 调用 mapper 方法添加学员
        studentMapper.insert(student);
    }
    /**
     * 根据ID查询学生
     */
    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }

    /**
     * 批量删除学生
     */
    @Override
    public void delete(List<Integer> ids) {
        // 1. 调用 mapper 方法删除学生
        studentMapper.delete(ids);
    }
    /**
     * 修改学生
     */
    @Override
    public void update(Student student) {
        // 1. 设置更新时间
        student.setUpdateTime(LocalDateTime.now());
        // 2. 调用 mapper 方法修改学生
        studentMapper.update(student);
    }
    /**
     * 违纪处理
     */
    @Override
    @Transactional
    public void violation(Integer id, Short score) {
        // 1. 根据 ID 查询学生
        Student student = studentMapper.selectById(id);
        // 2. 增加违纪次数和违纪分数
        try {
            student.setViolationCount((short) (student.getViolationCount() + 1));
            student.setViolationScore((short) (student.getViolationScore() + score));
        } catch (Exception e) {
            // 5. 捕获异常，回滚事务
            throw new RuntimeException("违纪处理失败", e);
        }
        // 3. 设置更新时间
        student.setUpdateTime(LocalDateTime.now());
        // 4. 调用 mapper 方法更新学生
        studentMapper.update(student);
    }
}
