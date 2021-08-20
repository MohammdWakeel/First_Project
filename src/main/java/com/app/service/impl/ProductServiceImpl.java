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
	public List<Product> getProductByPrice(double product_price) throws BusinessException {
		List<Product> productList=new ArrayList<>();
		if(product_price<=0) {
			throw new BusinessException("For this price no product available"+product_price);
		}else {
			//code here to DAO
			productList=productServiceDAO.getProductByPrice(product_price);
			
		}
		return productList;
	}

	@Override
	public List<Product> getProductByName(String product_name) throws BusinessException {
		List<Product> productList=null;
		if(product_name.matches("[a-zA-Z]{2,10}")) {
			//code here to DAO
			productList=productServiceDAO.getProductByName(product_name);
		}else {
			throw new BusinessException(product_name+" is out of stock at this time!");
		}
		return productList;
	}

	
}
