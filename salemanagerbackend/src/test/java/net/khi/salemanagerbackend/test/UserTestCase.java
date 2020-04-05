package net.khi.salemanagerbackend.test;

import static org.junit.Assert.assertEquals;
import net.khi.salemanagerbackend.dao.UserDAO;
import net.khi.salemanagerbackend.dto.Address;
import net.khi.salemanagerbackend.dto.Cart;
import net.khi.salemanagerbackend.dto.User;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO; 
	
	private  User user=null;
	private  Address address=null;
	private  Cart cart=null;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.khi.salemanagerbackend");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
			
	}
	
/*	
	@Test
	public void testAdd(){
		
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setPassword("123456");
		user.setRole("USER");
		
		
		//add the user
		
		//assertEquals("Failed to add user",true,userDAO.addUser(user)); 
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setState("Muharashtra");
		address.setBilling(true);
		
		//Link the user with the address using user id
		address.setUserId(user.getId());
		
		//add the address
		
		assertEquals("Failed to add address",true,userDAO.addAddress(address)); 
		
		if (user.getRole().equals("USER")) {
			
			//create a cart to the user
			cart = new Cart();
			cart.setUser(user); 
			
			user.setCart(cart);
			
			// add the cart
			assertEquals("Failed to add cart",true,userDAO.addUser(user)); 
			
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setCountry("India");
			address.setPostalCode("400001");
			address.setState("Muharashtra");
			//set shipping to true
			address.setShipping(true);
			
			//link it with the user
			address.setUserId(user.getId());			

			// add the shipping address
			assertEquals("Failed to add shipping address",true,userDAO.addAddress(address));
 	    }
		
 }  */
	
	
/*	
	@Test
	public void testAdd(){
		
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setPassword("123456");
		user.setRole("USER");
		
		if (user.getRole().equals("USER")) {
			
			//create a cart to the user
			cart = new Cart();
			cart.setUser(user); 
			
			user.setCart(cart);
			
			// add the cart
			assertEquals("Failed to add user",true,userDAO.addUser(user));
	     }
		
     }*/
/*	
	@Test
	public void testUpdateCart(){
		// fetch the user by this email
		   user = userDAO.getByEmail("hr@gmail.com"); 
			
			//get a cart to the user
			cart = user.getCart(); 
			
			cart.setGrandTotal(99999999);
			cart.setCartLines(5);
			
			// add the cart
			//assertEquals("Failed to update the cart",true,userDAO.updateCart(cart));
      }*/
	
	
	@Test
	public void testAddAddress(){


			//add the user
			user = new User();
			user.setFirstName("Hrithik");
			user.setLastName("Roshan");
			user.setEmail("hr@gmail.com");
			user.setContactNumber("1234512345");
			user.setPassword("123456");
			user.setRole("USER");

			assertEquals("Failed to add user",true,userDAO.addUser(user)); 

			//add the address
			address = new Address();
			address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
			address.setAddressLineTwo("Near Kaabil Store");
			address.setCity("Mumbai");
			address.setCountry("India");
			address.setPostalCode("400001");
			address.setState("Muharashtra");
			address.setBilling(true);
			 
			//attach user to the address
			address.setUserId(user.getId());
			
			assertEquals("Failed to add address",true,userDAO.addAddress(address)); 
			
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setCountry("India");
			address.setPostalCode("400001");
			address.setState("Muharashtra");
			//set shipping to true
			address.setShipping(true);
			
			//link it with the user
			address.setUserId(user.getId());			

			assertEquals("Failed to add address",true,userDAO.addAddress(address)); 
			
      }
	
	
	
	
	
	
}
