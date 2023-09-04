package com.magadiflo.kafka.provider.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Configurando Topics
 */

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete: siempre borra el mensaje, compact: mantiene el más actual
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");// Cuánto tiempo se retendrán los mensajes. En este caso 1 día en milisegundos. Por defecto -1, que no se borrarán nunca
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");// Tamaño máximo de segmento 1GB en bytes (también es el tamaño por defecto)
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "2097152");//Tamaño máximo de cada mensaje en bytes es 2mb (1mb por defecto),

        return TopicBuilder.name("magadiflo-topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }

}
