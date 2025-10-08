package com.azhuo.service;

import com.azhuo.pojo.JobOption;

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
}
