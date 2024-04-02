package com.spring.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.beans.Account;
import com.spring.beans.Account.AccountType;

public class AccountDao {
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	} 
	
	public int saveAccount(Account a) {
		String AccountType = mapAccTypeToString(a.getAccountType());
		String sql = "insert into account(owner_id, balance, accountType) value(?,?,?)";
		return template.update(sql, a.getAccount_id(), a.getBalance(), AccountType);
	}
	
	
	private String mapAccTypeToString(AccountType at) {
		if(at == AccountType.CHECKING) {
			return "CHECKING";
		} else if(at == AccountType.SAVINGS) {
			return "SAVINGS";
		}else {
			return "BUSINESS";
		}
	}
	
	public int update(Account a) {
		String AccountType = mapAccTypeToString(a.getAccountType());
		String sql = "update account set owner_id=?, balance=?, accountType=? where account_id = ?";
		return template.update(sql, a.getAccount_id(), a.getBalance(), AccountType, a.getAccount_id());
	}
	
	public Map<String, BigDecimal> retrieveBalances(int owner_id) {
	    String sql = "SELECT account_type, balance FROM account WHERE owner_id=?";
	    List<Map<String, Object>> rows = template.queryForList(sql, owner_id);
	    
	    Map<String, BigDecimal> balances = new HashMap<>();
	    for (Map<String, Object> row : rows) {
	        String accountType = (String) row.get("account_type");
	        BigDecimal balance = (BigDecimal) row.get("balance");
	        balances.put(accountType, balance);
	    }
	    
	    return balances;
	}
	
	public String withdrawBalance(int owner_id, String accountType, BigDecimal balance) {
	    // Retrieve the current balance for the specified account
	    BigDecimal currentBalance = retrieveBalance(owner_id, accountType);
	    
	    // Check if the account exists
	    if (currentBalance == null) {
	        return "Account does not exist";
	    }
	    
	    // Check if the balance is sufficient for the withdrawal
	    if (currentBalance.compareTo(balance) < 0) {
	        return "Insufficient balance";
	    }
	    
	    // Perform the withdrawal
	    BigDecimal newBalance = currentBalance.subtract(balance);
	    updateBalance(owner_id, accountType, newBalance);
	    
	    return "Withdrawal successful";
	}

	private BigDecimal retrieveBalance(int owner_id, String accountType) {
	    String sql = "SELECT balance FROM account WHERE owner_id=? AND account_type=?";
	    try {
	        return template.queryForObject(sql, BigDecimal.class, owner_id, accountType);
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Account does not exist
	    }
	}

	private void updateBalance(int owner_id, String accountType, BigDecimal newBalance) {
	    String sql = "UPDATE account SET balance=? WHERE owner_id=? AND account_type=?";
	    template.update(sql, newBalance, owner_id, accountType);
	}

	private boolean checkIfAccount(int owner_id, String accountType) {
	    if (accountType.isEmpty()) {
	        String sql = "SELECT COUNT(DISTINCT account_type) FROM account WHERE owner_id=?";
	        int accNo = template.queryForObject(sql, Integer.class, owner_id);
	        return accNo > 0;
	    } else {
	        try {
	            String sql = "SELECT account_type FROM account WHERE owner_id=? AND account_type=?";
	            String acc = template.queryForObject(sql, String.class, owner_id, accountType);
	            return acc != null;
	        } catch (EmptyResultDataAccessException e) {
	            return false; // Account doesn't exist for the specified accountType
	        }
	    }
	}

	
	
}
