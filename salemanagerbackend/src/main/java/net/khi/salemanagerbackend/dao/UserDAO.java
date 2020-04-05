package net.khi.salemanagerbackend.dao;

import java.util.List;

import net.khi.salemanagerbackend.dto.Address;
import net.khi.salemanagerbackend.dto.User;

public interface UserDAO {

	// user related operation
	 
	User get(int id);
	
	public boolean addUser(User user);

	User getByEmail(String email);

 
	// adding and updating a new address
	Address getAddress(int addressId);
	
	public boolean addAddress(Address address);

	boolean updateAddress(Address address);

	// alternative
	Address getBillingAddress(int userId);

	List<Address> listShippingAddress(int userId);
	
	 
		

	/*
	 * Address getBillingAddress(User user); List<Address>
	 * listShippingAddress(User user);
	 */
	

}
