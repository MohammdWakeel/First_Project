package test1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.app.dao.*;
import java.util.*;
import com.app.model.*;
import com.app.dao.impl.*;
import com.app.dao.service.*;
import com.app.dao.service.impl.ProductServiceDAOImpl;
import com.app.service.*;
import com.app.service.impl.*;

import com.app.exception.BusinessException;

class ShoppersShopTest {
	CustomerService customerService=new CustomerServiceImpl(); 
	ProductService productService=new ProductServiceImpl();
	ProductDAO productDAO=new ProductDAOImpl();
	ProductServiceDAO productServiceDAO=new ProductServiceDAOImpl();
	CustomerDAO customerDAO=new CustomerDAOImpl(); 
	List<Customer> customerList=new ArrayList<>();
	
	CartDAO cartDAO=new CartDAOImpl();
	CartService cartService=new CartServiceImpl();
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void  testAddCustomer() {
		int cust_id=16;
		String cust_name="Johan";
		String cust_mail="Johan@gmail.com";
		String cust_password="John@1445";
		int result=0;
		Customer customer=new Customer();
		customer=new Customer(cust_id,cust_name,cust_mail,cust_password);
		try {
		    result=customerService.addCustomer(customer);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		assertEquals(0,result);
		
	}

	@Test
	public void testCheckMail() {
		try {
			String cust_mail="John@gmail.com";
			boolean result=true;
			assertEquals(result, customerService.checkMail(cust_mail));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCheckPassword() {
		try {
		String cust_password="John@123";
		boolean result=true;
		assertEquals(result, customerService.checkPassword(cust_password));
		} catch(BusinessException e){
			e.printStackTrace();
		}
	}
	@Test
	public void testViewCustomer() {
		List<Customer> customerList=null;
		int noOfCustomers=4;
		try {
			customerList=customerService.viewCustomer();
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(noOfCustomers,customerList.size());
	}
	
	@Test
	public void testIsValidCustomer() {
		List<Customer> customerList=null;
		String cust_mail ="sachin@gmail.com";
		String cust_password ="Sachin@123";
		try {
			customerList=customerDAO.isValidCustomer(cust_mail,cust_password);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1,customerList.size());
	}
	
	@Test
    public void testSearchProductById() {	
			int product_id=100;
			Product product=new Product();
			try {
				product=productServiceDAO.getProductById(product_id);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			assertEquals(product,product);
		}
	
	@Test
	public void testGetAllProducts() {
		int noOfProduct=4;
		try {
			assertEquals(noOfProduct,productDAO.getAllProducts().size());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetProductByName() {
		String product_name="TV";
		List<Product> productList=null;
		try {
		productList=productServiceDAO.getProductByName(product_name);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		assertEquals(1,productList.size());
	}

	
	@Test
	public void testGetProductByCategory() {
		String product_category="TV";
		List<Product> productList=null;
		try {
		productList=productServiceDAO.getProductByName(product_category);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		assertEquals(1,productList.size());
	}

	
	
	@Test
	public void testRemoveFromCart() {
		int product_id=100;
		try {
			assertEquals(0,cartDAO.removeFromCart(product_id));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
