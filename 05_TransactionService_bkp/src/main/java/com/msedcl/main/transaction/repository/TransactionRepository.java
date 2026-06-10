package com.msedcl.main.transaction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msedcl.main.transaction.entity.Transaction;

public interface TransactionRepository 
				extends JpaRepository<Transaction, Integer> {
	//Optional<Transaction> findByEmail(String email);
List<Transaction> findByAccountId(int accountId);
}
