package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = TemplateProjectApplication.class)
public class TemplateProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateProjectApplication.class, args);
	}

}
