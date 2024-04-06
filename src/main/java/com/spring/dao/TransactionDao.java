package com.spring.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.beans.Transaction;
import com.spring.beans.Transaction.TransactionStatus;
import com.spring.beans.Transaction.TransactionType;

@Repository
public class TransactionDao {
	JdbcTemplate template;  
	AccountDao accountDao;
	@Autowired 
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	} 
	
	@Autowired
	 public TransactionDao(JdbcTemplate template, AccountDao accountDao) {
        this.template = template;
        this.accountDao = accountDao;
    }

	
	public int saveTransaction(Transaction transaction) {
	    try {
	    	int fromOwnerId = transaction.getFromAccount();
	        int toOwnerId = transaction.getToAccount();
	        BigDecimal amount = transaction.getAmount();
	        
	        String transactionType = mapTransactionType(transaction.getTransactionType());
	        String transactionStatus = mapTransactionStatus(transaction.getTransactionStatus());
	        String toAccountType = accountDao.mapAccTypeToString(transaction.getFromAccountType());
	        String fromAccountType = accountDao.mapAccTypeToString(transaction.getToAccountType());
	        if(transactionType != "DEPOSIT") {
	        	
	        	// get the current balance of the fromAccount
		        BigDecimal fromAccountBalance = accountDao.retrieveBalance(fromOwnerId, fromAccountType);

			     // Check if the balance is sufficient for the transaction
			        if (fromAccountBalance.compareTo(amount) < 0) {
			            // Insufficient balance
			            return -2;
			        }
	        	
	        }       
	    	        
	        boolean tochk = accountDao.checkAccountHasType(toOwnerId, toAccountType);
	        boolean fromchk = accountDao.checkAccountHasType(fromOwnerId, fromAccountType);
	        if (!tochk && !fromchk) {
	        	return -1;
	        }
	        
	    	
	        // Insert the transaction record into the database
	        String sql = "INSERT INTO transaction (transaction_type, to_account, from_account, amount, transaction_date, transaction_status,from_account_type, to_account_type) VALUES (?, ?, ?, ?, ?, ?,?,?)";
	        
	        template.update(sql, transactionType, transaction.getToAccount(), transaction.getFromAccount(), transaction.getAmount(), transaction.getTransactionDate(), transactionStatus, fromAccountType, toAccountType);

	        
	        // Update the balance in the respective accounts based on the transaction type
	        switch (transaction.getTransactionType()) {
	            case WITHDRAW:
	            	accountDao.withdrawBalance(fromOwnerId, fromAccountType, transaction.getAmount());
	                break;
	            case UTILITY:
	            	accountDao.withdrawBalance(fromOwnerId, fromAccountType, transaction.getAmount());
                	break;
	            case DEPOSIT:
	                // Check if the 'toAccount' exists
	                if (toOwnerId != 0 && toAccountType != null) {
	                	accountDao.depositBalance(toOwnerId, toAccountType, transaction.getAmount());
	                } else {
	                    throw new IllegalArgumentException("Destination account does not exist");
	                }
	                break;
	            case TRANSFER:
	                
	                // Withdraw from 'fromAccount' and deposit to 'toAccount'
	            	accountDao.withdrawBalance(fromOwnerId, fromAccountType, transaction.getAmount());
	            	accountDao.depositBalance(toOwnerId, toAccountType, transaction.getAmount());
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
	
	
	public int internalTransfer(int ownerId, String fromAccType, String toAccType, BigDecimal amount) {
	    // Define SQL queries to update the balances of the source and destination accounts
	    String updateFromAccSql = "UPDATE account SET balance = balance - ? WHERE owner_id = ? AND account_type = ? AND balance >= ?";
	    String updateToAccSql = "UPDATE account SET balance = balance + ? WHERE owner_id = ? AND account_type = ?";
	    try {
	        // Deduct the amount from the source account only if the balance is sufficient
	        int rowsUpdated = template.update(updateFromAccSql, amount, ownerId, fromAccType, amount);
	        if (rowsUpdated == 0) {
	            // Insufficient balance, rollback the transaction and return -1
	            return 0;
	        }

	        // Add the amount to the destination account
	        template.update(updateToAccSql, amount, ownerId, toAccType);

	        // Return 1 to indicate a successful internal transfer
	        return 1;
	    } catch (Exception e) {
	        // Log any exceptions or errors
	        e.printStackTrace();
	        // Return -1 to indicate failure
	        return -1;
	    }
	}



	public List<Transaction> getTransactions(int accountId, String accountType) {		
	    String sql = "SELECT * FROM transaction WHERE (to_account = ? OR from_account = ?) AND to_account_type=?";
	    List<Transaction> sr = template.query(sql, new TransactionMapper(), accountId, accountId, accountType);
	    return sr;
	}
	
	
	
	 public String mapTransactionType(TransactionType tt) {
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
	 
	 
	 public String mapTransactionStatus(TransactionStatus ts) {
		 if(ts == TransactionStatus.PENDING) {
			 return "PENDING";
		 }else if(ts == TransactionStatus.COMPLETED) {
			 return "COMPLETED";
		 }else {
			 return "FAILED";
		 }
	 }
}
