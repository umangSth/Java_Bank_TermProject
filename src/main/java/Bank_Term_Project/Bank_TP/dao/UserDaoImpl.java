package Bank_Term_Project.Bank_TP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import Bank_Term_Project.Bank_TP.model.User;

public class UserDaoImpl implements UserDao {

	private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
    @Override
    public User createUser(User user) {
        String query = "INSERT INTO User (name, password, email, phone, address, user_type) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getUser_type().toString());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return user;
    }

	@Override
	public User getUserById(int userId) {
		
		String query = "SELECT * FROM User Where id=?";
		User user = null;
		
		 try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, userId);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    user = extractUserFromResultSet(resultSet);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exception
	        }
	        return user;
	}

	@Override
	public User getUserByEmail(String email) {
	    String query = "SELECT * FROM User WHERE email = ?";
	    User user = null;
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, email);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                user = extractUserFromResultSet(resultSet);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    return user;
	}

	@Override
	public User updateUser(int userId, User user) {
	    String query = "UPDATE User SET name = ?, email = ?, password = ?, phone = ?, address = ?, user_type = ? WHERE id = ?";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, user.getName());
	        statement.setString(2, user.getEmail());
	        statement.setString(3, user.getPassword());
	        statement.setString(4, user.getPhone());
	        statement.setString(5, user.getAddress());
	        statement.setString(6, user.getUser_type().toString());
	        statement.setInt(7, userId);
	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated == 0) {
	            // User with the given ID doesn't exist
	            return null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    return user;
	}

	@Override
	public boolean deleteUser(int userId) {
	    String query = "DELETE FROM User WHERE id = ?";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, userId);
	        int rowsDeleted = statement.executeUpdate();
	        return rowsDeleted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exception
	        return false;
	    }
	}

	
	private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
	    User user = new User();
	    user.setId(resultSet.getInt("id"));
	    user.setName(resultSet.getString("name"));
	    user.setPassword(resultSet.getString("password"));
	    user.setEmail(resultSet.getString("email"));
	    user.setPhone(resultSet.getString("phone"));
	    user.setAddress(resultSet.getString("address"));
	    user.setUser_type(User.UserType.valueOf(resultSet.getString("user_type").toUpperCase()));
	    return user;
	}
}
