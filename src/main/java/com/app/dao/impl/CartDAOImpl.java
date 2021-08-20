package com.app.dao.impl;

import java.util.*;

import org.apache.log4j.Logger;

import java.sql.*;
import com.app.dao.*;
import com.app.dao.impl.*;
import com.app.dao.dbutil.*;
import com.app.exception.BusinessException;
import com.app.model.*;
import com.app.model.Customer;
public class CartDAOImpl implements CartDAO {

	private static Logger log=Logger.getLogger(CartDAOImpl.class);
	@Override
	public List<Cart> viewCart(Customer customer) throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
        try(Connection connection = MysqlConnection.getConnection()) {
            String sql = "SELECT cartId, cartProductId, cartCustomerId, cartProductQuantity, cartProductTotal FROM cart WHERE cartCustomerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getCust_id());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart();
                Product product = new Product();
               // Customer customer = new Customer();
                cart.setCart_id(resultSet.getInt("cart_id"));
                product.setProduct_id(resultSet.getInt("cartProduct_id"));
                customer.setCust_id(resultSet.getInt("cartCust_id"));
                cart.setStatus(resultSet.getString("status"));
                cartList.add(cart);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact system admin");
        }
        return cartList;
	}

	@Override
	public int addToCart(Cart cart) throws BusinessException {
		int c;
        try(Connection connection = MysqlConnection.getConnection()) {
            String sql = "INSERT INTO cart(cart_id, product_id, cust_id, status) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cart.getCart_id());
            preparedStatement.setInt(2, cart.getProduct_id());
            preparedStatement.setInt(3, cart.getCust_id());
            preparedStatement.setString(4, cart.getStatus());

            c = preparedStatement.executeUpdate();
            if (c == 0) {
                throw new BusinessException("Customer creation failed! Check your query and try again!!!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return c;
	}

	@Override
	public int removeFromCart(int cart_id) throws BusinessException {
		int c;
        try(Connection connection = MysqlConnection.getConnection()) {
            String sql = "Delete from cart where cart_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cart_id);
            c = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact systemAdmin");
        }
        return c;
	}

	
	
}
