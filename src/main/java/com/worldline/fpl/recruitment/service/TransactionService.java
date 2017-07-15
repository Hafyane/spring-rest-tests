package com.worldline.fpl.recruitment.service;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;
import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction service.
 *
 * @author A525125
 */

@Slf4j
@Service
public class TransactionService {

	/** The account service. */
	private AccountService accountService;

	/** The transaction repository. */
	private TransactionRepository transactionRepository;

	/**
	 * Instantiates a new transaction service.
	 *
	 * @param accountService the account service
	 * @param transactionRepository the transaction repository
	 */
	@Autowired
	public TransactionService(AccountService accountService,
			TransactionRepository transactionRepository) {
		this.accountService = accountService;
		this.transactionRepository = transactionRepository;
	}

	/**
	 * Get transactions by account.
	 *
	 * @param accountId            the account id
	 * @param p            the pageable object
	 * @return the transactions by account
	 */
	public Page<TransactionResponse> getTransactionsByAccount(String accountId,
			Pageable p) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.NOT_FOUND_ACCOUNT,
					"Account doesn't exist");
		}
		return new PageImpl<TransactionResponse>(transactionRepository
				.getTransactionsByAccount(accountId, p).getContent().stream()
				.map(this::map).collect(Collectors.toList()));
	}

	/**
	 * Map {@link Transaction} to {@link TransactionResponse}.
	 *
	 * @param transaction the transaction
	 * @return the transaction response
	 */
	private TransactionResponse map(Transaction transaction) {
		TransactionResponse result = new TransactionResponse();
		result.setBalance(transaction.getBalance());
		result.setId(transaction.getId());
		result.setNumber(transaction.getNumber());
		return result;
	}

    /**
     * Delete transaction.
     *
     * @param transactionId the transaction id
     */
    public void deleteTransaction(String accountId ,String transactionId) {
        log.debug("Service Request to delete Transaction : {}", transactionId);
        transactionRepository.deleteTransactionById(accountId , transactionId);

    }
    
    /**
     * Adds the transaction.
     *
     * @param transaction the transaction
     * @return the transaction
     */
    public Transaction addTransaction(Transaction transaction){
     		log.debug("Service Request to Add Transaction : {}", transaction.getId());
    	    Transaction result =transactionRepository.addTransaction(transaction);
         
         return result;
    }
    
    /**
     * Update transaction.
     *
     * @param transaction the transaction
     * @return the transaction
     */
    public Transaction updateTransaction(Transaction transaction){
 		log.debug("Service Request to Update Transaction : {}", transaction.getId());
	    Transaction result =transactionRepository.updateTransaction(transaction);
     
     return result;
}
}
