package Bank_Term_Project.Bank_TP.service;

import Bank_Term_Project.Bank_TP.dao.UserDao;
import Bank_Term_Project.Bank_TP.model.User;

public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
	    this.userDao = userDao;
	}

	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public User getUserByEmail(String email) {
		 return userDao.getUserByEmail(email);
	}

	@Override
	public User createUser(User user) {
		return userDao.createUser(user);
	}

	 @Override
    public User updateUser(int userId, User user) {
        // Add validation logic if needed
        return userDao.updateUser(userId, user);
    }

	@Override
	public boolean deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}

	
	
}
