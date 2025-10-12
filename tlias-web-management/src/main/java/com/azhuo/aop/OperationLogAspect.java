package com.azhuo.aop;

import com.azhuo.mapper.OperateLogMapper;
import com.azhuo.pojo.OperateLog;
import com.azhuo.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志切面类 针对controller层中增删改方法的操作日志记录
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private final OperateLogMapper operateLogMapper;

    public OperationLogAspect(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    @Around("@annotation(com.azhuo.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long beginTime = System.currentTimeMillis();

        // 执行目标方法
        // 只能用ProceedingJoinPoint来获取方法参数
        // joinPoint.proceed()表示执行目标方法
        Object result = joinPoint.proceed();

        // 结束时间
        long endTime = System.currentTimeMillis();

        // 操作日志对象
        // 包括记录操作人ID、操作时间、类名、方法名、方法参数、返回值、耗时
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(CurrentHolder.getCurrentId()); // 从ThreadLocal中获取当前用户ID
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(endTime - beginTime);

        // 记录日志
        log.info("操作日志：{}", operateLog);

        // 插入操作日志
        operateLogMapper.insert(operateLog);

        return result;
    }
}
