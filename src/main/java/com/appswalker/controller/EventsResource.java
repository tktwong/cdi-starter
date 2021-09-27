package com.appswalker.controller;

import org.springframework.stereotype.Controller;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Controller
@Path("events")
public class EventsResource {

    @Inject
    private Broadcaster broadcaster;

    //registeration
    @Path("subscribe")
    @GET
    @Produces("text/event-stream")
    public void subscribe(@Context SseEventSink sseEventSink, @Context Sse sse) {
        broadcaster.register(sseEventSink, sse);
    }

}