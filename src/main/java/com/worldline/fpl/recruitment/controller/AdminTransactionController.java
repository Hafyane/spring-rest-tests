package com.worldline.fpl.recruitment.controller;

import java.net.URISyntaxException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.entity.Transaction;

@RequestMapping(value = "/admin/transaction", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
public interface AdminTransactionController {

	
	/**
	 * Transaction add.
	 *
	 * @param transaction the transaction
	 * @return the response entity
	 * @throws URISyntaxException 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	ResponseEntity<Transaction> transactionAdd(@RequestBody Transaction transaction) throws URISyntaxException;
	
	/**
	 * Transaction update.
	 *
	 * @param transaction the transaction
	 * @return the response entity
	 * @throws URISyntaxException the URI syntax exception
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	ResponseEntity<Transaction> transactionUpdate(@RequestBody Transaction transaction) throws URISyntaxException;
	
	
}
