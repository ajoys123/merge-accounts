package com.demo.service;

import com.demo.dto.AccountDTO;
import com.demo.dto.AccountsDTO;
import com.demo.model.Account;
import com.demo.rest.client.AccountClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountMergerServiceTest {

	AccountMergerService _service;

	CsvIOService _csvIO;

	AccountClient _accountClient;

	@Before
	public void setUp() throws Exception {
		_service = new AccountMergerService();
		_csvIO = mock(CsvIOService.class);
		_accountClient = mock(AccountClient.class);

		_service.csvIO = _csvIO;
		_service.accountClient = _accountClient;
	}

	@Test
	public void testAccountMerge() {
		List<Account> accounts = new ArrayList();

		Account account = new Account();
		account.setAccountId("12345");
		account.setAccountName("testName");
		account.setCreatedOn(new Date());
		account.setFirstName("firstName");

		accounts.add(account);

		AccountsDTO accountsDTO = new AccountsDTO();
		AccountDTO accountDto = new AccountDTO();
		accountDto.setAccountId("12345");
		accountDto.setCreatedOn("2018-02-15");
		accountDto.setStatus("good");
		accountsDTO.setResults(Collections.singletonList(accountDto));

		when(_csvIO.readCsv(anyString())).thenReturn(accounts);

		when(_accountClient.getAccounts()).thenReturn(accountsDTO);

		doNothing().when(_csvIO).writeCsv(any());

		_service.mergeAccounts("");
	}

}