package com.whisperbox.controller;

import com.whisperbox.dto.Presence;
import com.whisperbox.service.PresenceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presence")
public class PresenceController {

    private final PresenceService service;

    public PresenceController(PresenceService service) {
        this.service = service;
    }

    @GetMapping("/{user}")
    public Presence presence(@PathVariable String user) {
        return service.getPresence(user.toUpperCase());
    }
}