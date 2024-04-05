package com.spring.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.spring.beans.Account.AccountType;



public class Transaction {
	private int transactionId;
    private TransactionType transactionType; // Enum representing withdraw, deposit, utility, or transfer
    private int toAccount; 
    private int fromAccount; 
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionStatus transactionStatus; // Enum representing pending, completed, or failed
	private AccountType accountType;
    
    
    
 public Transaction(int transactionId, TransactionType transactionType, int toAccount, int fromAccount,
			BigDecimal amount, LocalDateTime transactionDate, TransactionStatus transactionStatus,
			AccountType accountType) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionStatus = transactionStatus;
		this.accountType = accountType;
	}

public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}



	// TransactionType enum definition
    public enum TransactionType {
        WITHDRAW,
        DEPOSIT,
        UTILITY,
        TRANSFER
    }
    
    // TransactionStatus enum definition
    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
 
}
