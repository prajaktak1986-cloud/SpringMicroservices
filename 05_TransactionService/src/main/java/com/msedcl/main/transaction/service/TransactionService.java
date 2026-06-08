package com.msedcl.main.transaction.service;

import java.util.List;

import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;
import com.msedcl.main.transaction.dto.BalanceUpdateRequestDTO;

public interface TransactionService {
	//TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);
	//TransactionResponseDTO getTransactionByTransactionId(int transactionId);
	List<TransactionResponseDTO> getAllTransactionsByAccountId(int accountId);
	
	//TransactionResponseDTO updateTransactionBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO);

	TransactionResponseDTO depositTransactionAPI(TransactionRequestDTO transactionRequestDTO);
	TransactionResponseDTO withdrawTransactionAPI(TransactionRequestDTO transactionRequestDTO);
	
	//TransactionResponseDTO getTransactionById(Integer transactionId);

	//TransactionResponseDTO getTransactionByCustomerId(int customerId);
		//List<TransactionResponseDTO> getAllTransactions();

//	TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);
//
//	TransactionResponseDTO getTransactionById(Integer transactionId);
//
//	List<TransactionResponseDTO> getTransactionsByCustomer(Integer customerId);
//
//	TransactionResponseDTO updateTransactionBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO);
}
