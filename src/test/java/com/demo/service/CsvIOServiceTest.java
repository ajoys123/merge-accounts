package com.demo.service;

import com.demo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.Assert.*;

public class CsvIOServiceTest {
	CsvIOService _csvIOService;

	@Before
	public void setUp() throws Exception {
		_csvIOService = new CsvIOService();
	}

	@Test
	public void testReadCsv() {
		List<Account> accounts = _csvIOService.readCsv("input.csv");
		Assert.assertEquals(7, accounts.size());
	}

	@Test
	public void testReadCsvWithError() {
		List<Account> accounts = _csvIOService.readCsv("src/test/resources/input_dateError.csv");
		Assert.assertEquals(7, accounts.size());
		Assert.assertTrue(accounts.get(0).getCreatedOn() == null);
	}

}