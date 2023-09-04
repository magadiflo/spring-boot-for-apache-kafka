package com.magadiflo.kafka.provider.app.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configurando el cliente de Apache Kafka,
 * quien se encargará de enviar los mensajes
 */
@Configuration
public class KafkaProviderConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Configurando proveedor de mensajes de Kafka
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //Quién va a serializar la llave: pues StringSerializer
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//Quién va a serializar el objeto: pues StringSerializer
        return properties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(this.producerConfig());
    }

    /**
     * Aplicamos inyección de dependencia vía parámetro del método. El objeto a inyectar es el @Bean que definimos
     * en la parte superior ProducerFactory<String, String> producerFactory(). Lo podríamos haber realizado
     * directamente así: return new KafkaTemplate<>(this.producerFactory()), pero no sería lo apropiado, ya que el
     * método producerFactory() lo estamos definiendo como un @Bean
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
