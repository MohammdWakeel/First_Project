package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.ProductDAO;
import com.app.dao.service.ProductServiceDAO;
import com.app.dao.impl.ProductDAOImpl;
import com.app.dao.service.impl.ProductServiceDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductServiceDAO productServiceDAO=new ProductServiceDAOImpl();
	@Override
	public List<Product> getProductByCategory(String product_category) throws BusinessException {
		List<Product> productList=new ArrayList<>();
		if(product_category.matches("[a-zA-Z]{2,20}")) {
			productList=productServiceDAO.getProductByCategory(product_category);
			
		}else {
			//code here to DAO
			throw new BusinessException("For this category no product available "+product_category);
			
		}
		return productList;
	}

	@Override
	public Product getProductById(int product_id) throws BusinessException {
		// TODO Auto-generated method stub
		Product product=null;
		if(product_id<100 || product_id>1000) {
			throw new BusinessException("Invalid product id"+product_id);
		}
		else {
			product=productServiceDAO.getProductById(product_id);
		}
		// TODO Auto-generated method stub
		return product;
	}

	@Override
	public List<Product> getProductByName(String product_name) throws BusinessException {
		List<Product> productList=null;
		if(product_name.matches("[a-zA-Z]{2,10}")) {
			productServiceDAO.getProductByName(product_name);
		}else {
			throw new BusinessException(product_name+" is out of stock at this time!");
		}
		return productList;
	}

	
}
