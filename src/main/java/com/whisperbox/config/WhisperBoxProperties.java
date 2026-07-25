package com.whisperbox.config;

import com.whisperbox.exception.InvalidUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WhisperBoxProperties {

    @Value("${whisperbox.userA.url}")
    private String userAUrl;

    @Value("${whisperbox.userB.url}")
    private String userBUrl;

    public String sender(String key) {

        if (key.equalsIgnoreCase(userAUrl))
            return "A";

        if (key.equalsIgnoreCase(userBUrl))
            return "B";

        throw new InvalidUserException(
                "Unknown user: " + key);
    }

    public String receiver(String key) {

        if (key.equalsIgnoreCase(userAUrl))
            return "B";

        if (key.equalsIgnoreCase(userBUrl))
            return "A";

        throw new InvalidUserException(
                "Unknown user: " + key);
    }

    public void validateUser(String key) {

        if (!key.equalsIgnoreCase(userAUrl)
                && !key.equalsIgnoreCase(userBUrl)) {

            throw new InvalidUserException(
                    "Unknown user: " + key);
        }
    }

    public String userAUrl() {
        return userAUrl;
    }

    public String userBUrl() {
        return userBUrl;
    }
}