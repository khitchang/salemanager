package net.khi.salemanagerbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

//import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address implements Serializable{
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*********************************/
	@Column(name = "user_id")
	private int userId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*******************************/
	
	@Column(name="address_line_one")
	@NotBlank(message="Please enter address Line One!!")
	private String addressLineOne;
	
	@Column(name="address_line_two")
	@NotBlank(message="Please enter address Line Two!!")
	private String addressLineTwo;	

	@NotBlank(message="Please enter the city name !!")
	private String city;

	@NotBlank(message="Please enter state name !!")
	private String state;

	@NotBlank(message="Please enter country !!")
	private String country;
	
	@Column(name="postal_code")	
	@NotBlank(message="Please enter postal code !!")
	private String postalCode;
	
	@Column(name="is_billing")
	private boolean billing;
	
	@Column(name="is_shipping")
	private boolean shipping;
	
	public Address() {
		super();
	}

	public Address(int userId, String addressLineOne, String addressLineTwo,
			String city, String state, String country, String postalCode,
			boolean billing, boolean shipping) {
		super(); 
		this.userId=userId;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.billing = billing;
		this.shipping = shipping;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isBilling() {
		return billing;
	}

	public void setBilling(boolean billing) {
		this.billing = billing;
	}

	public boolean isShipping() {
		return shipping;
	}

	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", addressLineOne="
				+ addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", postalCode=" + postalCode + ", billing="
				+ billing + ", shipping=" + shipping + "]";
	}

	
	

}
