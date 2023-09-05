package com.magadiflo.kafka.provider.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MainProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainProviderApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("magadiflo-topic", "Soy el proveedor, este es el segundo mensaje. Prueba Final.");
        };
    }

}