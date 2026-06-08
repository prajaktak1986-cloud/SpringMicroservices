package com.msedcl.main.transaction.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {
		
	private int accountId;
	
	private double transactionAmount;
}
