package com.example.labb.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class IsbnService {
    public String generateIsbn() {
        try {
            Thread.sleep(3000); // external service, uncommitted SLA
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "978-" + ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L);
    }
}