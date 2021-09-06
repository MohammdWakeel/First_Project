package com.app.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductService {


	public Product getProductById(int product_id) throws BusinessException;
	public List<Product> getProductByName(String product_name) throws BusinessException;
	public List<Product> getProductByCategory(String product_category) throws BusinessException;
	
}
