package com.appswalker.config.springevents.synchronous;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(final CustomSpringEvent event) {
        log.info("Received spring custom event - " + event.getMessage());
    }

}