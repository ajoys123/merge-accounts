package com.demo.service;

import com.demo.dto.AccountDTO;
import com.demo.dto.AccountsDTO;
import com.demo.model.Account;
import com.demo.rest.client.AccountClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountMergerService {

	CsvIOService csvIO = new CsvIOService();

	AccountClient accountClient = new AccountClient();

	/**
	 * Merges accounts between the input csv file and result from /accounts end point
	 *
	 * @param inputFile Path to the input file
	 * @param outputFile Name of the output file
	 */
	public void mergeAccounts(String inputFile, String outputFile) {
		List<Account> csvAccounts = csvIO.readCsv(inputFile);
		AccountsDTO apiAccounts = accountClient.getAccounts();

		Map<String, AccountDTO> apiAccountMap =
				apiAccounts.getResults().stream()
						.collect(Collectors.toMap(AccountDTO::getAccountId, accountDTO -> accountDTO));

		mergeAccounts(csvAccounts, apiAccountMap);

		csvIO.writeCsv(csvAccounts, outputFile);
	}

	/**
	 * Returns a list of Account by merging the account from API Account map
	 * @param csvAccounts
	 * @param apiAccountMap
	 * @return
	 */
	private List<Account> mergeAccounts(List<Account> csvAccounts, Map<String, AccountDTO> apiAccountMap) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.print(df.parse("2011-01-12"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		csvAccounts.forEach(csvAccount -> {
			AccountDTO apiAccount = apiAccountMap.get(csvAccount.getAccountId());
			csvAccount.setStatus(apiAccount.getStatus());
			try {
				csvAccount.setCreatedOn(df.parse(apiAccount.getCreatedOn()));
				csvAccount.setStatusSetOn(new Date());
			} catch (ParseException e) {

			}
		});
		return csvAccounts;
	}
}
