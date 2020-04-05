package net.khi.salemanagerbackend.dao;

import java.util.List;

import net.khi.salemanagerbackend.dto.Cart;
import net.khi.salemanagerbackend.dto.CartLine;
import net.khi.salemanagerbackend.dto.OrderDetail;

public interface CartLineDAO {
	
	// the common methods from previously coded one
	public CartLine get(int id);
	
	public boolean add(CartLine cartLine);
	
	public boolean update(CartLine cartLine);
	
	public boolean delete(CartLine cartLine);
	
	public List<CartLine> list(int cartId);  
	
	
	// Other business method related to the cart lines

	public List<CartLine> listAvailable(int cartId);  
	
	public CartLine getByCartAndProduct(int cartId, int productId);  
	
	// updateCart a cart
	public boolean updateCart(Cart cart);

	boolean addOrderDetail(OrderDetail orderDetail);
	

}
