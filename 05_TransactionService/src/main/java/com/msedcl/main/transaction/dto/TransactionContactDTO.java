package com.msedcl.main.transaction.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "transaction")
public class TransactionContactDTO {
	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;

}
