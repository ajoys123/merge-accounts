package com.demo.application;

import com.demo.service.AccountMergerService;

public class AccountConsolidationApplication {

	public static void main(String args[]) {
		AccountMergerService merger = new AccountMergerService();
		merger.mergeAccounts(args[0]);
	}
}
