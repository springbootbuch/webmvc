package de.springbootbuch.webmvc;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Service
public class FilmService {
	private final List<Film> films = Stream.of(
			new Film("Die Buddy Holly Story", Year.of(1978)),
			new Film("8 Mile", Year.of(2002)),
			new Film("Armageddon", Year.of(1997)),
			new Film("Bandits", Year.of(1997)),
			new Film("Walk The Line", Year.of(2005))
		)
		.sorted(Comparator.comparing(Film::getReleaseYear))
		.collect(toList());

	List<Film> getFilms() {
		return Collections.unmodifiableList(films);
	}

	List<Film> getFilms(
		final Year year,
		final Optional<String> q,
		final Locale locale
	) {
		
		return this.films.stream()
			.filter(f -> f.getReleaseYear().equals(year))
			.filter(f -> q.map(s -> f.getTitle().contains(s.toLowerCase(locale))).orElse(true))
			.collect(toList());
	}

	public Film addFilm(final String title, final LocalDate releaseDate) {
		final Film film = new Film(
			title, releaseDate.query(t -> Year.of(t.get(ChronoField.YEAR))));
		this.films.add(film);
		return film;
	}

	public Film getFilm(String id) {
		return this.films.stream()
			.filter(f -> f.getId().equals(id))
			.findFirst()
			.orElseThrow(NoSuchFilmException::new);
	}	
}