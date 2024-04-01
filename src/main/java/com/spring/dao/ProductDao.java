package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.beans.Product;

public class ProductDao {
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int save(Product p){  
		
		int id = getProductByName(p.getName());
		
		if (id == -1) {
			String sql = "insert into product(name, quantity, price, description) values(?, ?, ?, ?)";  
		    return template.update(sql, p.getName(), p.getQuantity(), p.getPrice(), p.getDescription());   
		}else {
		    String sql = "update product set quantity=quantity + ?, price=?, description=? where id=?";
		    return template.update(sql, p.getQuantity(), p.getPrice(), p.getDescription(), id);
		}
	}  

	public int update(Product p){  
	    String sql = "update product set name=?, quantity=?, price=?, description=? where id=?";  
	    return template.update(sql, p.getName(),(int) p.getQuantity(), (float)p.getPrice(), p.getDescription(), (int)p.getId());  
	} 
	public int delete(int id) {
	    // Retrieve the current quantity of the product
	    String sqlGetQuantity = "select quantity from product where id=?";
	    int currentQuantity = template.queryForObject(sqlGetQuantity, Integer.class, id);
	    
	    // If the current quantity is zero, delete the product
	    if (currentQuantity == 1) {
	        String sqlDelete = "delete from product where id=?";
	        return template.update(sqlDelete, id);
	    } else {
	        // If the current quantity is not zero, decrement the quantity by 1
	        String sqlDecrementQuantity = "update product set quantity = quantity - 1 where id=?";
	        return template.update(sqlDecrementQuantity, id);
	    }
	}
  
	public Product getProductById(int id){  
	    String sql="select * from product where id=?";  
	    System.out.println("Getting record....");
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Product>(Product.class));  
	}  
	
	public int getProductByName(String name) {
	    String sql = "SELECT id FROM product WHERE name=?";
	    try {
	        // Execute the SQL query and return the product ID
	        return template.queryForObject(sql, Integer.class, name);
	    } catch (EmptyResultDataAccessException e) {
	        // Handle the case when no product with the given name is found
	        return -1; // Or any other suitable default value or error handling mechanism
	    }
	}
	public List<Product> getProduct(){  
	    return template.query("select * from product",new RowMapper<Product>(){  
	        public Product mapRow(ResultSet rs, int row) throws SQLException {  
	        	Product e=new Product();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setQuantity(rs.getInt(3)); 
	            e.setPrice(rs.getFloat(4));
	            e.setDescription(rs.getString(5));  
	            return e;  
	        }  
	    });  
	}
}
