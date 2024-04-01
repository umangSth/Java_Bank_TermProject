package com.spring.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.beans.User;

public class UserDao {
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(User u) {
		String sql = "insert into user(name, email, phone, address, user_type, password) values(?,?,?,?,?,?) ";
		return template.update(sql, u.getName(), u.getEmail(), u.getPhone(), u.getAddress(), u.getUser_type(), u.getPassword());
	}
	
	public int update(User u) {
		String sql = "update user set name=?, email=?, phone=?, address=?, user_type=?, password=? where id=?";
		return template.update(sql, u.getName(), u.getEmail(), u.getPhone(), u.getAddress(), u.getUser_type(), u.getPassword(), u.getId());
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
