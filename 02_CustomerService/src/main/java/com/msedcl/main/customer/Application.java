package com.msedcl.main.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Customer microservices REST API Documentation",
				description = "MSEDCL Bank Customer microservices REST API Documentation",
				version = "V1",
				contact =@Contact(name = "Prajakta Teni", email = "apmumbai22@mahadiscom.in", url = "https://www.mahadiscom.in/")
				)
		)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
