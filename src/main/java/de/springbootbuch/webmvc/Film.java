package de.springbootbuch.webmvc;

import java.time.Year;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
public class Film {
	private final String title;
	
	private final Year releaseYear;

	public Film(String title, Year releaseYear) {
		this.title = title;
		this.releaseYear = releaseYear;
	}

	public String getTitle() {
		return title;
	}

	public Year getReleaseYear() {
		return releaseYear;
	}
}
