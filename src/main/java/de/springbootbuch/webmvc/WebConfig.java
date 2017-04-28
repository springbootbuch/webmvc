package de.springbootbuch.webmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile(value = "only-for-example-inside-book")
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(
		ContentNegotiationConfigurer configurer
	) {
		configurer
			.mediaType("csv", MediaType
				.parseMediaTypes("text/csv").get(0))
			.favorParameter(false)
			.favorPathExtension(true)
			.useRegisteredExtensionsOnly(true);
	}

	@Override
	public void addCorsMappings(CorsRegistry reg) {
		reg.addMapping("/api/**").allowedOrigins("*");
	}

}
