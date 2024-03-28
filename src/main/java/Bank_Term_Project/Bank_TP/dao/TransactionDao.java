package Bank_Term_Project.Bank_TP.dao;

import Bank_Term_Project.Bank_TP.model.Transaction;

public interface TransactionDao {
	
	// Method to save the new Transaction
	void saveTransaction(Transaction transaction);
	
	// Method to update the transaction
	void updateTransaction(Transaction Transaction);
	
	// Method to get transaction by id
	Transaction getTransactionById(int transactionId);
	

}
