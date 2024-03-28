package Bank_Term_Project.Bank_TP.dao;

import Bank_Term_Project.Bank_TP.model.User;

public interface UserDao {
	
	
	// Method to save a new User
	void saveUser(User user);
	
	// Method to retrieve a user by ID
	User getUserById(int userId);
	
	// Method to retrieve a user by email
	User getUserByEmail(String email);
	
	// Method to update user information
	void updateUser(User user);
	
	// Method to delete a user
	void deleteUser(int userId);

}
