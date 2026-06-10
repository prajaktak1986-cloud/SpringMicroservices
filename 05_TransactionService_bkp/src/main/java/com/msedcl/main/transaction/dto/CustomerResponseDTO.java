package com.msedcl.main.transaction.dto;

//import com.msedcl.main.transaction.dto.CustomerResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
	private int customerId;
	private String name;
	private String email;
	private String mobileNumber;
}

