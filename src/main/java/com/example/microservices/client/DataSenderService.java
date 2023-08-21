package com.example.microservices.client;

import com.example.microservices.config.KafkaConfig;
import com.example.microservices.service.RandomWordServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


// Класс для отправки данных через Feign клиент или Kafka
@Service
public class DataSenderService {

    @Value("${data.sending.method}")
    private String sendingMethod;


    private final SecondApplicationClient secondApplicationClient;


    private final KafkaConfig kafkaConfig;


    public DataSenderService(SecondApplicationClient secondApplicationClient, KafkaConfig kafkaConfig) {
        this.secondApplicationClient = secondApplicationClient;
        this.kafkaConfig = kafkaConfig;
    }

    public void sendData(String word) {
        if (sendingMethod.equals("feign")) {
            secondApplicationClient.saveData(word);
        } else if (sendingMethod.equals("kafka")) {
            kafkaConfig.send("my_topic", word);
        }
    }
}
