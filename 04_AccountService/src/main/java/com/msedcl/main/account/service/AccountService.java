package com.msedcl.main.account.service;

import java.util.List;

import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.BalanceUpdateRequestDTO;

public interface AccountService {
	AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
	AccountResponseDTO getAccountByAccountId(int accountId);
	List<AccountResponseDTO> getAccountsByCustomerId(int customerId);
	
	AccountResponseDTO updateAccountBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO);

	//AccountResponseDTO getAccountById(Integer accountId);

	//AccountResponseDTO getAccountByCustomerId(int customerId);
		//List<AccountResponseDTO> getAllAccounts();

//	AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
//
//	AccountResponseDTO getAccountById(Integer accountId);
//
//	List<AccountResponseDTO> getAccountsByCustomer(Integer customerId);
//
//	AccountResponseDTO updateAccountBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO);
}
