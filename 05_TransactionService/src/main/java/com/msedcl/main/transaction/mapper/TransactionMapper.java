package com.msedcl.main.transaction.mapper;

import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;

import java.time.LocalDateTime;

import com.msedcl.main.transaction.dto.AccountResponseDTO;
import com.msedcl.main.transaction.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.transaction.dto.CustomerResponseDTO;
import com.msedcl.main.transaction.entity.Transaction;

public class TransactionMapper {
//	public static Transaction toEntity(TransactionRequestDTO transactionRequestDTO) {
//		Transaction transaction = new Transaction();
////		transaction.setName(transactionRequestDTO.getName());
////		transaction.setEmail(transactionRequestDTO.getEmail());
////		transaction.setMobileNumber(transactionRequestDTO.getMobileNumber());
////		
////		transaction.setCustomerId(transactionRequestDTO.getCustomerId());
////		transaction.setTransactionType(transactionRequestDTO.getTransactionType());
//		
//		transaction.setAccountId(transactionRequestDTO.getAccountId());
//		transaction.setTransactionAmount(transactionRequestDTO.getTransactionAmount());
//		return transaction; 
//	}
	
	public static Transaction toEntity(BalanceUpdateRequestDTO balanceUpdateRequestDTO) {
		Transaction transaction = new Transaction();
//		transaction.setName(transactionRequestDTO.getName());
//		transaction.setEmail(transactionRequestDTO.getEmail());
//		transaction.setMobileNumber(transactionRequestDTO.getMobileNumber());
//		
//		transaction.setCustomerId(transactionRequestDTO.getCustomerId());
//		transaction.setTransactionType(transactionRequestDTO.getTransactionType());
		
		transaction.setAccountId(balanceUpdateRequestDTO.getAccountId());
		transaction.setTransactionAmount(balanceUpdateRequestDTO.getAmount());
		transaction.setTransactionType(balanceUpdateRequestDTO.getTransactionType());
		transaction.setTransactionDate(LocalDateTime.now());
		return transaction; 
	}
	
	public static TransactionResponseDTO toDTO(Transaction transaction,AccountResponseDTO accountResponseDTO) {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		//transactionResponseDTO.setTransactionId(transaction.getTransactionId());
		transactionResponseDTO.setTransactionAmount(accountResponseDTO.getAccountBalance());
		transactionResponseDTO.setTransactionId(transaction.getTransactionId());
		transactionResponseDTO.setTransactionType(transaction.getTransactionType());
		transactionResponseDTO.setTransactionDate(transaction.getTransactionDate());
		transactionResponseDTO.setAccountResponseDTO(accountResponseDTO);
		//transactionResponseDTO.getAccountResponseDTO().setAccountId(accountResponseDTO.getAccountId());
		//transactionResponseDTO.setCustomerResponseDTO();
		//transactionResponseDTO.setAccountResponseDTO(accountResponseDTO);
		
//		transactionResponseDTO.setName(transaction.getName());
//		transactionResponseDTO.setEmail(transaction.getEmail());
//		transactionResponseDTO.setMobileNumber(transaction.getMobileNumber());
		return transactionResponseDTO;
	}

	public static TransactionResponseDTO toDTOList(Transaction transaction, AccountResponseDTO accountResponseDTO) {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		//transactionResponseDTO.setTransactionId(transaction.getTransactionId());
		transactionResponseDTO.setTransactionAmount(transaction.getTransactionAmount());
		transactionResponseDTO.setTransactionId(transaction.getTransactionId());
		transactionResponseDTO.setTransactionType(transaction.getTransactionType());
		//transactionResponseDTO.getAccountResponseDTO().setAccountId(transaction.getAccountId());
		//transactionResponseDTO.setCustomerResponseDTO();
		transactionResponseDTO.setAccountResponseDTO(accountResponseDTO);
		
//		transactionResponseDTO.setName(transaction.getName());
//		transactionResponseDTO.setEmail(transaction.getEmail());
//		transactionResponseDTO.setMobileNumber(transaction.getMobileNumber());
		return transactionResponseDTO;
	}
}
