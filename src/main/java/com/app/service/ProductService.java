package com.app.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductService {


	public List<Product> getProductByName(String product_name) throws BusinessException;
	public List<Product> getProductByPrice(double product_price) throws BusinessException;
	
}
