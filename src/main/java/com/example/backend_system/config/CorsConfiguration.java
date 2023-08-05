package com.example.backend_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
//                        .allowedMethods("OPTIONS","PUT", "GET")
//                        .exposedHeaders("Content-Type", "Authorization")
//                        .allowCredentials(true);
                //Enable CORS for the whole application
            }
        };
    }
}
