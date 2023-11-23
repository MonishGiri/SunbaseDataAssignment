package com.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.App.controller,com.app.service,com.App.config")
public class SunbaseDataAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunbaseDataAssignmentApplication.class, args);
	}

}
