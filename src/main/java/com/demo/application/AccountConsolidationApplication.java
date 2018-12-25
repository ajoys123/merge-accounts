package com.demo.application;

import com.demo.service.AccountMergerService;

public class AccountConsolidationApplication {

	public static void main(String args[]) {
		AccountMergerService merger = new AccountMergerService();
		if (args.length != 2) {
			return;
		}
		merger.mergeAccounts(args[0], args[1]);
	}
}
