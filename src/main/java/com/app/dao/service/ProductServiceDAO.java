package com.app.dao.service;

import java.util.List;
import com.app.dao.service.ProductServiceDAO;
import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductServiceDAO {
	
	public List<Product> getProductByName(String product_name) throws BusinessException;
	public List<Product> getProductByPrice(double product_price) throws BusinessException;

}
