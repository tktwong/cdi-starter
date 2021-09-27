package com.appswalker.observers;

import com.appswalker.events.ExampleEvent;
import com.appswalker.events.TickTock;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;

@Log4j2
public class SecondExampleEventObserver {

    public String onEvent(@Observes @Priority(2) ExampleEvent event) {
        log.info("event.getEventMessage(): "+event.getEventMessage());
        return event.getEventMessage();
    }

    public String onEvent(@Observes @Priority(2) TickTock tickTock) {
        log.info("TickTock2: "+tickTock);
        return tickTock.toString();
    }
}
