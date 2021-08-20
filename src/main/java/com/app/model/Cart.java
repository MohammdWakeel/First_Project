package com.app.model;

public class Cart {

	private int cart_id;
	private int product_id;
	private int cust_id;
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Cart(int cart_id, int product_id, int cust_id, String status) {
		super();
		this.cart_id = cart_id;
		this.product_id = product_id;
		this.cust_id = cust_id;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", product_id=" + product_id + ", cust_id=" + cust_id + ", status=" + status
				+ "]";
	}
		
}
