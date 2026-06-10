package com.msedcl.main.transaction.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.msedcl.main.transaction.common.ApiResponse;
import com.msedcl.main.transaction.dto.AccountResponseDTO;
import com.msedcl.main.transaction.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.transaction.exception.AccountNotFoundException;
import com.msedcl.main.transaction.exception.InsufficientBalanceException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AccountRESTClient {
	private final RestTemplate restTemplate;

	public AccountResponseDTO getAccountByAccountId(int accountId) {

		log.info("Retriveing Account Details From AccountService");
		String url = "http://localhost:8181/accountapi/accounts/" + accountId;
		try {
			// Calling AccountService - getAccountByAccountId
			ResponseEntity<ApiResponse<AccountResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null,
					// Converting JSON into Object of ApiResponse<AccountResponseDTO>
					new ParameterizedTypeReference<ApiResponse<AccountResponseDTO>>() {
					});

			log.info("Response Received");
			log.info(response.getBody().getData().toString());
			return response.getBody().getData();
		} catch (HttpClientErrorException.NotFound e) {
			throw new AccountNotFoundException("Invalid AccountId :: " + accountId);
		}
	}

	public AccountResponseDTO updateAccountBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO) {

		log.info("BalanceUpdateRequestDTO");
		log.info(balanceUpdateRequestDTO.toString());

		String url = "http://localhost:8181/accountapi/accounts/updateAccount";
		// BalanceUpdateRequestDTO balanceUpdateRequestDTO = new
		// BalanceUpdateRequestDTO();
		HttpEntity<BalanceUpdateRequestDTO> entity = new HttpEntity<>(balanceUpdateRequestDTO);
		try {
			// Calling AccountService - getAccountByAccountId
			ResponseEntity<ApiResponse<AccountResponseDTO>> response = restTemplate.exchange(url, HttpMethod.PUT,
					entity,
					// Converting JSON into Object of ApiResponse<AccountResponseDTO>
					new ParameterizedTypeReference<ApiResponse<AccountResponseDTO>>() {
					});

			return response.getBody().getData();
		} catch (HttpClientErrorException.NotFound e) {
			throw new InsufficientBalanceException("Insufficent Balance :: ");
		}
	}
}
