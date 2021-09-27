package com.appswalker.config;

import com.appswalker.controller.Broadcaster;
import com.appswalker.controller.EventsResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(Broadcaster.class);
        register(EventsResource.class);
    }
}