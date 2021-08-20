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
		ProductDAO productDAO = new ProductDAOImpl();
		MainClass mainClass = new MainClass();
		CartDAO cartDAO = new CartDAOImpl();
		CustomerDAO customerDAO = new CustomerDAOImpl();
		int choice = 0;
		int option=0;
		Scanner scanner = new Scanner(System.in);
		List<Product> productList = new ArrayList<>();
		List<Customer> customerList = new ArrayList<>();
		do {
			log.info("+-------------------------------- +");
			log.info("|    Welcome To Shopper's Shop    |");
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
			log.info("| 4. Add Product                  |");
			log.info("| 5. View all products            |");
			log.info("| 6. Seach Product                |");
			log.info("| 7. Go to Cart Menu              |");
			log.info("+---------------------------------+");
			log.info("");
			log.info("Choose your option...");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn(e.getMessage());
			}

			switch (choice) {

			case 1:
				// customerLogin.registerCustomer();
				int c = 0;
				do {
					// Customer customer = new Customer();
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
					// Customer customer = new Customer();
					log.info("");
					log.info("+-------------------------------+");
					log.info("|         Login As Customer        |");
					log.info("+-------------------------------+");
					log.info("Welcome To Shopper's Shop!");
					log.info("Please enter your details to Login");
					log.info("\nEnter mail id : ");
					String cust_mail = scanner.nextLine();
					try {
						// List<Product> productList=new ArrayList<>();
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
				break;
			case 3:
				log.info("Under Construction!");
				break;
			case 4:
				Product product = new Product(124, "watch", "Electronic", 3000);

				try {
					if (productDAO.addProduct(product) == 1) {
						log.info("Product added successfully");
						log.info("");
						log.info(product);
						log.info("");
					}
				} catch (BusinessException e) {
					log.error(e.getMessage());
				}
				break;
			case 5:
				log.info("The list of all product are given below!");
				log.info("");
				try {
					productList = productDAO.getAllProducts();
					for (Product p : productList) {
						log.info(p);
					}
				} catch (BusinessException e) {
					log.error(e);
				}
				break;
			case 6:
				do {
			
				ProductService productService = new ProductServiceImpl();
				log.info("+------------------------------+");
				log.info("|    Welcome to Search Menu!   |");
				log.info("+------------------------------+");
				log.info("| 1. Search By Name            |");
				log.info("| 2. Search By Price           |");
				log.info("| 3. Going to the Main Menu    |");
				log.info("| Please enter your choice(1-3)|");
				log.info("+------------------------------+");

				try {
					option = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
				}
				switch (option) {
				case 1:
					log.info("Enter name of the product to get detail!");
					try {
						String product_name = scanner.nextLine();
						productList = productService.getProductByName(product_name);
						if (productList.size() != 0) {
							log.info("Product with name " + product_name
									+ " found successfully... Below is the details");
							log.info("");
							log.info(productList);
							log.info("");
						}
					} catch (NumberFormatException e) {
						log.warn("Player Id should be digit only... Try Again");
					} catch (BusinessException e) {
						log.warn(e.getMessage());
					}
					break;
					case 2:
						break;
					case 3:
						break;
				    }
				}while(option!=3);
				case 7:
					
					log.info("+--------------------------------+");
					log.info("|      Welcome to Cart Menu!     |");
					log.info("+--------------------------------+");
					log.info("| 1. View cart                   |");
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
							cartDAO.viewCart(customer);
							for (Customer c1 : customerList) {
								log.info(c1);
							}
						} catch (BusinessException e) {
							log.error(e);
						}
						break;
					case 2:
						log.info("Enter product to add into cart!");
						
						Cart cart = new Cart(18,169,258,"added");
						try {
							cartDAO.addToCart(cart);
							log.info("Product added below data show!");
							log.info(cart);
						} catch (BusinessException e) {
							log.error(e);
						}
						break;
					case 3:
						log.info("Product remove from cart!");
						int cart_id = Integer.parseInt(scanner.nextLine());
						try {
							cartDAO.removeFromCart(cart_id);
						} catch (BusinessException e) {
							log.error(e);
						}

					default:
						break;
					}
					break;

			default:
				log.info("Enter a valid choice to login to select Account type");
			}
		} while (choice != 7);

		scanner.close();
	}

}
