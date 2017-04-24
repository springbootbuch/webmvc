package de.springbootbuch.webmvc;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Controller
public class ShopController {

	private final FilmService filmService;

	private final ObjectFactory<ShoppingCart> shoppingCart;
	
	public ShopController(
		FilmService filmService,
		ObjectFactory<ShoppingCart> shoppingCart		
	) {
		this.filmService = filmService;
		this.shoppingCart = shoppingCart;
	}
	
	@PostMapping("/cart/{id}")
	public String addToCart(
		@PathVariable String id
	) {
		this.shoppingCart.getObject()
			.add(this.filmService.getFilm(id));
		return "redirect:/films";
	}
	
	@DeleteMapping("/cart/{id}")
	public String removeFromCart(
		@PathVariable String id
	) {
		this.shoppingCart.getObject()
			.removeById(id);		
		return "redirect:/films";
	}
}