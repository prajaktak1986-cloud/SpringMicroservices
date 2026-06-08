package com.msedcl.main.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.account.common.ApiResponse;
import com.msedcl.main.account.dto.AccountContactDTO;
import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.account.service.AccountService;
import com.msedcl.main.account.service.AccountServiceImpl;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Slf4j
@RestController
@RequestMapping("accountapi")
public class AccountController {

	private final AccountService accountService;
	
	private AccountContactDTO accountContactDTO;
	
	@Value("${build.version}")
	private String buildVersion;
	
	public AccountController(AccountService accountService,AccountContactDTO accountContactDTO) {
		super();
		this.accountService = accountService;
		this.accountContactDTO = accountContactDTO;
	}

	@GetMapping("build-version")
	public ResponseEntity<String> printBuildVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@GetMapping("contact-details")
	public ResponseEntity<AccountContactDTO> printAccountContactDetails() {
		return ResponseEntity.status(HttpStatus.OK).body(accountContactDTO);
	}
	
//
//	@GetMapping("accounts")
//	public ResponseEntity<ApiResponse<List<AccountResponseDTO>>> 
//							getAllAccounts() {
//		
//		log.info("Request received to retrive all account details");
//		List<AccountResponseDTO> accountResponseDTOList 
//								= accountService.getAllAccounts();
//		log.info("All Accounts retrived successfully");
//		accountResponseDTOList.forEach(c -> log.info(c.toString()));
//
//		ApiResponse<List<AccountResponseDTO>> apiResponse 
//				= new ApiResponse<List<AccountResponseDTO>>("OK",
//				"All Accounts Retrived Successfully",
//				accountResponseDTOList);
//
//		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//
//	}

//	@GetMapping("accounts/email/{email}")
//	public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountByEmail(@PathVariable String email) {
//		log.info("Request received to retrive account details");
//		log.info("Email :: " + email);
//
//		AccountResponseDTO accountResponseDTO = accountService.getAccountByEmail(email);
//
//		log.info("Account details retrived successfully");
//		log.info(accountResponseDTO.toString());
//
//		// Response
//		ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<AccountResponseDTO>("FOUND",
//				"Account Details Retrived Successfully", accountResponseDTO);
//
//		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);
//
//	}

	@GetMapping("accounts/{accountId}")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> getSingleAccount(@PathVariable int accountId) {
		log.info("Request received to retrive account details");
		log.info("AccountId :: " + accountId);

		AccountResponseDTO accountResponseDTO = accountService.getAccountByAccountId(accountId);

		log.info("Account details retrived successfully");
		log.info(accountResponseDTO.toString());

		// Response
		ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<AccountResponseDTO>("FOUND",
				"Account Details Retrived Successfully", accountResponseDTO);

		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);

	}
	
//	@GetMapping("accounts/{customerId}")
//	public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountByCustomerID(@PathVariable int customerId) {
//		log.info("Request received to retrive customers account details");
//		log.info("AccountId :: " + customerId);
//
//		//AccountResponseDTO accountResponseDTO = accountService.getAccountByCustomerId(customerId);
//
//		log.info("Customer's Account/s details retrived successfully");
//		log.info(accountResponseDTO.toString());
//
//		// Response
//		ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<AccountResponseDTO>("FOUND",
//				"Customer's Account/s details retrived successfully", accountResponseDTO);
//
//		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);
//
//	}

	@PostMapping("accounts/account")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> createAccount(
		@Valid	@RequestBody AccountRequestDTO accountRequestDTO) {

		// Console
		log.info("Request received to add new account");
		log.info(accountRequestDTO.toString());

		AccountResponseDTO accountResponseDTO = accountService.createAccount(accountRequestDTO);

		// Console
		log.info("New account added successfully");
		log.info(accountResponseDTO.toString());

		// Response
		ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<AccountResponseDTO>("CREATED",
				"New Account Created Successfully", accountResponseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	
	@PutMapping("accounts/updateAccount")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> updateAccountBalance(
		@Valid	@RequestBody BalanceUpdateRequestDTO balanceUpdateRequestDTO) {

		// Console
		log.info("Request received to update Balance of account");
		log.info(balanceUpdateRequestDTO.toString());

		AccountResponseDTO accountResponseDTO = accountService.updateAccountBalance(balanceUpdateRequestDTO);

		// Console
		log.info(" Balance updated successfully");
		log.info(accountResponseDTO.toString());

		// Response
		ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<AccountResponseDTO>("UPDATED",
				"Balance updated successfully", accountResponseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
}
