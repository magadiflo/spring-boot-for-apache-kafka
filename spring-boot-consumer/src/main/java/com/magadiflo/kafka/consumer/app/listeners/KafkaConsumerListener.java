package com.magadiflo.kafka.consumer.app.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {
    private static Logger LOG = LoggerFactory.getLogger(KafkaConsumerListener.class);

    /***
     * @KafkaListener, escucharemos los mensajes que se enviarán al topic "magadiflo-topic"
     * groupId, anule la propiedad group.id de la fábrica de consumidores con este valor solo para este oyente.
     * Se admiten SpEL #{...} y marcadores de posición de propiedad ${...}.
     *
     * ¡Siempre tiene que haber un groupId!
     */
    @KafkaListener(topics = {"magadiflo-topic"}, groupId = "mi-grupo-id")
    public void listener(String message) {
        LOG.info("Mensaje recibido: {}", message);
    }
}
