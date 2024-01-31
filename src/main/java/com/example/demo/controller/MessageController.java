package com.example.demo.controller;

import com.example.demo.service.KafkaSenderService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "messages", consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class MessageController {

  private final ObservationRegistry observationRegistry;
  private final KafkaSenderService kafkaSenderService;

  public MessageController(final ObservationRegistry observationRegistry,
      final KafkaSenderService kafkaSenderService) {
    this.observationRegistry = observationRegistry;
    this.kafkaSenderService = kafkaSenderService;
  }

  @GetMapping
  ResponseEntity<String> hello() {
    return ResponseEntity.ok("world");
  }

  @PostMapping
  ResponseEntity<Void> send(@RequestBody final MessageDto dto,
      @RequestHeader(value = "messageSource", required = false) final String messageSource) {
    Observation.createNotStarted("sendMessage", observationRegistry)
        .observe(() -> kafkaSenderService.sendMessage(dto.message()));
    return ResponseEntity.ok().build();
  }

}