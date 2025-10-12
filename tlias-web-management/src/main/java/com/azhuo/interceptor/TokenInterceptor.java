package com.azhuo.interceptor;

import com.azhuo.utils.CurrentHolder;
import com.azhuo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1 获取请求路径。
        String requestURI = request.getRequestURI();

        // 2 判断是否需要拦截
        if(requestURI.contains("/login")){
            // 登录请求，放行
            log.info("登录请求，放行");
            return true;
        }

        // 3 其他请求，判断是否有token
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            // 3.1 没有token，说明用户未登录，返回错误信息并响应状态码401
            log.info("令牌为空，用户未登录，响应状态码401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is empty");
            return false;
        }

        // 3.2 有token，则需要校验token是否有效

        // 3.2.1 token无效，返回错误信息并响应状态码401
        try {
            Claims claims = JwtUtils.parseJWT(token);
            // 3.2.1.1 校验通过，将用户id存储到ThreadLocal中，以便后续日志记录使用
            Integer employeeId = Integer.parseInt(claims.get("id").toString());
            CurrentHolder.setCurrentId(employeeId);
        } catch (Exception e) {
            // 3.3 token无效，返回错误信息并响应状态码401
            log.info("令牌无效，响应状态码401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is invalid");
            return false;
        }

        // 3.2.2 token有效，放行
        log.info("令牌有效，放行");
        return true;
    }

    /**
     * 放行后，移除ThreadLocal中的用户id，防止内存泄漏
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 4 移除ThreadLocal中的用户id
        CurrentHolder.remove();
    }
}
