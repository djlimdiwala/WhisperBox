package com.whisperbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    public String sender(String key) {
        return isUserA(key) ? "A" : "B";
    }

    public String receiver(String key) {
        return isUserA(key) ? "B" : "A";
    }
}