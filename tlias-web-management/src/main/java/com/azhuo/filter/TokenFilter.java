package com.azhuo.filter;

import com.azhuo.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // ServletRequest是请求对象，ServletResponse是响应对象，这两个都是HttpServletRequest和HttpServletResponse的子类。
        // FilterChain是过滤器链对象。

        // 将ServletRequest转换为HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        // 将ServletResponse转换为HttpServletResponse
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        // 1 获取请求路径。
        String requestURI = httpServletRequest.getRequestURI();

        // 2 判断是否需要拦截
        if(requestURI.contains("/login")){
            // 登录请求，放行
            log.info("登录请求，放行");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        // 3 其他请求，判断是否有token
        String token = httpServletRequest.getHeader("token");
        if(token == null || token.isEmpty()){
            // 3.1 没有token，说明用户未登录，返回错误信息并响应状态码401
            log.info("令牌为空，用户未登录，响应状态码401");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is empty");
            return;
        }

        // 3.2 有token，则需要校验token是否有效

        // 3.2.1 token无效，返回错误信息并响应状态码401
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            // 3.3 token无效，返回错误信息并响应状态码401
            log.info("令牌无效，响应状态码401");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is invalid");
            return;
        }

        // 3.2.2 token有效，放行
        log.info("令牌有效，放行");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
