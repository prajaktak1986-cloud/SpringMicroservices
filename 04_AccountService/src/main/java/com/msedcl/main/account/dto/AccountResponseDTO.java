package com.msedcl.main.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
	

	private int accountId;	
	private int customerId;
	private String accountType;
	private CustomerResponseDTO customerResponseDTO;
	private double accountBalance;
	
	
}
