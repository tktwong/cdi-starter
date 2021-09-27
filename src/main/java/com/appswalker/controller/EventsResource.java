package com.appswalker.controller;

import com.appswalker.services.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController()
@RequestMapping("events")
public class EventsResource {

    @Autowired
    private SseService sseService;

    @GetMapping("subscription")
    public SseEmitter subscribe() throws IOException {

        SseEmitter emitter = new SseEmitter();

        sseService.add(emitter);
        emitter.onCompletion(() -> sseService.remove(emitter));

        return emitter;
    }
}