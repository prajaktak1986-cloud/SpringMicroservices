package com.msedcl.main.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msedcl.main.customer.entity.Customer;

public interface CustomerRepository 
				extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByEmail(String email);
	

}
