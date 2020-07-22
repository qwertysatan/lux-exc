package com.luxoft.vuvarov.example.exceptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.luxoft.vuvarov.example.exceptions")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
