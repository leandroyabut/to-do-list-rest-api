package com.leandroyabut.todolist.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    public final AppConfig appConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = appConfig.getAllowedOrigins().toArray(String[]::new);
        registry.addMapping("/to-do-lists/*")
                .allowedOrigins(allowedOrigins);
        registry.addMapping("/tasks/*")
                .allowedOrigins(allowedOrigins);
    }
}
