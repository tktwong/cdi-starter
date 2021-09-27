package com.appswalker.observers;

import com.appswalker.events.TickTock;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;

@Log4j2
public class TickTockObserver {
    public String onEvent(@Observes @Priority(1) TickTock tickTock) {
        log.info("TickTock1: "+tickTock);
        return tickTock.toString();
    }
}
