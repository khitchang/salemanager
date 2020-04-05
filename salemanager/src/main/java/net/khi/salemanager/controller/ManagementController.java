package net.khi.salemanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.khi.salemanager.util.FileUploadUtility;
import net.khi.salemanager.validator.ProductValidator;
import net.khi.salemanagerbackend.dao.CategoryDAO;
import net.khi.salemanagerbackend.dao.ProductDAO;
import net.khi.salemanagerbackend.dto.Category;
import net.khi.salemanagerbackend.dto.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory
			.getLogger(ManagementController.class);

	@RequestMapping(value = "/products")
	public ModelAndView showManageProducts(
			@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", "true");
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();

		// set a few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {

				mv.addObject("message", "Product Submitted Successfuly!!");

			}
			  else if (operation.equals("category")) {
			  
			  mv.addObject("message", "Category Submitted Successfuly!!"); }
			 
		}

		return mv;

	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSoumission(
			@Valid @ModelAttribute("product") Product mProduct,
			BindingResult results, Model model, HttpServletRequest request) {

		System.out.println(" avant toute validation  " + mProduct.toString());

		if (mProduct.getId() == 0) {
			// handle image validation for new products new

			System.out.println("1 error :::::  "+results.toString());
			
			new ProductValidator().validate(mProduct, results);

		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {

				System.out.println("2 error :::::  "+results.getErrorCount());

				new ProductValidator().validate(mProduct, results);
			}

		}

		logger.info(mProduct.toString());
		logger.debug(mProduct.toString());

		if (mProduct.getId() == 0) {
			System.out
					.println("//////////////////////////////////////////////////////////////////////////////////////managerController  Id Pro for create "
							+ mProduct.getId());
			// create a new product record
			//productDAO.add(mProduct);
		} else {
			System.out
					.println("////////////////////////////////////////////////////////////////////////////////////// managerController  Id Pro for update "
							+ mProduct.getId()); 
			// update the product if id not  0
			//productDAO.update(mProduct);
		}
		
		

		if (results.hasErrors()) {
			model.addAttribute("userClickManageProduits", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message",
					"Validation failed for Product Submission");

			return "page";
		}


		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFilde(request, mProduct.getFile(),
					mProduct.getCode());

		}

		return "redirect:/manage/products?operation=product";
	}

	/*
	 * // handling product submission
	 * 
	 * @RequestMapping(value = "/products", method = RequestMethod.POST) public
	 * String handleProductSoumission(@Valid @ModelAttribute("product") Product
	 * mProduct, BindingResult results, Model model, HttpServletRequest request)
	 * { System.out.println(" avant toute validation  "+mProduct.toString()); if
	 * (mProduct.getId()==0) {
	 * 
	 * // handle image validation for new products new
	 * ProductValidator().validate(mProduct, results);
	 * 
	 * } else { if(!mProduct.getFile().getOriginalFilename().equals("") ){
	 * 
	 * new ProductValidator().validate(mProduct, results); }
	 * 
	 * }
	 * 
	 * // check if there are any errors if(results.hasErrors()){
	 * 
	 * model.addAttribute("userClickManageProduits",true);
	 * model.addAttribute("title","Manage Products");
	 * model.addAttribute("message","Validation failed for Product Submission");
	 * 
	 * return "page"; }
	 * 
	 * logger.info(mProduct.toString());
	 * 
	 * if (mProduct.getId() == 0) { System.out.println(
	 * "//////////////////////////////////////////////////////////////////////////////////////  Id Pro for create "
	 * +mProduct.getId()); // create a new product record
	 * productDAO.add(mProduct); } else { System.out.println(
	 * "//////////////////////////////////////////////////////////////////////////////////////  Id Pro for update "
	 * +mProduct.getId()); // update the product if id not 0
	 * productDAO.update(mProduct); }
	 * 
	 * if(!mProduct.getFile().getOriginalFilename().equals("")){
	 * FileUploadUtility.uploadFilde(request, mProduct.getFile(),
	 * mProduct.getCode());
	 * 
	 * }
	 * 
	 * return
	 * "redirect:/manage/products?operation=product&id="+mProduct.getId(); }
	 */

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", "true");
		mv.addObject("title", "Manage Products");

		// fetch the product from the database
		Product nProduct = productDAO.get(id);
		System.out
				.println("Methode ShowEditProduct du Mngcontroller à mettre à jour :::::::::::::::::::::::::--------------- Product : "
						+ nProduct.toString());

		// set the products fetch from database
		mv.addObject("product", nProduct);

		return mv;

	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {

		// is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();

		// activating and deactivating on the value of the value of active field
		product.setActive(!product.isActive());

		// updating the product
		productDAO.update(product);

		return (isActive) ? "You have succesfully deactivatived the product with id "
				+ product.getId()
				: "You have succesfully activatived the product with id  "
						+ product.getId();
	}

	// to handle category submission
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {

		categoryDAO.add(category);

		return "redirect:/manage/products?operation=category";
	}

	// returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {

		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category getCategory() {

		return new Category();
	}

}
