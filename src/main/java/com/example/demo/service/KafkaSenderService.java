package com.example.demo.service;

import static com.example.demo.service.MessageConfig.TOPIC_NAME;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderService {
  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaSenderService(final KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(final String message) {
    kafkaTemplate.send(TOPIC_NAME, message);
  }
}
