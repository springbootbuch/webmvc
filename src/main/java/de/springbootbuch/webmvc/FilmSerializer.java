package de.springbootbuch.webmvc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@JsonComponent
public class FilmSerializer 
	extends JsonSerializer<Film> {

	@Override
	public void serialize(
		Film film, 
		JsonGenerator gen, 
		SerializerProvider serializers
	) throws IOException, JsonProcessingException {
		gen.writeStartObject();
		gen.writeObjectField("id", film.getId());
		gen.writeObjectField("title", film.getTitle());
		gen.writeObjectField(
			"releaseYear", film.getReleaseYear());
		gen.writeEndObject();
	}
}