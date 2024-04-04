package com.spring.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import com.spring.beans.User;
import com.spring.beans.User.UserType;


public class UserDao {
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(User u) {
	    String userTypeString = mapUserTypeToString(u.getUser_type());
	    String sql = "insert into user(name, email, phone, address, user_type, password) values(?,?,?,?,?,?)";
	    return template.update(sql, u.getName(), u.getEmail(), u.getPhone(), u.getAddress(), userTypeString, u.getPassword());
	}
	
	private String mapUserTypeToString(UserType userType) {
	    if (userType == UserType.ADMIN) {
	        return "ADMIN";
	    } else {
	        return "CLIENT";
	    }
	}
	
	public int update(User u) {
	    // Check if the new email is unique
	    boolean isUnique = isEmailUnique(u.getEmail());
	    if (!isUnique) {
	        // Email is not unique, return a value indicating failure
	        return -1; // You can choose any suitable return value to indicate failure
	    }

	    // Proceed with the update operation since the email is unique
	    String sql = "update user set name=?, email=?, phone=?, address=?, user_type=?, password=? where id=?";
	    return template.update(sql, u.getName(), u.getEmail(), u.getPhone(), u.getAddress(), u.getUser_type().toString(), u.getPassword(), u.getId());
	}

	public boolean isEmailUnique(String email) {
	    // Query the database to check if the email is unique
	    String sql = "select count(*) from user where email = ?";
	    int count = template.queryForObject(sql, Integer.class, email);
	    return count == 0; // Return true if email is unique, false otherwise
	}
	public User getUserByEmail(String email, String password) {
	    String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
	    try {
	        // Execute the SQL query and return the user object
	        return template.queryForObject(sql, new Object[]{email, password}, new BeanPropertyRowMapper<>(User.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Return null if no user is found
	    }
	}
	
	public int delete(String email) {
	    String sql = "DELETE FROM user WHERE email = ?";
	    return template.update(sql, email);
	}

}
