package com.whisperbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    @GetMapping("/blue")
    public String blue() {
        return "forward:/chat.html";
    }

    @GetMapping("/moon")
    public String moon() {
        return "forward:/chat.html";
    }
}