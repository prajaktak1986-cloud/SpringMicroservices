package com.msedcl.main.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
