package com.nexon.nexon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Asegúrate de que se aplique a todas las rutas
                .allowedOrigins("http://localhost:3000")  // Origen de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Todos los encabezados
                .allowCredentials(true);  // Si estás usando autenticación con cookies o credenciales
    }
}
