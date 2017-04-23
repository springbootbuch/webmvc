package de.springbootbuch.webmvc;

import java.util.List;
import javax.inject.Provider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RequestMapping("/shopAlt")
@Controller
public class ShopControllerAlt {
	
	private final Provider<ShoppingCart> shoppingCart;

	public ShopControllerAlt(
		Provider<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	@GetMapping("/cart")
	@ResponseBody
	public List<Film> cart() {
		return this.shoppingCart.get().getFilmsToRent();
	}
}
