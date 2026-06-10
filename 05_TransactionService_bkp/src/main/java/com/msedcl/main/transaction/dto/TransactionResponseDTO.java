package com.msedcl.main.transaction.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
	
	private int transactionId;
	private AccountResponseDTO accountResponseDTO;
	private String transactionType;
	private double transactionAmount;
	private LocalDateTime transactionDate;
	
	
}
