package com.planitary.base.handler;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zane
 * @date 2024-10-10 17:15:41
 * @description：全局traceId处理器
 */
public class TraceIdHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 生成唯一的 Trace ID
        String traceId = UUID.randomUUID().toString();

        // 将 Trace ID 放入 MDC
        MDC.put("traceId", traceId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后清除 Trace ID
        MDC.remove("traceId");
    }
}
