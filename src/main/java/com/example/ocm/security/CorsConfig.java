
package com.example.ocm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        // ✅ Frontend origins
        config.setAllowedOrigins(List.of(
            "https://thebestonlinecourse.netlify.app",
            "http://localhost:4200"
        ));

        // ✅ Methods
        config.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        // ✅ Headers
        config.setAllowedHeaders(List.of("*"));

        // ✅ Expose JWT
        config.setExposedHeaders(List.of("Authorization"));

        // ❌ JWT uses headers, not cookies
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
