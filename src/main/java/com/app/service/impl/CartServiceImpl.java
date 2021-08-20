package com.app.service.impl;

import com.app.dao.dbutil.MysqlConnection;
import com.app.exception.BusinessException;
import java.sql.*;
import java.util.List;
import org.apache.log4j.Logger;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.*;
import com.app.dao.*;
import com.app.dao.impl.*;
public class CartServiceImpl implements CartService{
	
	CartDAO cartDAO = new CartDAOImpl();

	@Override
	public int addToCart(Cart cart) throws BusinessException {
		int result = 0;
        result = cartDAO.addToCart(cart);
        return result;
	}

	@Override
	public List<Cart> viewCart(Customer customer) throws BusinessException {
		List<Cart> cartList = null;
        cartList = cartDAO.viewCart(customer);
        return cartList;
	}

	@Override
	public int removeFromCart(int cart_id) throws BusinessException {
		int result;
        result=cartDAO.removeFromCart(cart_id);
        return result;
	}
	
}
