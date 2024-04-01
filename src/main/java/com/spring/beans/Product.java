package com.spring.beans;

public class Product {
int id;
String name;
int quantity;
float price;
String description;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Product(int id, String name, int quantity, float price, String description) {
	super();
	this.id = id;
	this.name = name;
	this.quantity = quantity;
	this.price = price;
	this.description = description;
}
public Product() {
	super();
	// TODO Auto-generated constructor stub
}

}
