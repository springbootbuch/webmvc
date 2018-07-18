package de.springbootbuch.webmvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.MappingMediaTypeFileExtensionResolver;

/**
 * Part of springbootbuch.de.
 *
 * This is a workaround for
 * <code>org.springframework.web.servlet.view.ContentNegotiatingViewResolver#getCandidateViews(java.lang.String, java.util.Locale, java.util.List)</code>
 * and in detail <code>this.contentNegotiationManager.resolveFileExtensions(requestedMediaType)</code>.
 * With the changes in <a href="https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-pathmatch">Path Matching and Content Negotiation</a>
 * there is no longer a MediaTypeFileExtensionResolver registered, that is able to resolve a view like films.csv.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Configuration
@Profile("gh-13424-workaround")
public class WorkaroundViewResolverConfig {
	@Bean
	public BeanPostProcessor contentNegotiationManagerPostProcessor(final WebMvcProperties webMvcProperties) {
		return new BeanPostProcessor() {
			@Override
			public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
				if(ContentNegotiationManager.class.isInstance(bean)) {
					((ContentNegotiationManager)bean)
						.addFileExtensionResolvers(new MappingMediaTypeFileExtensionResolver(webMvcProperties.getContentnegotiation().getMediaTypes()));
				}
				return bean;
			}
		};
	}
}
