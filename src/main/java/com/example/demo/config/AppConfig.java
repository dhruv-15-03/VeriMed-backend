package com.example.demo.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
<<<<<<< HEAD
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
=======
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
>>>>>>> 39e49ea7434b000203a410179f8936790fec0a59

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
<<<<<<< HEAD
        http.authorizeHttpRequests(Authorize -> Authorize
                .requestMatchers("/api/signup", "/api/login", "/auth/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
                .addFilterBefore(new com.example.demo.config.jwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
=======
        http.authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
                .csrf().disable()
>>>>>>> 39e49ea7434b000203a410179f8936790fec0a59
                .cors(cors-> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
<<<<<<< HEAD
                "http://localhost:8081",
                "http://localhost:5000",
                "http://localhost:3000",
                "http://localhost:5173",
                "https://veri-med.vercel.app"
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
=======
                "http://localhost:5173",
                "http://localhost:3000",
                "https://dhr-social.vercel.app"
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
>>>>>>> 39e49ea7434b000203a410179f8936790fec0a59
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

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Set timeouts to prevent hanging during keep-alive pings
        return restTemplate;
    }
}
