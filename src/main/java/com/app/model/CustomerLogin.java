package com.app.model;

import com.app.dao.CustomerDAO;
import com.app.dao.impl.CustomerDAOImpl;
import com.app.dao.impl.ProductDAOImpl;
import com.app.dao.service.*;
import com.app.dao.service.impl.ProductServiceDAOImpl;
import com.app.exception.*;
import com.app.model.*;
import com.app.dao.*;
import java.util.*;
import org.apache.log4j.Logger;
import com.app.service.*;
import com.app.service.impl.*;
//import service.CustomerService;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerLogin {
    public static boolean customerLogin= false;
    private static Logger log = Logger.getLogger(CustomerLogin.class);
    CustomerService customerService = new CustomerServiceImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ProductServiceDAO productServiceDAO=new ProductServiceDAOImpl();
    ProductDAO productDAO=new ProductDAOImpl();
    Scanner scanner = new Scanner(System.in);

    public void registerCustomer() {
        int c = 0;
        do {
            Customer customer = new Customer();
            log.info("");
            log.info("+---------------------------------+");
            log.info("|      Register As New Customer   |");
            log.info("+---------------------------------+");
            log.info("| Hello..!!                       |");
            log.info("| Welcome To Shoppers Shop        |");
            log.info("|                                 |");
            log.info("| Enter Your Name :               |");
            log.info("+---------------------------------+");

            String cust_firstName = scanner.nextLine(); 
            customer.setCust_name(cust_firstName);
            log.info("Enter user mail : ");
            String cust_mail = scanner.nextLine();
            try {
                if (!customerDAO.checkMail(cust_mail)) {
                    customer.setCust_mail(cust_mail);
                    log.info("Enter Password : ");
                    String cust_password = scanner.nextLine();
                    if (customerService.checkPassword(cust_password)) {
                        customer.setCust_password(cust_password);
                        c = customerService.addCustomer(customer);
                        if (c== 1) {
                            Thread.sleep(1500);
                            log.info("Your account has been created!");                        }
                     }
                }
            } catch (BusinessException | InterruptedException e) {
                log.warn(e.getMessage());
                log.info("");
            }
        } while (c != 1);
    }

    public void customerLogin() {
        boolean c = false;
        do {
            Customer customer = new Customer();
            log.info("");
            log.info("+-------------------------------+");
            log.info("|         Login As Customer        |");
            log.info("+-------------------------------+");
            log.info("Welcome To Shopper's Shop!");
            log.info("Please enter your details to Login");
            log.info("\nEnter mail id : ");
            String cust_mail = scanner.nextLine();
            try {
            	List<Product> productList=new ArrayList<>();
                if (customerDAO.checkMail(cust_mail)) {
                    customer.setCust_mail(cust_mail);
                    log.info("Enter Password : ");
                    String cust_password = scanner.nextLine();
                    if (customerDAO.checkPassword(cust_password)) {
                        customer.setCust_mail(cust_mail);
                        customerLogin= true;
                        Thread.sleep(1000);
                        log.info("\nLogin Sucessfull!!!");
                        Thread.sleep(1000);
                        log.info("Please wait...Redirecting to your ProductList!");
                        Thread.sleep(1000);
                    }
                    else{
                    	log.info("Mail or password incorrect try again!");
                    }
                }
                else {
                	log.info("This user name does bot exist");
                }
            } catch (BusinessException | InterruptedException e) {
                log.warn(e.getMessage());
            }
        } while (!c);
    }
}