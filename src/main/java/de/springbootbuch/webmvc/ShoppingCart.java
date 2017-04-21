package de.springbootbuch.webmvc;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.stream.Collectors.toList;
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
	private final List<Film> filmsToRent;

	public ShoppingCart() {
		this.filmsToRent = ThreadLocalRandom.current()
			.ints(10, 1900, 2020)
			.mapToObj(
				i -> new Film("Best of " + i, Year.of(i)))
			.collect(toList());
	}
	
	public void addFilm(final Film film) {
		this.filmsToRent.add(film);
	}
	
	public List<Film> getFilmsToRent() {
		return Collections
			.unmodifiableList(filmsToRent);
	}
}