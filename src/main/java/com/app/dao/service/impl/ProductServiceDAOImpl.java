package com.app.dao.service.impl;
import java.util.*;

import org.apache.log4j.Logger;

import com.app.dao.dbutil.MysqlConnection;
import java.sql.*;
import com.app.dao.service.ProductServiceDAO;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductServiceDAOImpl implements ProductServiceDAO{
	
	private static Logger log=Logger.getLogger(ProductServiceDAOImpl.class);

	@Override
	public List<Product> getProductByName(String product_name) throws BusinessException {
		List<Product> productList=new ArrayList<>();
		try (Connection connection = MysqlConnection.getConnection()) {
			String sql= "select product_id, product_name, product_category, product_price from product where product_name=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product=new Product();
			  product.setProduct_id(resultSet.getInt("product_id"));
			  product.setProduct_name(resultSet.getString("product_name"));
			  product.setProduct_description(resultSet.getString("product_category"));
			  product.setProduct_price(resultSet.getDouble("product_price"));
			  productList.add(product);
			
			if(productList.size()<1) {
				throw new BusinessException("No Product Available at this time!");
			}
		}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error ocurred, please contact admin!");
		}
		return productList;
	}

	@Override
	public List<Product> getProductByPrice(double product_price) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
