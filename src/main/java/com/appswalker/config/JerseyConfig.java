package com.appswalker.config;

import com.appswalker.controller.Broadcaster;
import com.appswalker.controller.EventsResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(Broadcaster.class);
        register(EventsResource.class);
    }
}