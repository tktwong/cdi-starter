package com.appswalker.observers;

import com.appswalker.events.ExampleEvent;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;

public class SecondExampleEventObserver {

    public String onEvent(@Observes @Priority(2) ExampleEvent event) {
        System.out.println("event.getEventMessage(): "+event.getEventMessage());
        return event.getEventMessage();
    }
}
