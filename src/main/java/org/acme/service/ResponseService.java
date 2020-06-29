package org.acme.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.acme.entity.QueryResult;
import org.acme.service.util.ResponseServiceUtil;

@Singleton
public class ResponseService {
	
	@Inject
	ResponseServiceUtil responseServiceUtil;

	public Collection<QueryResult> respondTo(String question) {
		List<QueryResult> results=new ArrayList<QueryResult>();
		
		return results;
	}
}
