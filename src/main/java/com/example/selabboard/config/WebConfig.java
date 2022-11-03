package com.example.selabboard.config;

import com.example.selabboard.interceptor.LoginCheckInterceptor;
import com.example.selabboard.interceptor.WriteCheckInterceptor;
import com.example.selabboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public WriteCheckInterceptor writeCheckInterceptor() {
//        return new WriteCheckInterceptor();
//    }
    private final BoardService boardService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/list");

        registry.addInterceptor(new WriteCheckInterceptor(boardService))
                .order(2)
                .addPathPatterns("/board/write", "/board/delete/**");
    }
}
