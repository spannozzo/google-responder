package org.acme.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.entity.QueryResult;
import org.acme.service.RespondService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("v2/ask")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResponderController {

	@Inject
	RespondService respondService;
	
	CompletableFuture<Collection<QueryResult>> respondAndFindQuestions(String question) {
		return CompletableFuture.supplyAsync(() -> {
			return respondService.respondAndFindQuestions(question);
		});	
	}

	
	CompletableFuture<Collection<QueryResult>> respondToAllQuestions(Collection<QueryResult> qr ) {
		return CompletableFuture.supplyAsync(() -> {
			return respondService.respondToAllQuestions((List<QueryResult>) qr);
		});
	}
	
    @GET
    @Path("/{question}")
    public Collection<QueryResult> respond(@PathParam String question) throws Exception{
    	
    	   	
    	CompletableFuture<Collection<QueryResult>> results=respondAndFindQuestions(question).thenCompose(cqr -> respondToAllQuestions(cqr) );
    	
    	
    	List<QueryResult> qr=new ArrayList<QueryResult>();
    	
    	qr=(List<QueryResult>) results.get();
        
    	Collections.sort(qr);
    	
    	return qr;
    }
    

    
}