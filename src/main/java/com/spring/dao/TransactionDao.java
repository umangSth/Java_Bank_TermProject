package com.spring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.beans.Transaction;
import com.spring.beans.Transaction.TransactionStatus;
import com.spring.beans.Transaction.TransactionType;

public class TransactionDao {
	JdbcTemplate template;  
	AccountDao ad = new AccountDao();
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}
	
	public int saveTransaction(Transaction transaction) {
	    try {
	        // Insert the transaction record into the database
	        String sql = "INSERT INTO transaction (transactionType, toAccount, fromAccount, amount, transactionDate, transactionStatus) VALUES (?, ?, ?, ?, ?, ?)";
	        String transactionType = mapTransactionType(transaction.getTransactionType());
	        String transactionStatus = mapTransactionStatus(transaction.getTransactionStatus());
	        template.update(sql, transactionType, transaction.getToAccount(), transaction.getFromAccount(), transaction.getAmount(), transaction.getTransactionDate(), transactionStatus);

	        // Retrieve the owner ID and account type for the 'fromAccount'
	        AccountDao ad = new AccountDao();
	        Map<String, Object> fromAccountInfo = ad.getOwnerIdAndType(transaction.getFromAccount());
	        int fromOwnerId = (int) fromAccountInfo.get("owner_id");
	        String fromAccountType = (String) fromAccountInfo.get("accountType");
	        
	        // Retrieve the owner ID and account type for the 'toAccount'
            Map<String, Object> toAccountInfo = ad.getOwnerIdAndType(transaction.getToAccount());
            int toOwnerId = (int) toAccountInfo.get("owner_id");
            String toAccountType = (String) toAccountInfo.get("accountType");
	        // Update the balance in the respective accounts based on the transaction type
	        switch (transaction.getTransactionType()) {
	            case WITHDRAW:
	                ad.withdrawBalance(fromOwnerId, fromAccountType, transaction.getAmount());
	                break;
	            case DEPOSIT:
	                // Check if the 'toAccount' exists
	                if (toOwnerId != 0 && toAccountType != null) {
	                    ad.depositBalance(toOwnerId, toAccountType, transaction.getAmount());
	                } else {
	                    throw new IllegalArgumentException("Destination account does not exist");
	                }
	                break;
	            case TRANSFER:
	                
	                // Withdraw from 'fromAccount' and deposit to 'toAccount'
	                ad.withdrawBalance(fromOwnerId, fromAccountType, transaction.getAmount());
	                ad.depositBalance(toOwnerId, toAccountType, transaction.getAmount());
	                break;
	            default:
	                // Handle unsupported transaction types
	                throw new IllegalArgumentException("Unsupported transaction type: " + transaction.getTransactionType());
	        }

	        return 1; // Successful transaction
	    } catch (Exception e) {
	        // Log the exception
	        e.printStackTrace();
	        return 0; // Failed transaction
	    }
	}


	 public List<Transaction> getTransactions(int accountId, String accountType) {
	        String sql = "SELECT * FROM transaction WHERE account_id = ? and account_type = ?";
	        return template.query(sql, new TransactionMapper(), accountId,accountType);
	  }
	 
	 private String mapTransactionType(TransactionType tt) {
		 if(tt == TransactionType.WITHDRAW) {
			return "WITHDRAW"; 
		 }else if(tt == TransactionType.DEPOSIT) {
			 return "DEPOSIT";
		 }else if(tt == TransactionType.UTILITY) {
			 return "UTILITY";
		 }else {
			 return "TRANSFER";
		 }
	 }
	 
	 
	 private String mapTransactionStatus(TransactionStatus ts) {
		 if(ts == TransactionStatus.PENDING) {
			 return "PENDING";
		 }else if(ts == TransactionStatus.COMPLETED) {
			 return "COMPLETED";
		 }else {
			 return "FAILED";
		 }
	 }
}
