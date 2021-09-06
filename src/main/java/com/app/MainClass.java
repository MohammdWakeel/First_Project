package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import com.app.exception.BusinessException;
import com.app.model.*;
import com.app.model.Customer;
import com.app.model.CustomerLogin;
import com.app.dao.*;
import com.app.dao.impl.*;
import com.app.service.*;
import com.app.service.impl.*;
import com.app.model.Product;
import com.app.service.ProductService;
import com.app.service.impl.ProductServiceImpl;
import com.app.service.CustomerService;
import com.app.service.impl.CustomerServiceImpl;

public class MainClass {

	private static final Logger log = Logger.getLogger(MainClass.class);

	public static void main(String[] args) {

		CustomerService customerService = new CustomerServiceImpl();
		Customer customer = new Customer();
		Cart cart=new Cart();
		ProductDAO productDAO = new ProductDAOImpl();
		ProductService productService = new ProductServiceImpl(); 
		CartDAO cartDAO = new CartDAOImpl();
		CustomerDAO customerDAO = new CustomerDAOImpl();
		int choice = 0;
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		List<Cart> cartList=new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		List<Customer> customerList = new ArrayList<>();
		do {
			log.info("+-------------------------------- +");
			log.info("|    Welcome To SHOPPER'S SHOP    |");
			log.info("+---------------------------------+");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.warn(e.getMessage());
			}
			log.info("|                                 |");
			log.info("| 1. New Customer Registration    |");
			log.info("| 2. Customer Login               |");
			log.info("| 3. Employee Login               |");
			log.info("| 4. log Out                      |");
			log.info("|                                 |");
			log.info("|    Choose your option...        |");
			log.info("+---------------------------------+");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn(e.getMessage());
			}

			switch (choice) {
			case 1:
				int c = 0;
				do {
					log.info("");
					log.info("+-----------------------------+");
					log.info("| Register As New Customer    |");
					log.info("+-----------------------------+");
					log.info("| Hello..!!                   |");
					log.info("|                             |");
					log.info("| Enter Your Name :           |");
					log.info("+-----------------------------+");

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
								if (c == 1) {
									Thread.sleep(1500);
									log.info("Your account has been created!");
								}
							}
						}
					} catch (BusinessException | InterruptedException e) {
						log.warn(e.getMessage());
						log.info("");
					}
				} while (c != 1);
				break;
			case 2:
				boolean check = false;
				do {
					log.info("");
					log.info("+-------------------------------+");
					log.info("|       Login As Customer       |");
					log.info("+-------------------------------+");
					log.info("| Welcome To Shopper's Shop!    |");
					log.info("| Please enter your details to Login");
					log.info("|                               |");
					log.info("| Enter mail id :               |");
					log.info("+-------------------------------+");
					String cust_mail = scanner.nextLine();
					try {
						if (customerDAO.checkMail(cust_mail)) {
							customer.setCust_mail(cust_mail);
							log.info("Enter Password : ");
							String cust_password = scanner.nextLine();
							if (customerDAO.checkPassword(cust_password)) {
								customer.setCust_mail(cust_mail);
								// check=true;
								Thread.sleep(1000);
								log.info("\nLogin Sucessfull!!!");
								Thread.sleep(1000);
								log.info("Please wait...Redirecting to your Menu!");
							} else {
								log.info("Mail or password incorrect try again!");
							}
						} else {
							log.info("This user name does bot exist");
						}
					} catch (BusinessException | InterruptedException e) {
						log.warn(e.getMessage());
					}
				} while (check == true);
				do {
					log.info("+----------------------------------+");
					log.info("|     Welcome to Customer Menu     |");
					log.info("+----------------------------------+");
					log.info("| What you wanna to do today?      |");
					log.info("| 1. Search Product                |");
					log.info("| 2. Add Product                   |");
					log.info("| 3. View Cart                     |");
					log.info("| 4. Goto Main Menu                |");
					log.info("|                                  |");
					log.info("| Enter Your choice(1-3)           |");
					log.info("+----------------------------------+");

					try {
						option = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
					}
					switch (option) {
					case 1:
						do {
							log.info("+----------------------------------+");
							log.info("|  Welcome to Product Search Menu  |");
							log.info("+----------------------------------+");
							log.info("| 1. Search Product By id          |");
							log.info("| 2. Search Product By Category    |");
							log.info("| 3. Search Product By Name        |");
							log.info("| 4. Going to Customer Menu        |");
							log.info("|                                  |");
							log.info("|    Enter Your choice(1-4)        |");
							log.info("+----------------------------------+");
							try {
								option = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn(e.getMessage());
							}
							switch (option) {
							case 1:
								log.info("Enter Product id to search product!");
								try {
									int product_id = Integer.parseInt(scanner.nextLine());
									Product product = productService.getProductById(product_id);
									if (product != null) {
										log.info("Product with id " + product_id + " found successfully... Below is the details");
										log.info(product);
									}
								} catch (NumberFormatException e) {
									log.warn("Product Id should be digit only... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
							case 2:
								log.info("Enter product Category to get product details!");
								try {
									String product_category = scanner.nextLine();
									//List<Product> productList = new ArrayList<>();
									productList = productService.getProductByCategory(product_category);
									if (productList != null && productList.size() > 0) {
										log.info("Product with category" + product_category + " found given below with details...!");
										log.info(productList);
									}
								} catch (NumberFormatException e) {
									log.warn("Product category should be String,please try again...!");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
							case 3:
								log.info("Enter product Name to get product details!");
								try {
									String product_name = scanner.nextLine();
									//List<Product> productList = new ArrayList<>();
									productList = productService.getProductByName(product_name);
									if (productList != null && productList.size() > 0) {
										log.info("Product with " + product_name + " found given below with details...!");
										log.info(productList);
									}
								} catch (NumberFormatException e) {
									log.warn("Product id should be integer,please try again...!");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
							case 4:
								log.info("Going Back to Customer Menu!");
								break;
							default:
								break;
							}
						} while (option != 4);
						break;

					case 2:
						log.info("Enter product to add into List!");
						Product product=null;
						try {
							productDAO.addProduct(product);
						}
						catch (BusinessException e) {
							log.error(e);
						}
						break;

					case 3:
						do {
							log.info("+--------------------------------+");
							log.info("|      Welcome to Cart Menu!     |");
							log.info("+--------------------------------+");
							log.info("| 1. View cart product           |");
							log.info("| 2. Add into the Cart           |");
							log.info("| 3. Remove from Cart            |");
							log.info("| 4. Return to main menu         |");
							log.info("|    Enter your choice(1-4)      |");
							log.info("+--------------------------------+");
							try {
								option = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn(e.getMessage());
							}
							switch (option) {
							case 1:
								log.info("Cart product are Below!");
								try {
									cartList=cartDAO.viewCart(customer);
									/*for (Customer c1 : customerList) {
										log.info(c1);
									}*/
									if(cartList!=null && cartList.size()>0) {
							
										for (Cart c1 : cartList) {
										  System.out.println(c1);
									    }
									}
								} catch (BusinessException e) {
									log.error(e);
								}
								break;
							case 2:
								int cart_id;
								try {
									cart_id=cartDAO.addToCart(cart);
									if (cart_id==1) {
										log.info("Product id "+cart.getProduct_id()+" is added in your cart");
									}	
								} catch (BusinessException | NumberFormatException e) {
									log.warn(e.getMessage());
								}
								break;
							case 3:
								log.info("Enter id of product to remove from cart!");
								int product_id= Integer.parseInt(scanner.nextLine());
								   
								try {
									cartDAO.removeFromCart(product_id);
								} catch (BusinessException e) {
									log.error(e);
								}
								break;
							case 4:
								log.info("Going Back to Customer Menu!");
								break;
							default:
								break;
							}
						} while (option != 4);
						break;
						
					case 4:
						log.info("Go to Main Menu!");
						break;
					default:
						break;
					}
				} while (option != 3);
				break;
			case 3:
				log.info("Employee Login page is under construction!");
				break;
			case 4:
				log.info("You are log out successfuly!");
				break;
			default:
				log.info("Invalid choice, Choose number between 1 and 4.");
				break;
			}
		} while (option != 4);
		scanner.close();
	}
}

