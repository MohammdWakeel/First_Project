package com.app.model;

public class Customer {
	private int cust_id;
	private String cust_name;
	private String cust_mail;
	private String cust_password;
	
	public Customer() {
		
	}
	
	public Customer(int cust_id, String cust_name, String cust_mail, String cust_password) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_mail = cust_mail;
		this.cust_password = cust_password;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_mail=" + cust_mail
				+ ", cust_password=" + cust_password + "]";
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_mail() {
		return cust_mail;
	}

	public void setCust_mail(String cust_mail) {
		this.cust_mail = cust_mail;
	}

	public String getCust_password() {
		return cust_password;
	}

	public void setCust_password(String cust_password) {
		this.cust_password = cust_password;
	}

	
	}
