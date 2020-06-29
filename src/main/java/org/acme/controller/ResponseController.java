package org.acme.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.entity.QueryResult;
import org.acme.service.ResponseService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/ask")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResponseController {

	@Inject
	ResponseService responseService;
	
    @GET
    @Path("/{question}")
    public Collection<QueryResult> hello(@PathParam String question) {
    	
    	List<QueryResult> results=new ArrayList<>();
    	
    	responseService.respondTo(question);
    	
    	
    	if(!question.endsWith("?")) {
    		question=question+"?";
    	}
    	
    	QueryResult qr1=new QueryResult(question, "Emmanuel Macron");
    	QueryResult qr2=new QueryResult("Who is the main leader of France?", "xxx");
    	QueryResult qr3=new QueryResult("Why does France have a president and a prime minister", "yyy");
    	QueryResult qr4=new QueryResult("q4", "zzz");
    	
    	results.add(qr1);
    	results.add(qr2);
    	results.add(qr3);
    	results.add(qr4);
    	
        return results;
    }
}