package com.example.microservices.client;

import com.example.microservices.service.RandomWordServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Интерфейс Feign клиента
@FeignClient(name = "second-application", url = "http://localhost:8092")
public interface SecondApplicationClient {

    @PostMapping("/saveData")
    void saveData(@RequestBody String word);
}

