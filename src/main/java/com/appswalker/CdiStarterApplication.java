package com.appswalker;

import com.appswalker.events.ExampleEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

@EnableScheduling
@SpringBootApplication
public class CdiStarterApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		new CdiStarterApplication().configure(new SpringApplicationBuilder(CdiStarterApplication.class)).run(args);
	}
}
