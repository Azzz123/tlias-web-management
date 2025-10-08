package com.azhuo.controller;

import com.azhuo.pojo.JobOption;
import com.azhuo.pojo.Result;
import com.azhuo.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 统计员工职位数量
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位数量");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
}
