package com.planitary.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    // 开启CORS
    // springboot开启CORS的方法：https://blog.csdn.net/JokerLJG/article/details/123659384
    // 1.自定义过滤器 2.实现WebMvcConfigurer接口 3.@CrossOrigin注解，其中若采取2的方式，当allowCredentials为true时,
    // 要把allowedOrgins改成 allowedOriginPatterns
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // 允许所有域
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                        .allowedHeaders("*") // 允许所有请求头
                        .allowCredentials(true); // 允许发送凭据信息（如cookies）

                // 可以根据实际需要进行更详细的配置
            }
        };
    }

}
