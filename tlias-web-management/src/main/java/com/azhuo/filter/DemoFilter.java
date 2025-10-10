package com.azhuo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class DemoFilter implements Filter {
    /**
     * 初始化方法，在过滤器被创建时调用，仅调用一次
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter.init");
    }

    /**
     * 拦截到请求后执行，会执行多次，每次执行完后，会调用filterChain.doFilter()方法，将请求传递给下一个过滤器
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("DemoFilter.doFilter");

        // 放行请求
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 销毁方法，在过滤器被销毁时调用，仅调用一次
     */
    @Override
    public void destroy() {
        log.info("DemoFilter.destroy");
    }
}
