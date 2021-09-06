package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			
			String sql= "select product_id, product_name, product_category, product_price from product order by product_id";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product=new Product();
			  product.setProduct_id(resultSet.getInt("product_id"));
			  product.setProduct_name(resultSet.getString("product_name"));
			  product.setProduct_category(resultSet.getString("product_category"));
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

			String sql = "insert into product(product_id, product_name, product_category, product_price) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			/*preparedStatement.setInt(1, product.getProduct_id());
			preparedStatement.setString(2, product.getProduct_name());
			preparedStatement.setString(3, product.getProduct_category());
			preparedStatement.setDouble(4, product.getProdcut_price());*/
			Scanner sc=new Scanner(System.in);
			
			while(true) {
				System.out.println("Enter product id");
				int product_id=Integer.parseInt(sc.nextLine());
				System.out.println("Enter product name");
				String product_name =sc.nextLine();
				System.out.println("Enter product category");
				String product_category= sc.nextLine();
				System.out.println("Enter product price");
				double product_price=Double.parseDouble(sc.nextLine());
				preparedStatement.setInt(1, product_id);
				preparedStatement.setString(2, product_name);
				preparedStatement.setString(3, product_category);
				preparedStatement.setDouble(4, product_price);
				c = preparedStatement.executeUpdate();
				System.out.println("Record Inserted successfuly!");
				System.out.println("Do you wnat to insert one more record(Yes/No)");
				String opString=sc.nextLine();
				if(opString.equalsIgnoreCase("No")) {
					break;
				}
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error ocurred, please contact admin!");
		}
		return c;
	}

}
