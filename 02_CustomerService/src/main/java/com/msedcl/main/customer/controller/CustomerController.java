package com.msedcl.main.customer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.customer.common.ApiResponse;
import com.msedcl.main.customer.dto.CustomerContactDTO;
import com.msedcl.main.customer.dto.CustomerRequestDTO;
import com.msedcl.main.customer.dto.CustomerResponseDTO;
import com.msedcl.main.customer.service.CustomerService;
import com.msedcl.main.customer.service.CustomerServiceImpl;
import com.msedcl.main.util.SpringUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("customerapi")
@Tag(
		name = "CRUD REST APIs for Customers in MSEDCL Bank ",
		description = "CRUD REST APIs to CREATE, FETCH ALL, FETCH ONE customer details"
) 
public class CustomerController {

	private final CustomerService customerService;
	
	@Value("${build.version}")
	private String buildVersion;
	
	private CustomerContactDTO customerContactDTO;
	
	public CustomerController(CustomerService customerService, CustomerContactDTO customerContactDTO) {
		super();
		this.customerService = customerService;
		this.customerContactDTO = customerContactDTO;
	}

	@GetMapping("build-version")
	public ResponseEntity<String> printBuildVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}

	@GetMapping("contact-details")
	public ResponseEntity<CustomerContactDTO> printCustomerContactDetails() {
		return ResponseEntity.status(HttpStatus.OK).body(customerContactDTO);
	}
	
	@Operation(
			summary = "FETCH all customers",
			description = "REST API to FETCH customer details from database"
			)
	@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "200",
			description = "HTTP Status OK"
			)
	@GetMapping("customers")
	public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> 
							getAllCustomers() {
		
		log.info("Request received to retrive all customer details");
		List<CustomerResponseDTO> customerResponseDTOList 
								= customerService.getAllCustomers();
		log.info("All Customers retrived successfully");
		customerResponseDTOList.forEach(c -> log.info(c.toString()));

		ApiResponse<List<CustomerResponseDTO>> apiResponse 
				= new ApiResponse<List<CustomerResponseDTO>>("OK",
				"All Customers Retrived Successfully",
				customerResponseDTOList);

		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@GetMapping("customers/email/{email}")
	public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerByEmail(@PathVariable String email) {
		log.info("Request received to retrive customer details");
		log.info("Email :: " + email);

		CustomerResponseDTO customerResponseDTO = customerService.getCustomerByEmail(email);

		log.info("Customer details retrived successfully");
		log.info(customerResponseDTO.toString());

		// Response
		ApiResponse<CustomerResponseDTO> apiResponse = new ApiResponse<CustomerResponseDTO>("FOUND",
				"Customer Details Retrived Successfully", customerResponseDTO);

		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);

	}

	@GetMapping("customers/{customerId}")
	public ResponseEntity<ApiResponse<CustomerResponseDTO>> getSingleCustomer(@PathVariable int customerId) {
		log.info("Request received to retrive customer details");
		log.info("CustomerId :: " + customerId);

		CustomerResponseDTO customerResponseDTO = customerService.getCustomerByCustomerId(customerId);

		log.info("Customer details retrived successfully");
		log.info(customerResponseDTO.toString());

		// Response
		ApiResponse<CustomerResponseDTO> apiResponse = new ApiResponse<CustomerResponseDTO>("FOUND",
				"Customer Details Retrived Successfully", customerResponseDTO);

		return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);

	}

	@PostMapping("customers/customer")
	public ResponseEntity<ApiResponse<CustomerResponseDTO>> addNewCustomer(
		@Valid	@RequestBody CustomerRequestDTO customerRequestDTO) {

		// Console
		log.info("Request received to add new customer");
		log.info(customerRequestDTO.toString());

		CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customerRequestDTO);

		// Console
		log.info("New customer added successfully");
		log.info(customerResponseDTO.toString());

		// Response
		ApiResponse<CustomerResponseDTO> apiResponse = new ApiResponse<CustomerResponseDTO>("CREATED",
				"New Customer Created Successfully", customerResponseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@GetMapping("maventesting/{msg}")
	public static String getMessageUpperCase(@PathVariable String msg) {
		return SpringUtil.getUppcaseText(msg);
		//return msg;
		
	}

}
