package com.app.dao.impl;

import java.sql.PreparedStatement;
import com.app.exception.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.CustomerDAO;
import com.app.dao.impl.*;
import com.app.model.Customer;
import com.app.dao.dbutil.*;
import java.sql.Connection;
public class CustomerDAOImpl implements CustomerDAO{
	private static Logger log=Logger.getLogger(CustomerDAOImpl.class);
	@Override
	public int addCustomer(Customer customer) throws BusinessException {
		int ch=0;
		try (Connection connection = MysqlConnection.getConnection()) {

			String sql = "insert into customer(cust_id, cust_name, cust_mail, cust_password) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCust_id());
			preparedStatement.setString(2, customer.getCust_name());
			preparedStatement.setString(3, customer.getCust_mail());
			preparedStatement.setString(4, customer.getCust_password());

			ch = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error ocurred, please contact admin!----");
		}
		return ch;
	}

	public List<Customer> isValidCustomer(String cust_mail, String cust_password) throws BusinessException {
		List<Customer> customerList=new ArrayList<>();
		try (Connection connection = MysqlConnection.getConnection()) {

			String sql = "select cust_id, cust_name ,cust_mail,cust_password from customer where cust_password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cust_password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Customer customer=new Customer();
				customer.setCust_id(resultSet.getInt("cust_id"));
				customer.setCust_name(resultSet.getString("cust_name"));
				customer.setCust_mail(resultSet.getString("cust_mail"));
				customer.setCust_password(resultSet.getString("cust_password"));
				customerList.add(customer);				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error ocurred, please contact admin!");

		}
		return customerList;

	}

	@Override
	public List<Customer> viewCustomer() throws BusinessException {
		// TODO Auto-generated method stub
		List<Customer> customerList = new ArrayList<>();
        try(Connection connection = MysqlConnection.getConnection()) {
            String sql = "SELECT cust_id, cust_name, cust_mail, cust_password FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCust_id(resultSet.getInt("cust_id"));
                customer.setCust_name(resultSet.getString("cust_name"));
                customer.setCust_mail(resultSet.getString("cust_mail"));
                customer.setCust_password(resultSet.getString("cust_password"));
                customerList.add(customer);
            }
            if (customerList.size() == 0) {
                throw new BusinessException("Customer Data not available!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred, contact system admin!");
        }
        return customerList;
	}

	@Override
	public boolean checkMail(String cust_mail) throws BusinessException {
        try(Connection connection = MysqlConnection.getConnection()) {
            String sql = "SELECT cust_mail FROM Customer WHERE cust_mail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cust_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.warn(e);
            throw new BusinessException("Internal error occurred! contact system admin!");
        }
        return false;
	}

	@Override
	public boolean checkPassword(String cust_password) throws BusinessException {
		// TODO Auto-generated method stub
		try(Connection connection = MysqlConnection.getConnection()) {
            String sql = "SELECT cust_password FROM Customer WHERE cust_password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cust_password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal error occurred! contact system admin!");
        }
        return false;
	}
}
