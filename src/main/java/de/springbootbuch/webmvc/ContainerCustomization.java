package de.springbootbuch.webmvc;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("programmatic-customization")
@Component
public class ContainerCustomization implements
WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(
		TomcatServletWebServerFactory server
	) {
		server.addConnectorCustomizers(connector -> {
			connector.setProxyName("myproxy");
			connector.setProxyPort(80);
		});
	}
}
