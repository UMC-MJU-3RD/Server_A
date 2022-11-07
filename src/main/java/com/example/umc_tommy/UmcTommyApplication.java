package com.example.umc_tommy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UmcTommyApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmcTommyApplication.class, args);
    }

}
