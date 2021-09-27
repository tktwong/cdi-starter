package com.appswalker.config.springevents.synchronous;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CustomSpringEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final String message) {
        log.info("Publishing custom event. ");
        final CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    public void publishGenericEvent(final String message, boolean success) {
        log.info("Publishing generic event.");
        final GenericSpringEvent<String> genericSpringEvent = new GenericStringSpringEvent(message, success);
        applicationEventPublisher.publishEvent(genericSpringEvent);
    }

    public void publishGenericAppEvent(final String message) {
        log.info("Publishing generic app event.");
        final GenericSpringAppEvent<String> genericSpringEvent = new GenericStringSpringAppEvent(this, message);
        applicationEventPublisher.publishEvent(genericSpringEvent);
    }

}
