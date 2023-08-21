package com.example.microservices.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomWordServiceImpl implements RandomWordService {

    private static final String[] WORDS = {"apple", "banana", "cherry", "grape", "orange"};

    @Override
    public String generateRandomWord() {
        Random random = new Random();
        int index = random.nextInt(WORDS.length);
        return WORDS[index];
    }
}
