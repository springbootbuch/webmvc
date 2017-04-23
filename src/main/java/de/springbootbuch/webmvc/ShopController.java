package de.springbootbuch.webmvc;

import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Controller
public class ShopController {
	
	private final ObjectFactory<ShoppingCart> shoppingCart;

	public ShopController(
		ObjectFactory<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	@GetMapping("/cart")
	@ResponseBody
	public List<Film> cart() {	
		return this.shoppingCart
			.getObject().getFilmsToRent();
	}
}