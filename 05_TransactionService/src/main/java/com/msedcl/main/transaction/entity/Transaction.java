package com.msedcl.main.transaction.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transaction_details")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private int transactionId;
	
	@Column(name = "account_id")
	private int accountId;

	
	@Column(name = "transaction_type", length = 50, nullable = false)
	private String transactionType;

	@Column(name = "amount")
	private double transactionAmount;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	
}
