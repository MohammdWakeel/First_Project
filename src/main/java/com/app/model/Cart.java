package com.app.model;

public class Cart {

	private int cart_id;
	private int product_id;
	private int cust_id;
	private double product_price;
	public Cart() {
		
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public Cart(int cart_id, int product_id, int cust_id, double product_price) {
		super();
		this.cart_id = cart_id;
		this.product_id = product_id;
		this.cust_id = cust_id;
		this.product_price = product_price;
	}
	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", product_id=" + product_id + ", cust_id=" + cust_id + ", product_price=" + product_price
				+ "]";
	}
		
}
