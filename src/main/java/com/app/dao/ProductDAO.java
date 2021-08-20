package com.app.dao;
import com.app.exception.BusinessException;
import java.util.List;
import com.app.model.*;
public interface ProductDAO {
	
	public List<Product> getAllProducts() throws BusinessException;

	public int addProduct(Product product) throws BusinessException;
	
}
