package net.khi.salemanager.controller;

import javax.servlet.http.HttpSession;

import net.khi.salemanager.model.UserModel;
import net.khi.salemanagerbackend.dao.UserDAO;
import net.khi.salemanagerbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDAO;

	private UserModel userModel = null;

	private User user = null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {

		// add the user model
		if (session.getAttribute("userModel") == null) {

			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();

			if (!authentication.getPrincipal().equals("anonymousUser")) {

				// get the user from the database
				user = userDAO.getByEmail(authentication.getName());

				if (user != null) {
					// create a new user model object to pass the user details
					userModel = new UserModel();

					userModel.setId(user.getId());
					userModel.setEmail(user.getEmail());
					userModel.setRole(user.getRole());
					userModel.setFullName(user.getFirstName() + " "
							+ user.getLastName());

					if (userModel.getRole().equals("USER")) {
						// set the cart only if user is a buyer
						userModel.setCart(user.getCart());
					}
					// set the userModel in the session
					session.setAttribute("userModel", userModel);

					return userModel;
				}

			}
		}
		return (UserModel) session.getAttribute("userModel");
	}

}
