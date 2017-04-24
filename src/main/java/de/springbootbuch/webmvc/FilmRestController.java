package de.springbootbuch.webmvc;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RestController
public class FilmRestController {
	
	private final FilmService filmService;

	public FilmRestController(
		FilmService filmService
	) {
		this.filmService = filmService;
	}
	
	@GetMapping("/api/films")
	public List<Film> index() {
		return filmService.getFilms();
	}
}