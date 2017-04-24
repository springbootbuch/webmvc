package de.springbootbuch.webmvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Component
@SessionScope
public class ShoppingCart {
	private final List<Film> filmsToRent
		= new ArrayList<>();
	
	public void add(final Film film) {
		this.filmsToRent.add(film);
	}
	
	public void removeById(final String id) {		
		this.filmsToRent
			.removeIf(film -> film.getId().equals(id));
	}
	
	public List<Film> getContent() {
		return Collections
			.unmodifiableList(filmsToRent);
	}
}