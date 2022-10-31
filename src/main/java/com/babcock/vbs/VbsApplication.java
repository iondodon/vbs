package com.babcock.vbs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class VbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VbsApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void serviceSetup() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
