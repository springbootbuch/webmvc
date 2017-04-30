package de.springbootbuch.webmvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchFilmException extends RuntimeException {
	private static final long serialVersionUID = -1469903817218715717L;
}
