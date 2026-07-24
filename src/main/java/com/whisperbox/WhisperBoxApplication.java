package com.whisperbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WhisperBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhisperBoxApplication.class, args);
    }
}