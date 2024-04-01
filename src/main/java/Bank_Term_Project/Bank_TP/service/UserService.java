package Bank_Term_Project.Bank_TP.service;

import Bank_Term_Project.Bank_TP.model.User;

public interface UserService {
	User getUserById(int userId);

    User getUserByEmail(String email);

    User createUser(User user);

    boolean deleteUser(int userId);

	User updateUser(int userId, User user);
}
