package com.whisperbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                FlywayAutoConfiguration.class
        }
)
public class WhisperBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhisperBoxApplication.class, args);
    }
}