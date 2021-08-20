package com.app.service.impl;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.CustomerService;
import com.app.dao.*;
import com.app.dao.impl.*;
public class CustomerServiceImpl implements CustomerService {
    CustomerDAO customerDAO = new CustomerDAOImpl();
	@Override
	public int addCustomer(Customer customer) throws BusinessException {
		int added;
        if (customer.getCust_name().length()!=0) {
            added = customerDAO.addCustomer(customer);
        } else {
            throw new BusinessException("Invalid Customer Details!");
        }
        return added;
	}

	@Override
	public int loginCustomer(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Customer> viewCustomer() throws BusinessException {
        List<Customer> customerList;
        customerList = customerDAO.viewCustomer();
        return customerList;
	}

	@Override
	public boolean checkMail(String cust_mail) throws BusinessException {
        if (cust_mail.matches("[a-z]\\w{6,30}")) {
            return true;
        } else {
            throw new BusinessException("Your mail id is not valid");
        }
	}

	@Override
	public boolean checkPassword(String cust_password) throws BusinessException {
		if (cust_password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$")) {
            return true;
        } else {
            throw new BusinessException("Your Password is not valid");
        }
	}

	
	
}
