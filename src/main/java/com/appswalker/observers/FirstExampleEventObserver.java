package com.appswalker.observers;

import com.appswalker.events.ExampleEvent;
import com.appswalker.services.TextService;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;

@Log4j2
public class FirstExampleEventObserver {
    public String onEvent(@Observes @Priority(1) ExampleEvent event, TextService textService) {
        log.info("textService.parseText(event.getEventMessage()): "+textService.parseText(event.getEventMessage()));
        return textService.parseText(event.getEventMessage());
    }
}
