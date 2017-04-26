package de.springbootbuch.webmvc;

import java.io.IOException;
import java.time.Year;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RunWith(SpringRunner.class)
@JsonTest
public class FilmSerializerIT {
	
	@Autowired
	private JacksonTester<Film> json;

	@Test
	public void testSomeMethod() throws IOException {
		final Film film = new Film(
			"some-id", "Bandits", Year.of(1997));
		assertThat(json.write(film))
			.isEqualToJson("/film.json");
	}
}