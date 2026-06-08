package com.msedcl.main.customer.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "customer")
public class CustomerContactDTO {
	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;

}
