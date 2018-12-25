package com.demo.rest.client;

import com.demo.dto.AccountsDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class AccountClient {

	//Using the end point accounts instead of accounts/{account_id}. Making a HTTP call is expensive for each account
	//If we know before hand about the size of data set, we can use limit and offset query parameters.
	private static final String REST_URI = "http://interview.wpengine.io/v1/accounts/";
	private Client client = ClientBuilder.newClient();

	public AccountsDTO getAccounts() {
		AccountsDTO accounts = client.target(REST_URI)
				.request(MediaType.APPLICATION_JSON)
				.get(AccountsDTO.class);

		return accounts;
	}
}
