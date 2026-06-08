package com.msedcl.main.account.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.msedcl.main.account.common.ApiResponse;
import com.msedcl.main.account.dto.CustomerResponseDTO;
import com.msedcl.main.account.exception.CustomerNotFoundException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerRESTClient {
	private final RestTemplate restTemplate;
	
	public CustomerResponseDTO getCustomerByCustomerId(int customerId) {

		String url = "http://localhost:8080/customerapi/customers/" + customerId;
         try {
		// Calling CustomerService - getCustomerByCustomerId
		ResponseEntity<ApiResponse<CustomerResponseDTO>> response = 
				restTemplate.exchange(url,
						HttpMethod.GET, 
						null,
						// Converting JSON into Object of ApiResponse<CustomerResponseDTO>
						new ParameterizedTypeReference<ApiResponse<CustomerResponseDTO>>() {
				});

		return response.getBody().getData();
         } catch(HttpClientErrorException.NotFound e) {
        	 throw new CustomerNotFoundException("Invalid CustomerId :: " + customerId);
         }
	}
}
