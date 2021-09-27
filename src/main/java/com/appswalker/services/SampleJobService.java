package com.appswalker.services;

import com.appswalker.config.springevents.synchronous.CustomSpringEventPublisher;
import com.appswalker.events.ExampleEvent;
import com.appswalker.model.TickTock;
import com.appswalker.model.TickTockQualifier;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Service
public class SampleJobService {

    public static final long EXECUTION_TIME = 5000L;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicInteger count = new AtomicInteger();

    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;

    private static final Random gen = new Random();

    public void executeSampleJob() {

        logger.info("The sample job has begun...");
        try {
//            Thread.sleep(EXECUTION_TIME);
            log.info("timer triggered!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            customSpringEventPublisher.publishCustomEvent("Custom Hello World");
            customSpringEventPublisher.publishGenericAppEvent("Generic App-Level Hello World");
            customSpringEventPublisher.publishGenericEvent("Generic Hello World", true);

            SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
            try (SeContainer container = containerInitializer.initialize()) {
                container.getBeanManager().fireEvent(new ExampleEvent("Welcome to Baeldung!"));
                container.getBeanManager().fireEvent(new TickTock("tick-"+gen.nextInt(10), "tock-"+gen.nextInt(10)));
            }
//            SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
//            try (SeContainer container = containerInitializer.initialize()) {
//                container.getBeanManager().fireEvent(new ExampleEvent("Hello World!!!"));
//            }
//            exampleEvent.fireAsync(new ExampleEvent("Hello World!!!"));
//            tickTockEvent.fireAsync(new TickTock("tick-"+gen.nextInt(10), "tock-"+gen.nextInt(10)),
//                    NotificationOptions.builder().setExecutor(threadPoolTaskExecutor).build());
            log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@Fired CDI event from thread "+ Thread.currentThread().getName());
        } catch (Exception e) {
            logger.error("Error while executing sample job", e);
        } finally {
            count.incrementAndGet();
            logger.info("Sample job has finished...");
        }
    }

    public int getNumberOfInvocations() {
        return count.get();
    }
}