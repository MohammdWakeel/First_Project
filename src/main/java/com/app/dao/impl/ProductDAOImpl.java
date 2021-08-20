package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.ProductDAO;
import com.app.dao.dbutil.MysqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductDAOImpl implements ProductDAO {
	Logger log=Logger.getLogger(ProductDAOImpl.class);

	@Override
	public List<Product> getAllProducts() throws BusinessException{
		List<Product> productList=new ArrayList<>();
	
		try (Connection connection = MysqlConnection.getConnection()) {
			
			String sql= "select product_id,product_name, product_category, product_price from product order by product_id";
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
		}
		catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error ocurred, please contact admin+!");
		}
		return productList;
	}

	@Override
	public int addProduct(Product product) throws BusinessException {
		int c = 0;
		try (Connection connection = MysqlConnection.getConnection()) {

			String sql = "insert into product(product_id,product_name, product_category, product_price) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getProduct_id());
			preparedStatement.setString(3, product.getProduct_name());
			preparedStatement.setString(2, product.getProduct_category());
			preparedStatement.setDouble(4, product.getProdcut_price());

			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error ocurred, please contact admin!");
		}
		return c;
	}

}
