package com.golosovalka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration - аннотация, которая указывает, что это класс конфигурации Spring
//public class WebMvcConfig implements WebMvcConfigurer - класс, который настраивает конфигурацию Spring MVC
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //private final long MAX_AGE_SECS = 3600; - константа, которая устанавливает максимальное время жизни кэша CORS в секундах
    private final long MAX_AGE_SECS = 3600;
    //@Override - аннотация, которая указывает, что метод переопределяет метод из родительского класса или интерфейса
    //public void addCorsMappings(CorsRegistry registry) - метод, который добавляет настройки CORS для заданного реестра
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/**") - добавляет маппинг для всех URL
        //.allowedOrigins("*") - разрешает доступ со всех источников
        //.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE") - разрешает использование указанных HTTP-методов
        //.maxAge(MAX_AGE_SECS); - устанавливает максимальное время жизни кэша CORS в секундах
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
