package net.khi.salemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.khi.salemanager.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final static Logger logger = LoggerFactory
			.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@RequestMapping("/show")
	public ModelAndView showCart(
			@RequestParam(name = "result", required = false) String result) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);

		if (result != null) {

			switch (result) {

			case "updated":
				mv.addObject("message", "Cart has been updated successfully!");
				break;

			case "added":
				mv.addObject("message",
						"Product has been successfully added inside cart!");
				// cartService.validateCartLine();
				break;
			case "deleted":
				mv.addObject("message",
						"CartLine has been successfully removed!");
				break;
			case "unavailable":
				mv.addObject("message", "Product quantity is not available!");
				break;

			case "modified":
				mv.addObject("message",
						"One or more items inside cart has been modified!");
				break;
			case "maximum":
				mv.addObject("message",
						"Maximum limit for the item has been reached!");
				break;

			case "error":
				mv.addObject("message", "Something went wrong!! ");
				break;

			}

		} else {
			String response = cartService.validateCartLine();

			if (response.equals("result=modified")) {
				mv.addObject("message","One or more items inside cart has been modified!");
			}
		}
		mv.addObject("cartLines", cartService.getCartLines());
		logger.info("INFO CART CONTROLLER :");
		return mv;
	}

	

	@RequestMapping("/{cartLineId}/update")
	public String updateCartLine(@PathVariable int cartLineId,
			@RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count); 
		return "redirect:/cart/show?" + response;
	}
	

	@RequestMapping("/{cartLineId}/delete")
	public String removeCartLine(@PathVariable int cartLineId) {
		String response = cartService.removeCartLine(cartLineId);
	
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/add/{productId}/product")
	public String addCartLine(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		System.out
				.println("--------------------------------------------------------- addCart  valeur result   "
						+ response);
		return "redirect:/cart/show?" + response;
	}
	
	
	
	/* after validating it redirect to checkout
	 * if result received is success proceed to checkout 
	 * else display the message to the user about the changes in cart page
	 * */	
	@RequestMapping("/validate")
	public String validateCart() {	
		String response = cartService.validateCartLine();
		if(!response.equals("result=success")) {
			return "redirect:/cart/show?"+response;
		}
		else {
			return "redirect:/cart/checkout";
		}
	}	
	

}
