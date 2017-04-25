package de.springbootbuch.webmvc;

import java.io.OutputStreamWriter;
import java.io.Writer;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Component("films.csv")
public class FilmsCsvView extends AbstractView {

	public FilmsCsvView() {
		super.setContentType("text/csv");
	}

	@Override
	protected void renderMergedOutputModel(
		Map<String, Object> model, 
		HttpServletRequest request, 
		HttpServletResponse response
	) throws Exception {
		final List<Film> films = (List<Film>) 
			model.getOrDefault("films", new ArrayList<>());
		response.setContentType(UTF_8.name());
		try(Writer out = new OutputStreamWriter(response.getOutputStream(), UTF_8)) {
			for(Film film : films) {
				out.write(film.getTitle());
				out.write(";");
				out.write(film.getReleaseYear().toString());
				out.write("\n");
			}
		}
		response.flushBuffer();
	}
}