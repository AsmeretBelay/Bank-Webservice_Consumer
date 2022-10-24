package com.webservices.consumer.webserviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebServiceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceConsumerApplication.class, args);
	}

}
