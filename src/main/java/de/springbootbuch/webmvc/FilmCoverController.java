package de.springbootbuch.webmvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.util.concurrent.TimeUnit.DAYS;
import javax.servlet.http.Part;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import static org.springframework.util.DigestUtils.md5DigestAsHex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Controller
@RequestMapping(path = "/films")
public class FilmCoverController {
	private final FilmService filmService;

	public FilmCoverController(
		FilmService filmService
	) {
		this.filmService = filmService;
	}
	
	@PostMapping("/addCover")
	public String addCover(
			@RequestParam String id,
			@RequestParam Part cover
	) throws IOException {
		Film film = filmService.getFilm(id);
		Path coverImage = Files.createTempFile("cover", ".jpg");
		Files.copy(
			cover.getInputStream(), 
			coverImage, 
			StandardCopyOption.REPLACE_EXISTING);
		film.setCover(new Cover(
			coverImage.toString(),
			md5DigestAsHex(cover.getInputStream())
		));
		return "redirect:/films";
	}
	
	@GetMapping("/{id}/cover")
	public ResponseEntity<Resource> showCover(
		@PathVariable String id
	) throws IOException  {
		Film film = filmService.getFilm(id);
		
		final Resource cover;
		final String etag;
		CacheControl cacheControl = CacheControl.noCache();
		if(film.getCover() == null) {
			cover = new ClassPathResource(
				"/reel.jpg");
			etag = md5DigestAsHex(cover.getInputStream());
		} else {
			cover = new PathResource(
				Paths.get(film.getCover().getPath()));
			cacheControl = CacheControl.maxAge(30, DAYS);
			etag = film.getCover().getHash();
		}

		return ResponseEntity
			.ok()
			.cacheControl(cacheControl)
			.lastModified(cover.lastModified())
			.eTag(etag)
			.contentType(MediaType.IMAGE_JPEG)
			.body(cover);
	}
}
