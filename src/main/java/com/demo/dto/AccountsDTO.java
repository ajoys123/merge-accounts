package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsDTO {
	private List<AccountDTO> results;

	public List<AccountDTO> getResults() {
		return results;
	}

	public void setResults(List<AccountDTO> results) {
		this.results = results;
	}
}
