package com.azhuo.service;

import com.azhuo.pojo.JobOption;
import com.azhuo.pojo.StudentClazzData;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位数量
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别数量
     */
    List<Map<String, Object>> getEmpGenderData();

     /**
      * 统计班级人数
      */
    StudentClazzData getStudentCount();
}
