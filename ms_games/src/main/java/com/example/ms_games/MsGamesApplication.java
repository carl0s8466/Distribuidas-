package com.example.ms_games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MsGamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGamesApplication.class, args);
    }

    @Bean
    @LoadBalanced // Agrega esta anotación
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
