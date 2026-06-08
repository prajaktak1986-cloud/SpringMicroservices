package com.msedcl.main.transaction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.msedcl.main.transaction.audit.AuditAwareImpl;
import com.msedcl.main.transaction.client.AccountRESTClient;
import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;
import com.msedcl.main.transaction.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.transaction.dto.AccountResponseDTO;
import com.msedcl.main.transaction.entity.Transaction;
import com.msedcl.main.transaction.exception.TransactionNotFoundException;
import com.msedcl.main.transaction.exception.InsufficientBalanceException;
import com.msedcl.main.transaction.mapper.TransactionMapper;
import com.msedcl.main.transaction.repository.TransactionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	private AccountRESTClient accountRESTClient;

//	@Override
//	public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
//	AccountResponseDTO accountResponseDTO =	accountRESTClient.getAccountByAccountId(transactionRequestDTO.getAccountId());
//	log.info(accountResponseDTO.toString());
//	return null;
//	}

//	@Override
//	public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
//
//		AccountResponseDTO accountResponseDTO = accountRESTClient
//				.getAccountByAccountId(transactionRequestDTO.getAccountId());
//		
//		log.info(accountResponseDTO.toString());
//		
//Transaction transaction=TransactionMapper.toEntity(transactionRequestDTO);
//		Transaction savedtransaction=transactionRepository.save(transaction);
//		log.info("Transaction Details saved");
//		log.info(savedtransaction.toString());
//		return TransactionMapper.toDTO(savedtransaction,accountResponseDTO);
//
	//// log.info(accountResponseDTO.toString()); return accountResponseDTO;
//	}
//	
//	@Override
//	public TransactionResponseDTO getTransactionByTransactionId(int transactionId) {
//		Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
//		if (optionalTransaction.isPresent()) {
//			
//			AccountResponseDTO accountResponseDTO = accountRESTClient
//					.getAccountByAccountId(optionalTransaction.get().getAccountId());
//			log.info(accountResponseDTO.toString());
//			//TransactionResponseDTO transactionResponseDTO  = TransactionMapper.toDTO(optionalTransaction.get(),accountResponseDTO );
//			
//			//return transactionResponseDTO;
//			return TransactionMapper.toDTO(optionalTransaction.get(), accountResponseDTO);
//			
//		}
//		else
//		throw new TransactionNotFoundException("Invalid TransactionId :: " + transactionId);
//		//return null
//	}

//	@Override
//	public List<TransactionResponseDTO> getAllTransactions() {
//		List<Transaction> transactionList = transactionRepository.findAll();
//		List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
//
//		for (Transaction transaction : transactionList) {
//			transactionResponseDTOList.add(TransactionMapper.toDTO(transaction));
//		}
//		return transactionResponseDTOList;
//	}

//	@Override
//	public TransactionResponseDTO getTransactionByEmail(String email) {
//		Optional<Transaction> optionalTransaction = transactionRepository.findByEmail(email);
//		if (optionalTransaction.isPresent()) {
//			TransactionResponseDTO transactionResponseDTO = TransactionMapper.toDTO(optionalTransaction.get());
//			return transactionResponseDTO;
//		}
//		throw new TransactionNotFoundException("Invalid Email :: " + email);
//	}

	@Override
	public List<TransactionResponseDTO> getAllTransactionsByAccountId(int accountId) {
		
		log.info("getAllTransactionsByAccountId Started");
		
		AccountResponseDTO accountResponseDTO = accountRESTClient.getAccountByAccountId(accountId);
		
		log.info(accountResponseDTO.toString());
		
		List<Transaction> transactionList = transactionRepository.findByAccountId(accountId);
		
		log.info(transactionList.toString());
		
		List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();

		for (Transaction transaction : transactionList) {
			transactionResponseDTOList.add(TransactionMapper.toDTOList(transaction, accountResponseDTO));
		}

		return transactionResponseDTOList;

	}

	@Override
	public TransactionResponseDTO depositTransactionAPI(TransactionRequestDTO transactionRequestDTO) {
		// TODO Auto-generated method stub

		log.info("Deposit Transaction Started");

		BalanceUpdateRequestDTO balanceUpdateRequestDTO = new BalanceUpdateRequestDTO();
		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
		balanceUpdateRequestDTO.setTransactionType("DEPOSIT");
		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getTransactionAmount());
		

		AccountResponseDTO accountResponseDTO = accountRESTClient
				.getAccountByAccountId(balanceUpdateRequestDTO.getAccountId());

//		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
//		balanceUpdateRequestDTO.setTransactionType("DEPOSIT");
//		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getTransactionAmount());

		log.info(accountResponseDTO.toString());

		log.info("Updating Account Balance");
		Transaction transaction=TransactionMapper.toEntity(balanceUpdateRequestDTO);
		Transaction savedtransaction=transactionRepository.save(transaction);
		AccountResponseDTO accountResponseDTOOne = accountRESTClient.updateAccountBalance(balanceUpdateRequestDTO);
		log.info("Transaction Details saved");
		log.info(accountResponseDTOOne.toString());
		return TransactionMapper.toDTO(savedtransaction,accountResponseDTOOne);

//	BalanceUpdateRequestDTO balanceUpdateRequestDTO = new BalanceUpdateRequestDTO();
//	
//	AccountResponseDTO accountResponseDTO = accountRESTClient
//	.getAccountByAccountId(transactionRequestDTO.getAccountId());
//	
//	balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
//	balanceUpdateRequestDTO.setTransactionType("DEPOSIT");
//	balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getTransactionAmount());
//	
//	
//
//
//log.info(accountResponseDTO.toString());
//
//Transaction transaction=TransactionMapper.toEntity(balanceUpdateRequestDTO);
//Transaction savedtransaction=transactionRepository.save(transaction);
//log.info("Transaction Details saved");
//log.info(savedtransaction.toString());
//return TransactionMapper.toDTO(savedtransaction,accountResponseDTO);
//	//return null;
	}

	@Override
	public TransactionResponseDTO withdrawTransactionAPI(TransactionRequestDTO transactionRequestDTO) {
		log.info("WITHDRAW Transaction Started");

		BalanceUpdateRequestDTO balanceUpdateRequestDTO = new BalanceUpdateRequestDTO();
		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
		balanceUpdateRequestDTO.setTransactionType("WITHDRAW");
		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getTransactionAmount());
		

		AccountResponseDTO accountResponseDTO = accountRESTClient
				.getAccountByAccountId(balanceUpdateRequestDTO.getAccountId());

//		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
//		balanceUpdateRequestDTO.setTransactionType("DEPOSIT");
//		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getTransactionAmount());

		log.info(accountResponseDTO.toString());

		log.info("Updating Account Balance");
		Transaction transaction=TransactionMapper.toEntity(balanceUpdateRequestDTO);
		Transaction savedtransaction=transactionRepository.save(transaction);
		AccountResponseDTO accountResponseDTOOne = accountRESTClient.updateAccountBalance(balanceUpdateRequestDTO);
		log.info("Transaction Details saved");
		log.info(accountResponseDTOOne.toString());
		return TransactionMapper.toDTO(savedtransaction,accountResponseDTOOne);
		
//		BalanceUpdateRequestDTO balanceUpdateRequestDTO = new BalanceUpdateRequestDTO();
//
//		AccountResponseDTO accountResponseDTO = accountRESTClient
//				.getAccountByAccountId(balanceUpdateRequestDTO.getAccountId());
//
//		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
//		balanceUpdateRequestDTO.setTransactionType("WITHDRAW");
//		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getTransactionAmount());
//
//		log.info(accountResponseDTO.toString());
//
//Transaction transaction=TransactionMapper.toEntity(balanceUpdateRequestDTO);
//Transaction savedtransaction=transactionRepository.save(transaction);
//		AccountResponseDTO accountResponseDTOOne = accountRESTClient.updateAccountBalance(balanceUpdateRequestDTO);
//
//		log.info("Transaction Details saved");
//		log.info(accountResponseDTOOne.toString());
//		return TransactionMapper.toDTO(savedtransaction,accountResponseDTOOne);
	}

//	@Override
//	public TransactionResponseDTO updateTransactionBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO) {
//		// TODO Auto-generated method stub
//		Optional<Transaction> optionalTransaction = transactionRepository.findById(balanceUpdateRequestDTO.getAccountId());
//		
//		if (optionalTransaction.isPresent()) {
//			if(balanceUpdateRequestDTO.getTransactionType().equalsIgnoreCase("WITHDRAW")) {
//				if(optionalTransaction.get().getTransactionAmount() >= 
//						balanceUpdateRequestDTO.getAmount()) {
//					double balance = optionalTransaction.get().getTransactionAmount() - balanceUpdateRequestDTO.getAmount();
//					optionalTransaction.get().setTransactionAmount(balance);
//				} 
//				else {
//					throw new InsufficientBalanceException("Insufficient Balance.");
//				}
//			} else if(balanceUpdateRequestDTO.getTransactionType().equalsIgnoreCase("DEPOSIT")) {
//				double balance = optionalTransaction.get().getTransactionAmount() + balanceUpdateRequestDTO.getAmount();
//				optionalTransaction.get().setTransactionAmount(balance);
//			}			
//			
//		}
//		else {
//			throw new TransactionNotFoundException("Transaction not found.");
//		}
//		Transaction updateTransactionBalance = transactionRepository.save(optionalTransaction.get());
//		
//		AccountResponseDTO accountResponseDTO = accountRESTClient.getAccountByAccountId(optionalTransaction.get().getAccountId());
//		
//		return TransactionMapper.toDTO(updateTransactionBalance, accountResponseDTO);
//	}

}
