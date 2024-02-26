package com.galaxe.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableAutoConfiguration
@ComponentScan("com.galaxe.metrics")
@SpringBootApplication
@EnableScheduling
public class GxMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GxMetricsApplication.class, args);
	}
	
}
