package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({ "com", "com.controller", "com.exception", "com.model", "com.service", "com.serviceImpl",
		"com.configuration", "com.constants", "com.repository" })

@EnableJpaRepositories
@SpringBootApplication
public class SpringBootDataStructionApplication {

	public static void main(String[] args) {
				SpringApplication.run(SpringBootDataStructionApplication.class, args);
		
	}

}
