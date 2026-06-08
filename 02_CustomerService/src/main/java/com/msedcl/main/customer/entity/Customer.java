package com.msedcl.main.customer.entity;

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
@Table(name = "customer_details")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "mobile_number", length = 15, nullable = false)
	private String mobileNumber;
}
