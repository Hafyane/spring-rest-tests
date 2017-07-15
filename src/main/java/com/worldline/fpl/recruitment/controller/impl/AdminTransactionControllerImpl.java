package com.worldline.fpl.recruitment.controller.impl;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.controller.AdminTransactionController;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.service.TransactionService;

@Slf4j
@RestController
public class AdminTransactionControllerImpl implements AdminTransactionController{

	
	/** The transaction service. */
	private TransactionService transactionService;

	/**
	 * Instantiates a new transaction controller impl.
	 *
	 * @param transactionService the transaction service
	 */
	@Autowired
	public AdminTransactionControllerImpl(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@Override
	public ResponseEntity<Transaction> transactionAdd(@RequestBody Transaction transaction) throws URISyntaxException {
		log.debug("REST request to save Transaction");
	    if (transaction.getId() == null) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    Transaction result = transactionService.addTransaction(transaction);
		return ResponseEntity.created(new URI("/admin/transaction/" + result.getId()))
	           .body(result);
	}

	@Override
	public ResponseEntity<Transaction> transactionUpdate(@RequestBody Transaction transaction)
			throws URISyntaxException {
		log.debug("REST request to Update Transaction");
	    if (transaction.getId() == null) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    Transaction result = transactionService.updateTransaction(transaction);
		return ResponseEntity.ok().body(result);
	}

}
