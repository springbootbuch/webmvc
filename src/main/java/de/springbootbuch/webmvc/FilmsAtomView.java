package de.springbootbuch.webmvc;

import com.rometools.rome.feed.atom.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Component("films.atom")
public class FilmsAtomView extends AbstractAtomFeedView {
	@Override
	protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		final List<Film> films = (List<Film>) model.getOrDefault("films", new ArrayList<>());
		return films.stream().map(film -> {
			final Entry entry = new Entry();
			entry.setTitle(film.getTitle());
			return entry;
		}).collect(toList());
	}
}