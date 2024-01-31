package com.example.demo;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@SpringBootApplication
public class DemoApplication {

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(
      final ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }


  public static void main(final String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
