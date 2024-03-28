package Bank_Term_Project.Bank_TP.dao;

import Bank_Term_Project.Bank_TP.model.Account;

public interface AccountDao {

	// method to save new account
	void saveAccount(Account account);
	
	// method to update new account
	void updateAccount(Account account);
	
	// method to delete new account
	void deleteAccount(int accountId);
}
