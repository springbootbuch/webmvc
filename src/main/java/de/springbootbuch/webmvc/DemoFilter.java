package de.springbootbuch.webmvc;

import java.io.IOException;
import javax.inject.Provider;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Component
public class DemoFilter implements Filter {
	private static final Logger LOG = LoggerFactory
		.getLogger(DemoFilter.class);
	
	private final Provider<ShoppingCart> shoppingCart;

	public DemoFilter(
		Provider<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	// Init und Destroy der Übersicht 
	// halber nicht gezeigt
	
	@Override
	public void init(
		FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(
		ServletRequest request, 
		ServletResponse response, 
		FilterChain chain
	) throws IOException, ServletException {
		chain.doFilter(request, response);
		LOG.info(
			"Shopping cart is {} empty",
			shoppingCart.get()
				.getContent().isEmpty() ? "" : "not" 
		);
	}

	@Override
	public void destroy() {
	}
}