package com.msedcl.main.transaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.transaction.common.ApiResponse;
import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;
import com.msedcl.main.transaction.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.transaction.dto.TransactionContactDTO;
import com.msedcl.main.transaction.service.TransactionService;
import com.msedcl.main.transaction.service.TransactionServiceImpl;

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
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Slf4j
@RestController
@RequestMapping("transactionapi")
public class TransactionController {

	private final TransactionService transactionService;
	private TransactionContactDTO transactionContactDTO;

	@Value("${build.version}")
	private String buildVersion;	
	
	

	public TransactionController(TransactionService transactionService,  TransactionContactDTO transactionContactDTO) {
		super();
		this.transactionService = transactionService;
		this.transactionContactDTO = transactionContactDTO;
	}
	
	@GetMapping("build-version")
	public ResponseEntity<String> printBuildVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@GetMapping("contact-details")
	public ResponseEntity<TransactionContactDTO> printCustomerContactDetails() {
		return ResponseEntity.status(HttpStatus.OK).body(transactionContactDTO);
	}
	
	@GetMapping("transactions/{accountID}")
	public ResponseEntity<ApiResponse<List<TransactionResponseDTO>>> 
							getAllTransactions(@PathVariable int accountID) {
		
		log.info("Request received to retrive all transaction details");
		List<TransactionResponseDTO> transactionResponseDTOList 
								= transactionService.getAllTransactionsByAccountId(accountID);
		log.info("All Transactions retrived successfully");
		transactionResponseDTOList.forEach(c -> log.info(c.toString()));

		ApiResponse<List<TransactionResponseDTO>> apiResponse 
				= new ApiResponse<List<TransactionResponseDTO>>("OK",
				"All Transactions Retrived Successfully",
				transactionResponseDTOList);

		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

//	@GetMapping("transactions/email/{email}")
//	public ResponseEntity<ApiResponse<TransactionResponseDTO>> getTransactionByEmail(@PathVariable String email) {
//		log.info("Request received to retrive transaction details");
//		log.info("Email :: " + email);
//
//		TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionByEmail(email);
//
//		log.info("Transaction details retrived successfully");
//		log.info(transactionResponseDTO.toString());
//
//		// Response
//		ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<TransactionResponseDTO>("FOUND",
//				"Transaction Details Retrived Successfully", transactionResponseDTO);
//
//		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);
//
//	}

//	@GetMapping("transactions/{transactionId}")
//	public ResponseEntity<ApiResponse<TransactionResponseDTO>> getSingleTransaction(@PathVariable int transactionId) {
//		log.info("Request received to retrive transaction details");
//		log.info("TransactionId :: " + transactionId);
//
//		TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionByTransactionId(transactionId);
//
//		log.info("Transaction details retrived successfully");
//		log.info(transactionResponseDTO.toString());
//
//		// Response
//		ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<TransactionResponseDTO>("FOUND",
//				"Transaction Details Retrived Successfully", transactionResponseDTO);
//
//		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);
//
//	}
	
//	@GetMapping("transactions/{customerId}")
//	public ResponseEntity<ApiResponse<TransactionResponseDTO>> getTransactionByCustomerID(@PathVariable int customerId) {
//		log.info("Request received to retrive customers transaction details");
//		log.info("TransactionId :: " + customerId);
//
//		//TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionByCustomerId(customerId);
//
//		log.info("Customer's Transaction/s details retrived successfully");
//		log.info(transactionResponseDTO.toString());
//
//		// Response
//		ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<TransactionResponseDTO>("FOUND",
//				"Customer's Transaction/s details retrived successfully", transactionResponseDTO);
//
//		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);
//
//	}

//	@PostMapping("transactions/transaction")
//	public ResponseEntity<ApiResponse<TransactionResponseDTO>> createTransaction(
//		@Valid	@RequestBody TransactionRequestDTO transactionRequestDTO) {
//
//		// Console
//		log.info("Request received to add new transaction");
//		log.info(transactionRequestDTO.toString());
//
//		TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(transactionRequestDTO);
//
//		// Console
//		log.info("New transaction added successfully");
//		log.info(transactionResponseDTO.toString());
//
//		// Response
//		ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<TransactionResponseDTO>("CREATED",
//				"New Transaction Created Successfully", transactionResponseDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
//	}

	@PostMapping("transactions/depositTransaction")
	public ResponseEntity<ApiResponse<TransactionResponseDTO>> depositTransactionAPI(
		@Valid	@RequestBody TransactionRequestDTO transactionRequestDTO) {

		// Console
		log.info("Request received to deposit amount.");
		log.info(transactionRequestDTO.toString());

		TransactionResponseDTO transactionResponseDTO = transactionService.depositTransactionAPI(transactionRequestDTO);

		// Console
		log.info(" Amount deposited successfully");
		log.info(transactionResponseDTO.toString());

		// Response
		ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<TransactionResponseDTO>("UPDATED",
				" Amount deposited  successfully", transactionResponseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@PostMapping("transactions/withdrawTransaction")
	public ResponseEntity<ApiResponse<TransactionResponseDTO>> withdrawTransactionAPI(
		@Valid	@RequestBody TransactionRequestDTO transactionRequestDTO) {

		// Console
		log.info("Request received to withdraw amount");
		log.info(transactionRequestDTO.toString());

		TransactionResponseDTO transactionResponseDTO = transactionService.withdrawTransactionAPI(transactionRequestDTO);

		// Console
		log.info(" Amount withdrawan successfully");
		log.info(transactionResponseDTO.toString());

		// Response
		ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<TransactionResponseDTO>("UPDATED",
				" Amount withdrawan successfully", transactionResponseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
}
