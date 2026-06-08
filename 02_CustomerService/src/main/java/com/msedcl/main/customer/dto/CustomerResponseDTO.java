package com.msedcl.main.customer.dto;

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
