package com.banking.backend.Banking_Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Using BCrypt for encoding passwords
    }

    // Security Filter Chain Bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disabling CSRF for APIs (can be enabled for web applications)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/public/**").permitAll()  // Public access to user-related endpoints
                        .requestMatchers("/api/admin/**","/api/user/**").authenticated()  // Only admins can access /api/admin/**
                        .requestMatchers("/api/admim/**").hasAnyRole( "ADMIN")  // Both user and admin can access /api/user/**
                        .requestMatchers("/api/user/**").hasAnyRole("USER")
                        .anyRequest().permitAll()  // All other requests require authentication
                )
                .httpBasic(Customizer.withDefaults());  // Use Basic Authentication (could be replaced with JWT for API security)

        return http.build();
    }
}
