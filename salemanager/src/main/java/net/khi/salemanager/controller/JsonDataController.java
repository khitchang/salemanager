package net.khi.salemanager.controller;

import java.util.List;

import net.khi.salemanagerbackend.dao.ProductDAO;
import net.khi.salemanagerbackend.dto.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/all/products")
	@ResponseBody
	 public List<Product> getAllProduits(){
		 
		 return productDAO.listActiveProducts(); 
	 }
	
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	 public List<Product> getAllProduitsForAdmin(){
		 
		 return productDAO.list(); 
	 }
	
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	 public List<Product> getAllActiveProductsByCategory(@PathVariable int id){
		 
		 return productDAO.listActiveProductsByCategory(id); 
	 }
	
	
	
	@RequestMapping("/mv/products")
	@ResponseBody
	public List<Product> getMostViewedProducts() {		
		return productDAO.getProductsByParam("views", 5);				
	}
		
	@RequestMapping("/mp/products")
	@ResponseBody
	public List<Product> getMostPurchasedProducts() {		
		return productDAO.getProductsByParam("purchases", 5);				
	}
	
	
	
	
	
	
	
}
