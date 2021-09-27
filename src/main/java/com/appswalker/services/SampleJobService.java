package com.appswalker.services;

import com.appswalker.config.springevents.synchronous.CustomSpringEventPublisher;
import com.appswalker.events.ExampleEvent;
import com.appswalker.events.TickTock;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Service
public class SampleJobService {

    public static final long EXECUTION_TIME = 5000L;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicInteger count = new AtomicInteger();

    @Autowired
    private CustomSpringEventPublisher customSpringEventPublisher;

    @Autowired
    private SseService sseService;

    private static final Random gen = new Random();

    public void executeSampleJob() {

        logger.info("The sample job has begun...");
        try {
            log.info("Thread.currentThread().getName(): "+Thread.currentThread().getName());
            customSpringEventPublisher.publishCustomEvent("CustomEvent: Hello World");
            customSpringEventPublisher.publishGenericAppEvent("GenericAppEvent: Hello World");
            customSpringEventPublisher.publishGenericEvent("GenericEvent: Hello World", true);

            SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
            try (SeContainer container = containerInitializer.initialize()) {
                container.getBeanManager().fireEvent(new ExampleEvent("Example event from spring quartz!"));
                container.getBeanManager().fireEvent(new TickTock("tick-"+gen.nextInt(10), "tock-"+gen.nextInt(10)));
            }

            sseService.getSsEmitters().forEach((SseEmitter emitter) -> {
                try {
                    emitter.send(new TickTock("tick-"+gen.nextInt(10), "tock-"+gen.nextInt(10)), MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    emitter.complete();
                    sseService.remove(emitter);
                    e.printStackTrace();
                }
            });

            log.info("The sample job has ended here...");
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