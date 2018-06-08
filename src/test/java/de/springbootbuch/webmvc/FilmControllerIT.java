package de.springbootbuch.webmvc;

import java.time.Year;
import static java.util.Arrays.asList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_ATOM_XML;
import static org.springframework.http.MediaType.TEXT_HTML;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RunWith(SpringRunner.class)
@WebMvcTest(
	controllers = FilmController.class,
	includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {FilmsAtomView.class, FilmsCsvView.class, WorkaroundViewResolverConfig.class})
)
@MockBean(classes = ShoppingCart.class, name = "shoppingCart")
public class FilmControllerIT {

	@MockBean
	private FilmService filmService;

	@Autowired
	private MockMvc mockMvc;

	private final List<Film> films = asList(
		new Film("test", Year.of(2017)),
		new Film("test2", Year.of(2017))
	);

	@Before
	public void initMocks() {
		when(filmService.getFilms()).thenReturn(films);
	}

	@Test
	public void indexHTMLShouldWork() throws Exception {
		mockMvc
			.perform(get("/films").accept(TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("films"))
			.andExpect(model().attribute("films", films));
	}

	@Test
	public void indexAtomShouldWork() throws Exception {
		mockMvc
			.perform(get("/films").accept(APPLICATION_ATOM_XML))
			.andExpect(status().isOk())
			.andExpect(view().name("films"))
			.andExpect(xpath("/feed/entry").nodeCount(2))
			.andExpect(xpath("/feed/entry[1]/title").string("test"));
	}

	@Test
	public void indexCSVShouldWork() throws Exception {
		mockMvc
			.perform(get("/films").accept("text/csv"))
			.andExpect(status().isOk())
			.andExpect(view().name("films"))
			.andExpect(content().string(""
				+ "test;2017\n"
				+ "test2;2017\n"
			));
	}

	@Test
	public void indexJSONShouldWork() throws Exception {
		mockMvc
			.perform(get("/films").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].title", is("test")))
			;
	}
}
