package com.app.dao.service;

import java.util.List;
import com.app.dao.service.ProductServiceDAO;
import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductServiceDAO {
	
	public List<Product> getProductByName(String product_name) throws BusinessException;
	public List<Product> getProductByCategory(String product_category) throws BusinessException;
	public Product getProductById(int product_id) throws BusinessException;

}
