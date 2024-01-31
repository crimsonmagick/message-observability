package com.example.demo.service;

import static com.example.demo.service.MessageConfig.CONSUMER_GROUP_NAME;
import static com.example.demo.service.MessageConfig.TOPIC_NAME;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiverService {

  private static final Logger log = LogManager.getLogger(KafkaSenderService.class);

  @KafkaListener(topics = TOPIC_NAME, groupId = CONSUMER_GROUP_NAME)
  public void listen(ConsumerRecord<String, String> record) {
    final String headers = StreamSupport.stream(record.headers().spliterator(), false)
        .map(header -> header.key() + ": " + new String(header.value()))
        .collect(Collectors.joining(", ", "{", "}"));

    log.info("Received message={}, headers={}", record.value(), headers);
  }

}