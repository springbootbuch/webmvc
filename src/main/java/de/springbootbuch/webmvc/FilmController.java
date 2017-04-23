package de.springbootbuch.webmvc;

import java.time.Year;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Controller
@RequestMapping("/films")
public class FilmController {

	private final FilmService filmService;

	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}

	@GetMapping
	public void index(
		final Model model
	) {
		model.addAttribute(
			"films", filmService.getFilms());
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Film> index() {
		return filmService.getFilms();
	}
	
	@GetMapping("/byYear/{year}")
	public String byYear(
		@PathVariable final int year,
		@RequestParam(required = false) final String q,
		final Locale locale,
		final Model model
	) {
		model.addAttribute(
			"films", filmService.getFilms(
				Year.of(year), Optional.ofNullable(q), locale));
		return "films";
	}
	
	@GetMapping("/byYear2/{year}")
	public String byYear2(
		@PathVariable final Year year,
		@RequestParam(required = false) final Optional<String> q,
		final Locale locale,
		final Model model
	) {
		model.addAttribute(
			"films", filmService.getFilms(year, q, locale));
		return "films";
	}

	@GetMapping("/new")
	FilmForm filmForm() {
		return new FilmForm();
	}

	@PostMapping
	public String addFilm(
		@Valid FilmForm filmForm,
		BindingResult bindingResult,
		final Model model
	) {
		String rv = "films/new";
		if(!bindingResult.hasErrors()) {
			this.filmService
				.addFilm(filmForm.getTitle(), filmForm.getReleaseDate());
			rv = "redirect:/films";
		}
		return rv;
	}
	
	@DeleteMapping("/{id}")
	public String deleteFilm(
		@PathVariable String id
	) {
		this.filmService.deleteFilm(id);
		return "redirect:/films";
	}
}