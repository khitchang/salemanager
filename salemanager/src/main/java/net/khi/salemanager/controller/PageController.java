package net.khi.salemanager.controller;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.khi.salemanager.controller.PageController;
import net.khi.salemanager.exception.ProductNotFoundException;
import net.khi.salemanagerbackend.dao.CategoryDAO;
import net.khi.salemanagerbackend.dao.ProductDAO;
import net.khi.salemanagerbackend.dto.Category;
import net.khi.salemanagerbackend.dto.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PageController.class);
	 
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value={"/", "/home", "/index"})
	public ModelAndView index(){
		
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","Home");
		

		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		// passing the list of cagetories
		mv.addObject("categories",categoryDao.list());
		
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/about")
	public ModelAndView about(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	/*9
	@RequestMapping(value="/show/All/Products")
	public ModelAndView listProducts(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("userClickAllProducts",true);
		return mv;
	}*/
	
	
	@RequestMapping(value="/contact")
	public ModelAndView contact(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;
	}
	
	
	
	
	
	/** Methods to load all the products and based on category */

	@RequestMapping(value ="/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// CategoryDAO to fetch a single category
		Category category = null;
		category = categoryDao.get(id);

		mv.addObject("title", category.getName());
		// passing the list of cagetories
		mv.addObject("categories", categoryDao.list());
		// passing the single category object
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	
	/** Methods to load all the products and based on category */

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		
		// passing the list of cagetories
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	

	@RequestMapping(value ="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id)
			throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);

		if (product == null)
			throw new ProductNotFoundException();

		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);

		return mv;
	}
	
	
	/* Having similar mapping to our flow id */
	@RequestMapping(value ="/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");

		logger.info("Page Controller membership called!");
		return mv;
	}
	
	/* Login page */
	@RequestMapping(value = { "/login" })
	public ModelAndView login(
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("title", "Login");

		if (error != null) {
			mv.addObject("message", "Invalid Username and Password");
		}

		if (logout != null) {
			mv.addObject("logout", "User has successfully logged out");
		}

		return mv;
	}

	/* access denied page */
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("errorTitle", "Aha!! - Caught You. ");
		mv.addObject("errorDescription",
				"You are not authorized to view this page!");
		return mv;
	}
	
	/* Logout */
	@RequestMapping(value ="/perform-logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response,
					authentication);
		}
		return "redirect:/login?logout";
	}
	
	
	
	
}
