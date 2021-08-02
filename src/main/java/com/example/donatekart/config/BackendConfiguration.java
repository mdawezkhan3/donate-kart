package com.example.donatekart.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BackendConfiguration {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
