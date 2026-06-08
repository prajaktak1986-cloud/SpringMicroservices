package com.msedcl.main.account.mapper;

import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.CustomerResponseDTO;
import com.msedcl.main.account.entity.Account;

public class AccountMapper {
	public static Account toEntity(AccountRequestDTO accountRequestDTO) {
		Account account = new Account();
//		account.setName(accountRequestDTO.getName());
//		account.setEmail(accountRequestDTO.getEmail());
//		account.setMobileNumber(accountRequestDTO.getMobileNumber());
		
		account.setCustomerId(accountRequestDTO.getCustomerId());
		account.setAccountType(accountRequestDTO.getAccountType());
		return account; 
	}

	public static AccountResponseDTO toDTO(Account account, CustomerResponseDTO customerResponseDTO) {
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		//accountResponseDTO.setAccountId(account.getAccountId());
		accountResponseDTO.setAccountBalance(account.getAccountBalance());
		accountResponseDTO.setAccountId(account.getAccountId());
		accountResponseDTO.setAccountType(account.getAccountType());
		accountResponseDTO.setCustomerId(account.getCustomerId());
		//accountResponseDTO.setCustomerResponseDTO();
		accountResponseDTO.setCustomerResponseDTO(customerResponseDTO);
		
//		accountResponseDTO.setName(account.getName());
//		accountResponseDTO.setEmail(account.getEmail());
//		accountResponseDTO.setMobileNumber(account.getMobileNumber());
		return accountResponseDTO;
	}
}
