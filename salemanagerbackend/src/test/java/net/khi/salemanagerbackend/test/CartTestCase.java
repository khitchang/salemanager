package net.khi.salemanagerbackend.test;

import static org.junit.Assert.assertEquals; 
import net.khi.salemanagerbackend.dao.CartLineDAO;
import net.khi.salemanagerbackend.dao.ProductDAO;
import net.khi.salemanagerbackend.dao.UserDAO;
import net.khi.salemanagerbackend.dto.Cart;
import net.khi.salemanagerbackend.dto.CartLine;
import net.khi.salemanagerbackend.dto.Product;
import net.khi.salemanagerbackend.dto.User;
 

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CartTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO = null;

	private static ProductDAO productDAO = null;

	private static UserDAO userDAO = null;

	private Product product = null;

	private User user = null;

	private CartLine cartLine = null;

	private Cart cart = null;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.khi.salemanagerbackend");
		context.refresh();

		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");

		productDAO = (ProductDAO) context.getBean("productDAO");

		userDAO = (UserDAO) context.getBean("userDAO");

	}

	@Test
	public void testAddNewCartLine() {

		// 1. get the User
		user = userDAO.getByEmail("khifamel@gmail.com");

		// 2. fetch the cart
		cart = user.getCart();

		// 3. get the product
		product = productDAO.get(1);

		// 4. create New CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);

		assertEquals("Failed to add the cartline ", true,
				cartLineDAO.add(cartLine));

		// update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed to update the cartline ", true,
				cartLineDAO.updateCart(cart));

	}

}
