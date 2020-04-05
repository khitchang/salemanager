package net.khi.salemanagerbackend.test;

import static org.junit.Assert.assertEquals;
import net.khi.salemanagerbackend.dao.ProductDAO;
import net.khi.salemanagerbackend.dto.Product;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private  Product  product;
	
	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.khi.shoppingbackend");
		context.refresh();		
		productDAO = (ProductDAO)context.getBean("productDAO");

	}
	
	/*	
	@Test
	public void testCRUDPRODUCT(){ 
		
		product= new Product();
		product.setName("Infinix");
		product.setDescription("This is some description  infinix mobile phone");
		product.setUnitPrice(35000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);		 			
	 
	    assertEquals("Sucessfuly deleted a single category from the table!!",true,productDAO.add(product));
	    
		product=productDAO.get(2);
		product.setName("Samsung S7");
		assertEquals("Sucessfuly update a single category from the table!!",true,productDAO.update(product));
	 

		product=productDAO.get(33);
		product.setActive(false);
		assertEquals("Sucessfuly deleted a single category from the table!!",true,productDAO.delete(product));
		
		
		assertEquals("Sucessfuly list a  category from the table!!",7,productDAO.list().size());
		
	
	}
	*/

	
	/*
	
	@Test
	public void testListProduct(){ 
	 
	          assertEquals("Sucessfuly list  a single category from the table!!",3,productDAO.listActiveProductsByCategory(3).size());
	          assertEquals("Sucessfuly list  a single category from the table!!",2,productDAO.listActiveProductsByCategory(1).size());
	
	}
	*/
	
	
	@Test
	public void testCRUDPRODUCT(){ 
		
		product= new Product();
		product.setName("Test");
		product.setDescription("This is some description  test mobile phone");
		product.setUnitPrice(35000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);		 			
	 
	    assertEquals("Sucessfuly deleted a single category from the table!!",true,productDAO.add(product));
	    }
	
	
	
	
	

}
