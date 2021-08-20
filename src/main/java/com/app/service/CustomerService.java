package com.app.service;
import com.app.model.Customer;
import java.util.List;
import com.app.exception.BusinessException;

public interface CustomerService {
	public int addCustomer(Customer customer) throws BusinessException;
    public int loginCustomer(Customer customer) throws BusinessException;
    public List<Customer> viewCustomer() throws BusinessException;
    public boolean checkMail(String cust_mail) throws BusinessException;
    public boolean checkPassword(String cust_password) throws BusinessException;


}
