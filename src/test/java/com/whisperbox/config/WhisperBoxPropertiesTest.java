package com.whisperbox.config;

import com.whisperbox.exception.InvalidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class WhisperBoxPropertiesTest {

    private WhisperBoxProperties properties;

    @BeforeEach
    void setUp() {

        properties = new WhisperBoxProperties();

        ReflectionTestUtils.setField(
                properties,
                "userAUrl",
                "blue"
        );

        ReflectionTestUtils.setField(
                properties,
                "userBUrl",
                "moon"
        );
    }

    @Test
    void shouldReturnSender() {

        assertEquals("A", properties.sender("blue"));
        assertEquals("B", properties.sender("moon"));

    }

    @Test
    void shouldReturnReceiver() {

        assertEquals("B", properties.receiver("blue"));
        assertEquals("A", properties.receiver("moon"));

    }

    @Test
    void shouldThrowExceptionForInvalidUser() {

        assertThrows(
                InvalidUserException.class,
                () -> properties.sender("xyz")
        );

    }

}