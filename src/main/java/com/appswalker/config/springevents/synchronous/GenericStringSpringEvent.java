package com.appswalker.config.springevents.synchronous;

public class GenericStringSpringEvent extends GenericSpringEvent<String> {

    GenericStringSpringEvent(final String what, final boolean success) {
        super(what, success);
    }

}
