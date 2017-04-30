package de.springbootbuch.webmvc;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
public class Cover {
	private final String path;
	
	private final String hash;

	public Cover(String path, String hash) {
		this.path = path;
		this.hash = hash;
	}

	public String getPath() {
		return path;
	}

	public String getHash() {
		return hash;
	}
}