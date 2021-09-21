package com.appswalker;

import com.appswalker.events.ExampleEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

@SpringBootApplication
public class CdiStarterApplication {

	public static void main(String[] args) {
		SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
		try (SeContainer container = containerInitializer.initialize()) {
			container.getBeanManager().fireEvent(new ExampleEvent("Hello World!!!"));
		}
		SpringApplication.run(CdiStarterApplication.class, args);
	}

}
