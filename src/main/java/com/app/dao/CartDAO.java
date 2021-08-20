package com.app.dao;
import java.util.*;
import com.app.model.*;
import com.app.exception.*;
public interface CartDAO {
	
	public List<Cart> viewCart(Customer customer) throws BusinessException;
    public int addToCart(Cart cart) throws BusinessException;
    public int removeFromCart(int cart_id) throws BusinessException;

}
