package com.example.microservices.controller;

import com.example.microservices.client.DataSenderService;
import com.example.microservices.service.RandomWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomWordController {


    private final RandomWordService randomWordService;

    private final DataSenderService dataSenderService;

    public RandomWordController(RandomWordService randomWordService, DataSenderService dataSenderService) {
        this.dataSenderService = dataSenderService;
        this.randomWordService = randomWordService;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateAndSendRandomWord() {
        String randomWord = randomWordService.generateRandomWord();
        //вынести в сервис
        dataSenderService.sendData(randomWord);
        return ResponseEntity.ok("Random word generated and sent to Kafka: " + randomWord);
    }
}
