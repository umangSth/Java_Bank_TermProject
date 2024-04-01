package Bank_Term_Project.Bank_TP.dao;

import Bank_Term_Project.Bank_TP.model.User;

public interface UserDao {
	
	
	// Method to save a new User
	User createUser(User user);
	
	// Method to retrieve a user by ID
	User getUserById(int userId);
	
	// Method to retrieve a user by email
	User getUserByEmail(String email);
	
	// Method to update user information
	User updateUser(int userId, User user);
	
	// Method to delete a user
	boolean deleteUser(int userId);

}
