package com.azhuo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {
    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查）
     * 返回 true 表示继续流程（如调用下一个拦截器或处理器）
     * 返回 false 表示流程中断（如登录检查失败），不会继续调用其他拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("DemoInterceptor preHandle");
        return true;
    }
    /**
     * 后处理回调方法，实现处理器的后处理（如视图渲染）
     * 只有在 preHandle 返回 true 时才会调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("DemoInterceptor postHandle");
    }
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时调用（如日志记录）
     * 只有在 preHandle 返回 true 时才会调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("DemoInterceptor afterCompletion");
    }
}
