package com.demo.service;

import com.demo.model.Account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CsvIOService {
	public List<Account> readCsv(String fileLocation) {
		List<Account> accounts = new ArrayList<>();
		AtomicInteger i = new AtomicInteger(0);
		try (Stream<String> stream = Files.lines(Paths.get(fileLocation))) {
			stream.forEach(line -> {
				//Do not add the header in the csv to accounts
				if (i.get() != 0) {
					String[] accountFields = line.split(",");
					Account account = new Account();
					account.setAccountId(accountFields[0]);
					account.setAccountName(accountFields[1]);
					account.setFirstName(accountFields[2]);
					String createdOn = accountFields[3];
					DateFormat df = new SimpleDateFormat("MM/DD/YY");
					try {
						account.setCreatedOn(df.parse(createdOn));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					accounts.add(account);
				}
				i.getAndAdd(1);
			});

		} catch (IOException e) {

		}
		return accounts;
	}

	public void writeCsv(List<Account> accounts) {
		final String commaSpace = ", ";
		File f = new File("output.csv");

		try (FileWriter writer = new FileWriter(f)) {
			writer.write("Account ID, First Name, Created On, Status, Status Set On\n");
			accounts.forEach(account -> {
				StringBuffer sb = new StringBuffer();
				sb.append(account.getAccountId());
				sb.append(commaSpace);
				sb.append(account.getFirstName());
				sb.append(commaSpace);
				sb.append(account.getCreatedOn());
				sb.append(commaSpace);
				sb.append(account.getStatus());
				sb.append(commaSpace);
				sb.append(account.getStatusSetOn());
				sb.append("\n");

				try {
					writer.write(sb.toString());
					writer.flush();
				} catch (IOException e) {

				}
			});

		} catch (IOException e) {

		}

	}
}
