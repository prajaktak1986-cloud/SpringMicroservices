package com.msedcl.main.customer.mapper;

import com.msedcl.main.customer.dto.CustomerRequestDTO;
import com.msedcl.main.customer.dto.CustomerResponseDTO;
import com.msedcl.main.customer.entity.Customer;

public class CustomerMapper {
	public static Customer toEntity(CustomerRequestDTO customerRequestDTO) {
		Customer customer = new Customer();
		customer.setName(customerRequestDTO.getName());
		customer.setEmail(customerRequestDTO.getEmail());
		customer.setMobileNumber(customerRequestDTO.getMobileNumber());
		return customer;
	}

	public static CustomerResponseDTO toDTO(Customer customer) {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerId(customer.getCustomerId());
		customerResponseDTO.setName(customer.getName());
		customerResponseDTO.setEmail(customer.getEmail());
		customerResponseDTO.setMobileNumber(customer.getMobileNumber());
		return customerResponseDTO;
	}
}
