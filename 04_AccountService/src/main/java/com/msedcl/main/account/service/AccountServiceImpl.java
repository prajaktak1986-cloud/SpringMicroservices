package com.msedcl.main.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.msedcl.main.account.audit.AuditAwareImpl;
import com.msedcl.main.account.client.CustomerRESTClient;
import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.account.dto.CustomerResponseDTO;
import com.msedcl.main.account.entity.Account;
import com.msedcl.main.account.exception.AccountNotFoundException;
import com.msedcl.main.account.exception.InsufficientBalanceException;
import com.msedcl.main.account.mapper.AccountMapper;
import com.msedcl.main.account.repository.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private CustomerRESTClient customerRESTClient;
	

//	@Override
//	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
//	CustomerResponseDTO customerResponseDTO =	customerRESTClient.getCustomerByCustomerId(accountRequestDTO.getCustomerId());
//	log.info(customerResponseDTO.toString());
//	return null;
//	}

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {

		CustomerResponseDTO customerResponseDTO = customerRESTClient
				.getCustomerByCustomerId(accountRequestDTO.getCustomerId());
		
		log.info(customerResponseDTO.toString());
		
Account account=AccountMapper.toEntity(accountRequestDTO);
		Account savedaccount=accountRepository.save(account);
		log.info("Account Details saved");
		log.info(savedaccount.toString());
		return AccountMapper.toDTO(savedaccount,customerResponseDTO);

//		log.info(customerResponseDTO.toString());
//		return customerResponseDTO;
	}
	
	@Override
	public AccountResponseDTO getAccountByAccountId(int accountId) {
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if (optionalAccount.isPresent()) {
			
			CustomerResponseDTO customerResponseDTO = customerRESTClient
					.getCustomerByCustomerId(optionalAccount.get().getCustomerId());
			log.info(customerResponseDTO.toString());
			//AccountResponseDTO accountResponseDTO  = AccountMapper.toDTO(optionalAccount.get(),customerResponseDTO );
			
			//return accountResponseDTO;
			return AccountMapper.toDTO(optionalAccount.get(), customerResponseDTO);
			
		}
		else
		throw new AccountNotFoundException("Invalid AccountId :: " + accountId);
		//return null
	}

//	@Override
//	public List<AccountResponseDTO> getAllAccounts() {
//		List<Account> accountList = accountRepository.findAll();
//		List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
//
//		for (Account account : accountList) {
//			accountResponseDTOList.add(AccountMapper.toDTO(account));
//		}
//		return accountResponseDTOList;
//	}

//	@Override
//	public AccountResponseDTO getAccountByEmail(String email) {
//		Optional<Account> optionalAccount = accountRepository.findByEmail(email);
//		if (optionalAccount.isPresent()) {
//			AccountResponseDTO accountResponseDTO = AccountMapper.toDTO(optionalAccount.get());
//			return accountResponseDTO;
//		}
//		throw new AccountNotFoundException("Invalid Email :: " + email);
//	}

	@Override
	public List<AccountResponseDTO> getAccountsByCustomerId(int customerId) {
		
		CustomerResponseDTO customerResponseDTO = customerRESTClient
				.getCustomerByCustomerId(customerId);
		
		List<Account> accountList = accountRepository.findByCustomerId(customerId);
		
		List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
		
		for(Account account : accountList) {
			accountResponseDTOList.add(AccountMapper.toDTO(account, customerResponseDTO));
		}
				
		return accountResponseDTOList;
		
	}
	
	
	@Override
	public AccountResponseDTO updateAccountBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO) {
		// TODO Auto-generated method stub
		Optional<Account> optionalAccount = accountRepository.findById(balanceUpdateRequestDTO.getAccountId());
		
		if (optionalAccount.isPresent()) {
			if(balanceUpdateRequestDTO.getTransactionType().equalsIgnoreCase("WITHDRAW")) {
				if(optionalAccount.get().getAccountBalance() >= 
						balanceUpdateRequestDTO.getAmount()) {
					double balance = optionalAccount.get().getAccountBalance() - balanceUpdateRequestDTO.getAmount();
					optionalAccount.get().setAccountBalance(balance);
				} 
				else {
					throw new InsufficientBalanceException("Insufficient Balance.");
				}
			} else if(balanceUpdateRequestDTO.getTransactionType().equalsIgnoreCase("DEPOSIT")) {
				double balance = optionalAccount.get().getAccountBalance() + balanceUpdateRequestDTO.getAmount();
				optionalAccount.get().setAccountBalance(balance);
			}			
			
		}
		else {
			throw new AccountNotFoundException("Account not found.");
		}
		Account updateAccountBalance = accountRepository.save(optionalAccount.get());
		
		CustomerResponseDTO customerResponseDTO = customerRESTClient.getCustomerByCustomerId(optionalAccount.get().getCustomerId());
		
		return AccountMapper.toDTO(updateAccountBalance, customerResponseDTO);
	}
	

	

}
