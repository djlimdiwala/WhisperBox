package com.whisperbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "WhisperBox API",
        version = "1.0",
        description = "A secure backend messaging service built using Spring Boot and PostgreSQL.",
        contact = @Contact(
            name = "Dhaval Limdiwala",
            email = "your-email@example.com"
        ),
        license = @License(
            name = "MIT"
        )
    )
)
@SpringBootApplication
@EnableScheduling
public class WhisperBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhisperBoxApplication.class, args);
    }
}