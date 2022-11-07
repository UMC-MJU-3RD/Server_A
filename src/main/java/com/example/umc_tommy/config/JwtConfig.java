package com.example.umc_tommy.config;

import com.example.umc_tommy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(secret);
    }
}
