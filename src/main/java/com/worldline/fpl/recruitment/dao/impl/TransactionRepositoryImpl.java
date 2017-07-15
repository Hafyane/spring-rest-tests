package com.worldline.fpl.recruitment.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;

/**
 * Implementation of {@link TransactionRepository}
 * 
 * @author A525125
 *
 */
@Slf4j
@Repository
public class TransactionRepositoryImpl implements TransactionRepository,
		InitializingBean {

	private List<Transaction> transactions;

	@Override
	public void afterPropertiesSet() throws Exception {
		transactions = new ArrayList<>();
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(42.12));
			transaction.setId("1");
			transaction.setNumber("12151885120");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(456.00));
			transaction.setId("2");
			transaction.setNumber("12151885121");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(-12.12));
			transaction.setId("3");
			transaction.setNumber("12151885122");
			transactions.add(transaction);
		}
	}

	@Override
	public Transaction findById(String id) {
		return transactions.stream()
				.filter(transaction -> transaction.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Page<Transaction> getTransactionsByAccount(String accountId,
			Pageable p) {
		return new PageImpl<Transaction>(transactions.stream()
				.filter(t -> t.getAccountId().equals(accountId))
				.collect(Collectors.toList()));
	}

	@Override
	public void deleteTransactionById(String accountId,String transactionId) {
		log.debug("Repositorie Begin request to delete Transaction : {}", transactionId);
		transactions.stream().filter(a -> a.getAccountId().equals(accountId) && a.getId().equals(transactionId)).findFirst()
			.map(trans -> {transactions.remove(trans); return trans;})
			.orElseThrow(() -> new ServiceException(ErrorCode.NOT_FOUND_TRANSACTION,
        					"Transaction doesn't exist"));
		log.debug("Repositorie End request to delete Transaction : {}", transactionId);


	}

	@Override
	public Transaction addTransaction(Transaction transaction) {
		log.debug("Repositorie request to Add Transaction : {}", transaction.getId());
		transactions.add(transaction);
		return transaction;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		log.debug("Repositorie request to Update Transaction : {}", transaction.getId());
		transactions.stream().filter(a -> a.getId().equals(transaction.getId())).findFirst()
		.map(trans -> {transactions.remove(trans); transactions.add(transaction); return transaction;})
		.orElseThrow(() -> new ServiceException(ErrorCode.NOT_FOUND_TRANSACTION,
    					"Transaction doesn't exist"));
		return transaction;
	}

}
