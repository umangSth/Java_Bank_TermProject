package com.spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDao {
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	} 
}
