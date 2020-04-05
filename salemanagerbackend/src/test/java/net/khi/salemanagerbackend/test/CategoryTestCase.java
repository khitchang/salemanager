package net.khi.salemanagerbackend.test;

import static org.junit.Assert.assertEquals; 
import net.khi.salemanagerbackend.dao.CategoryDAO;
import net.khi.salemanagerbackend.dto.Category;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private  Category  category;
	
	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.khi.shoppingbackend");
		context.refresh();		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");

	}
	
	/*@Test
	public void testAddCategory(){ 
		
		category= new Category();
		category.setName("Television");
		category.setDescription("This is some description  for my television");
		category.setImageURL("CAT_1.png");
		
		assertEquals("Succefuly added a category inside the table!!", true, categoryDAO.add(category));
		
	}*/
	
	/*
	@Test
	public void testGetCategory(){ 
	
	category=categoryDAO.get(38);
	assertEquals("Sucessfuly fetched a single category from the table!!","Television",category.getName());
	
	}*/
	
	
	/*
	@Test
	public void testUpdateCategory(){ 
	
	category=categoryDAO.get(38);
	category.setName("TV");
	assertEquals("Sucessfuly update a single category from the table!!",true,categoryDAO.update(category));
	
	}*/
	
	
 /*	@Test
	public void testDeleteCategory(){ 
	
	category=categoryDAO.get(38);
	category.setActive(false);
	assertEquals("Sucessfuly deleted a single category from the table!!",true,categoryDAO.delete(category));
	
	}*/
	
	/*
	@Test
	public void testListCategory(){ 
	 
	assertEquals("Sucessfuly list  a single category from the table!!",12,categoryDAO.list().size());
	
	}
	*/
	
	
	@Test
	public void testCrudCategory(){ 
		
		category= new Category();
		category.setName("Television");
		category.setDescription("This is some description  for my television");
		category.setImageURL("CAT_1.png");
		
		
		category= new Category();
		category.setName("Laptop");
		category.setDescription("This is some description  for my laptop");
		category.setImageURL("CAT_2.png");
		
		category= new Category();
		category.setName("Telephone");
		category.setDescription("This is some description  for my telephone");
		category.setImageURL("CAT_3.png");
		
		category= new Category();
		category.setName("Mobilier");
		category.setDescription("This is some description  for my mobilier");
		category.setImageURL("CAT_4.png");			
	 
	    assertEquals("Sucessfuly deleted a single category from the table!!",12,categoryDAO.list().size());
	
	    category=categoryDAO.get(2);
		assertEquals("Sucessfuly fetched a single category from the table!!","Laptop",category.getName());
		
		category=categoryDAO.get(3);
		category.setName("Mobile Phone");
		assertEquals("Sucessfuly update a single category from the table!!",true,categoryDAO.update(category));

		category=categoryDAO.get(4);
		category.setActive(false);
		assertEquals("Sucessfuly deleted a single category from the table!!",true,categoryDAO.delete(category));
		
		
		assertEquals("Sucessfuly list a  category from the table!!",3,categoryDAO.list().size());
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	

}
