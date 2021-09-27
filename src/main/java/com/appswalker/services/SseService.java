package com.appswalker.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
@Service
public class SseService {
    private final List<SseEmitter> emitters = new ArrayList<SseEmitter>();

    public boolean add(SseEmitter sseEmitter) {
        return this.emitters.add(sseEmitter);
    }

    public boolean remove(SseEmitter sseEmitter) {
        return this.emitters.remove(sseEmitter);
    }

    public List<SseEmitter> getSsEmitters() {
        return this.emitters;
    }
}


