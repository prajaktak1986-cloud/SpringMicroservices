package com.msedcl.main.account.entity;

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
@Table(name = "accounts_details")
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int accountId;

	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "account_type", length = 50, nullable = false)
	private String accountType;

	@Column(name = "balance")
	private double accountBalance;
	
}
