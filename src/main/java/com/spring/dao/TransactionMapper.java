package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.beans.Transaction;

public class TransactionMapper implements RowMapper<Transaction> {

    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rs.getInt("transaction_id"));
        transaction.setTransactionType(Transaction.TransactionType.valueOf(rs.getString("transaction_type")));
        transaction.setToAccount(rs.getInt("to_account"));
        transaction.setFromAccount(rs.getInt("from_account"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());
        transaction.setTransactionStatus(Transaction.TransactionStatus.valueOf(rs.getString("transaction_status")));
        return transaction;
    }
}