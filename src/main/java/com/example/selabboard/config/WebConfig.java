package com.example.selabboard.config;

import com.example.selabboard.interceptor.LoginCheckInterceptor;
import com.example.selabboard.interceptor.WriteCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WriteCheckInterceptor writeCheckInterceptor() {
        return new WriteCheckInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/list");

        registry.addInterceptor(writeCheckInterceptor())
                .order(2)
                .addPathPatterns("/board/write", "/board/delete/**");
    }
}
