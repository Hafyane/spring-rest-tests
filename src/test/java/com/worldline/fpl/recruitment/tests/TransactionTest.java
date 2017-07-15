package com.worldline.fpl.recruitment.tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

/**
 * Account test.
 *
 * @author A525125
 */
public class TransactionTest extends AbstractTest {

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 * @throws Exception the exception
	 */
	@Test
	public void getTransactions() throws Exception {
		mockMvc.perform(get("/accounts/1/transactions"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", is(3)))
				.andExpect(jsonPath("$.content[0].number", is("12151885120")))
				.andExpect(jsonPath("$.content[0].balance", is(42.12)));
	}

	/**
	 * Gets the transactions no content.
	 *
	 * @return the transactions no content
	 * @throws Exception the exception
	 */
	@Test
	public void getTransactionsNoContent() throws Exception {
		mockMvc.perform(get("/accounts/2/transactions")).andExpect(
				status().isNoContent());
	}

	/**
	 * Gets the transactions on unexisting account.
	 *
	 * @return the transactions on unexisting account
	 * @throws Exception the exception
	 */
	@Test
	public void getTransactionsOnUnexistingAccount() throws Exception {
		mockMvc.perform(get("/accounts/3/transactions"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("NOT_FOUND_ACCOUNT")));
	}
	
	/**
	 * Transaction delete.
	 *
	 * @throws Exception the exception
	 */
	// Exercice 2
	@Test
	public void transactionDelete() throws Exception {
		//delete Transaction
		mockMvc.perform(delete("/accounts/1/transactions/1"))
		.andExpect(status().isOk());
		
		// Validate suppression
		mockMvc.perform(get("/accounts/1/transactions/"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalElements", is(2)))
		.andExpect(jsonPath("$.content[0].id", is("2")))
		.andExpect(jsonPath("$.content[1].id", is("3"))); 
	}
	
	/**
	 * Transaction delete on unexisting transaction.
	 *
	 * @throws Exception the exception
	 */
	// Exercice 2
	@Test
	public void transactionDeleteOnUnexistingTransaction() throws Exception {
		//delete Unexisting Transaction
		mockMvc.perform(delete("/accounts/1/transactions/4"))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.errorCode", is("NOT_FOUND_TRANSACTION")));
	}	
}
