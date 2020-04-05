package net.khi.salemanager.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ProductNotFoundException(){
		this("This product is not avalaible!!");	
		
	}
	
	
	public ProductNotFoundException(String message){
		this.message= System.currentTimeMillis() + message	;	
		
	}


	public String getMessage() {
		return message;
	}
	
	

}
