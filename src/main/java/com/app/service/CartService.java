package com.app.service;
import com.app.model.*;

import java.util.List;

import com.app.exception.*;

public interface CartService {

	public int addToCart(Cart cart) throws BusinessException;
	public List<Cart> viewCart(Customer customer) throws BusinessException;
	public int removeFromCart(int product_id) throws BusinessException;
	
}
