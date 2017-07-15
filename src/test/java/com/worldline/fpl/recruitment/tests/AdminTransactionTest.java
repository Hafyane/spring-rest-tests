package com.worldline.fpl.recruitment.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;







import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.worldline.fpl.recruitment.entity.Transaction;


/**
 * Account test
 * 
 * @author A525125
 *
 */
public class AdminTransactionTest extends AbstractTest {

	@Test
	public void createTransaction() throws Exception {
		
		Transaction transaction= new Transaction();
		transaction.setId("4");
		transaction.setAccountId("1");
		transaction.setNumber("123243");
		transaction.setBalance(BigDecimal.valueOf(12.12));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(transaction);

	    mockMvc.perform(post("/admin/transaction").contentType(MediaType.APPLICATION_JSON_UTF8)
	        .content(requestJson))
	        .andExpect(status().isCreated());
	}

	@Test
	public void createTransactionBadParam() throws Exception {

		Transaction transaction= new Transaction();
		transaction.setId(null);  //  id is null
		transaction.setAccountId("1");
		transaction.setNumber("123243");
		transaction.setBalance(BigDecimal.valueOf(12.12));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(transaction);

	    mockMvc.perform(post("/admin/transaction").contentType(MediaType.APPLICATION_JSON_UTF8)
	        .content(requestJson))
	        .andExpect(status().isBadRequest());

	}

	@Test
	public void updateTransaction() throws Exception {
		
		Transaction transaction= new Transaction();
		transaction.setId("1");
		transaction.setAccountId("1");
		transaction.setNumber("44444");
		transaction.setBalance(BigDecimal.valueOf(12.12));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(transaction);

	    mockMvc.perform(put("/admin/transaction").contentType(MediaType.APPLICATION_JSON_UTF8)
	        .content(requestJson))
	        .andExpect(status().isOk());
	}

	@Test
	public void updateUnexistingTransaction() throws Exception {
		Transaction transaction= new Transaction();
		transaction.setId("9");   //  Unexisting Transaction
		transaction.setAccountId("1");
		transaction.setNumber("44444");
		transaction.setBalance(BigDecimal.valueOf(12.12));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(transaction);

	    mockMvc.perform(put("/admin/transaction").contentType(MediaType.APPLICATION_JSON_UTF8)
	        .content(requestJson))
	        .andExpect(status().isNotFound());
	}

	@Test
	public void updateTransactionBadRequest() throws Exception {
		Transaction transaction= new Transaction();
		transaction.setId(null);   //  id is null
		transaction.setAccountId("1");
		transaction.setNumber("44444");
		transaction.setBalance(BigDecimal.valueOf(12.12));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(transaction);

	    mockMvc.perform(put("/admin/transaction").contentType(MediaType.APPLICATION_JSON_UTF8)
	        .content(requestJson))
	        .andExpect(status().isBadRequest());
	}



}
