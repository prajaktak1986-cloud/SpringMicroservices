package com.msedcl.main.transaction.exception;

public class TransactionNotFoundException extends RuntimeException {
	public TransactionNotFoundException(String message) {
		super(message);
	}

}
