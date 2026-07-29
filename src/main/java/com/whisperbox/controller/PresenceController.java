package com.whisperbox.controller;

import com.whisperbox.dto.Presence;
import com.whisperbox.service.PresenceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presence")
public class PresenceController {

    private final PresenceService service;

    public PresenceController(
            PresenceService service) {

        this.service = service;

    }

    @PostMapping("/ping/{user}")

    public void ping(
            @PathVariable String user){

        service.ping(user);

    }

    @GetMapping("/{user}")

    public Presence get(
            @PathVariable String user){

        return service.getPresence(user);

    }

}