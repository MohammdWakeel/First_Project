package com.app.dao;
import java.util.List;
import com.app.model.*;

import com.app.exception.BusinessException;

public interface CustomerDAO {
	
	public int addCustomer(Customer customer) throws BusinessException;
	public List<Customer> isValidCustomer(String cust_mail, String cust_password) throws BusinessException;
    public List<Customer> viewCustomer() throws BusinessException;
    public boolean checkMail(String cust_mail) throws BusinessException;
    public boolean checkPassword(String cust_password) throws BusinessException;
	
}
