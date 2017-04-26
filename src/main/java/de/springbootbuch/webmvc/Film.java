package de.springbootbuch.webmvc;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
public class Film {
	private final String id;

	private final String title;

	private final Year releaseYear;

	private final List<Actor> actors = new ArrayList<>();

	public Film(String title, Year releaseYear) {
		this(UUID.randomUUID().toString(), title, releaseYear);
	}
	
	Film(final String id, String title, Year releaseYear) {
		this.id = id;
		this.title = title;
		this.releaseYear = releaseYear;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Year getReleaseYear() {
		return releaseYear;
	}

	public List<Actor> getActors() {
		return actors;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Film other = (Film) obj;
		return Objects.equals(this.id, other.id);
	}
}
