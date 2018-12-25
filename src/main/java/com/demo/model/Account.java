package com.demo.model;

import java.util.Date;

public class Account {

	private String accountId;
	private String accountName;
	private String firstName;
	private Date createdOn;
	private String status;
	private Date statusSetOn;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusSetOn() {
		return statusSetOn;
	}

	public void setStatusSetOn(Date statusSetOn) {
		this.statusSetOn = statusSetOn;
	}
}
