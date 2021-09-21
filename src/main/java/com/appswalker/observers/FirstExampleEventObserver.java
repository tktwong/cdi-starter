package com.appswalker.observers;

import com.appswalker.events.ExampleEvent;
import com.appswalker.services.TextService;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;

public class FirstExampleEventObserver {

    public String onEvent(@Observes @Priority(1) ExampleEvent event, TextService textService) {
        System.out.println("textService.parseText(event.getEventMessage()): "+textService.parseText(event.getEventMessage()));
        return textService.parseText(event.getEventMessage());
    }
}
