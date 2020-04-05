package net.khi.salemanagerbackend.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
 


@Entity
@Table(name = "user_detail")
public class User  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * Private fields for user
	 * ***/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	@NotBlank(message="Please enter fisrt name !!")
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank(message="Please enter last name !!")
	private String lastName;

	@NotBlank(message="Please enter email !!")
	private String email;
	
	@Column(name="contact_number")
	@NotBlank(message="Please enter contact number !!")
	private String contactNumber;

	private String role;
	
	@NotBlank(message="Please enter password !!")
 	private String password;
	
 	private boolean enabled = true; 
 	
 	@Transient
 	private String confirmPassword;
 	

 /********************************************/
 	@OneToOne(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 	private Cart cart;
 	
 	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
/*****************************************/
	public User() {
		super();
	}
	
 	public User(String firstName, String lastName, String email,
			String contactNumber, String role, String password, boolean enabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.password = password;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", role=" + role + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

 	
 	
}
