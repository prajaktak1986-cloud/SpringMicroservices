package com.msedcl.main.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msedcl.main.account.entity.Account;

public interface AccountRepository 
				extends JpaRepository<Account, Integer> {
	//Optional<Account> findByEmail(String email);
List<Account> findByCustomerId(int customerId);
}
