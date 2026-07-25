package com.whisperbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MessageService service;

    @Test
    void shouldSendMessage() throws Exception {

        SendMessageRequest request =
                new SendMessageRequest();

        request.setMessage("Hello");

        mvc.perform(

                post("/messages/A")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(mapper.writeValueAsString(request))

        )

        .andExpect(status().isOk());

    }

}