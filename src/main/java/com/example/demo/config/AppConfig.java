package com.example.demo.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableWebSecurity
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        // Support all JSON content types with different charset variations
        jsonConverter.setSupportedMediaTypes(List.of(
            MediaType.APPLICATION_JSON,
            MediaType.valueOf("application/json;charset=UTF-8"),
            MediaType.valueOf("application/json;charset=utf-8"),
            MediaType.valueOf("application/json; charset=UTF-8"),
            MediaType.valueOf("application/json; charset=utf-8"),
            MediaType.valueOf("text/json"),
            MediaType.valueOf("text/plain")
        ));

        converters.add(0, jsonConverter);
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.sessionManagement(management-> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.authorizeHttpRequests(Authorize -> Authorize
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .requestMatchers("/api/signup", "/api/login", "/auth/**").permitAll()
        .requestMatchers("/api/**").authenticated()
        .anyRequest().permitAll())
        .addFilterBefore(new com.example.demo.config.jwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .cors(cors-> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
    // Use patterns to cover preview deployments and ports
    configuration.setAllowedOriginPatterns(Arrays.asList(
        "http://localhost:*",
        "https://*.vercel.app",
        "https://veri-med.vercel.app"
    ));

    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
    configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin"));
    configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}