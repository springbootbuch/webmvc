package de.springbootbuch.webmvc;

import java.time.Year;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Component
public class StringToYearConvertor implements Converter<String, Year> {
	@Override
	public Year convert(String source) {
		return Year.parse(source);
	}	
}
