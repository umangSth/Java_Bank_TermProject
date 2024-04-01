package com.spring.beans;

import java.math.BigDecimal;


public class Account {
	private int account_id;
	private int owner_id;
	private BigDecimal balance;
	private AccountType accountType; // Enum representing checking, business, or savings

	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

		public Account(int account_id, int owner_id, BigDecimal balance, AccountType accountType) {
		super();
		this.account_id = account_id;
		this.owner_id = owner_id;
		this.balance = balance;
		this.accountType = accountType;
	}

		public Account() {
			super();
			// TODO Auto-generated constructor stub
		}

		// AccountType enum definition
		public enum AccountType {
			CHECKING,
			BUSINESS,
			SAVINGS
		}
}
