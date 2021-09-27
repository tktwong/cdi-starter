package com.appswalker.controller;

import com.appswalker.model.TickTock;
import com.appswalker.model.TickTockQualifier;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Controller
@Log4j2
@Path("")
public class Broadcaster {
    @Context
    private Sse sse;

    private SseBroadcaster broadcaster;

    public void register(SseEventSink eventSink, Sse sse){
        this.sse = sse;
        if (broadcaster == null) {
            broadcaster = sse.newBroadcaster();
            log.info("broadcaster created!!!!!!!!!!!!!!!!!");
        }
        broadcaster.register(eventSink);
        log.info("Registered Event sink!!!!!!!!!!!!!");
    }

    public void listener(@ObservesAsync @TickTockQualifier TickTock ticktock){
        log.info("@@@@@@@@@@@@@@@@@@@@@SSE event in thread "+ Thread.currentThread().getName());
        OutboundSseEvent sseEvent = sse.newEventBuilder().name(ticktock.getTick()).data(ticktock.getTock()).mediaType(MediaType.TEXT_PLAIN_TYPE).build();
        broadcast(sseEvent);
    }

    private void broadcast(OutboundSseEvent event){
        try {
            broadcaster.broadcast(event);
        } catch (Exception ex) {
            Logger.getLogger(Broadcaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void free(@Disposes Sse sse){
//        broadcaster.close();
//        log.info("broadcaster closed!!!!!!!!!!!!!!!!!!!!!!!!!!");
//    }
}