package org.acme.service.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.enterprise.context.Dependent;

@Dependent
public class ResponseServiceUtil {
	private final String googleString = "https://www.google.com/search?q=";

	private String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

	public String getUrlString(String question) {
		return googleString + encodeValue(question);
	}

}
