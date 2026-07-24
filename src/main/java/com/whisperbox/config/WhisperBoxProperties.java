package com.whisperbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.whisperbox.exception.InvalidUserException;

@Component
public class WhisperBoxProperties {

    @Value("${whisperbox.userA.url}")
    private String userAUrl;

    @Value("${whisperbox.userB.url}")
    private String userBUrl;

    public boolean isUserA(String key) {
        return userAUrl.equals(key);
    }

    public boolean isUserB(String key) {
        return userBUrl.equals(key);
    }

    public String sender(String userKey) {

        return switch (userKey.toUpperCase()) {
            case "A" -> "A";
            case "B" -> "B";
            default -> throw new InvalidUserException(
                    "Unknown user: " + userKey);
        };
    }

    public String receiver(String userKey) {

        return switch (userKey.toUpperCase()) {
            case "A" -> "B";
            case "B" -> "A";
            default -> throw new InvalidUserException(
                    "Unknown user: " + userKey);
        };
    }

    public void validateUser(String userKey) {

        if (!userKey.equalsIgnoreCase("A")
                && !userKey.equalsIgnoreCase("B")) {

            throw new InvalidUserException(
                    "Unknown user: " + userKey);
        }
    }
}