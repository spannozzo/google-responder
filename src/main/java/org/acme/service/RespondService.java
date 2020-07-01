package org.acme.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.acme.entity.QueryResult;
import org.acme.service.util.ResponseServiceUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Singleton
public class RespondService {

	@Inject
	ResponseServiceUtil responseServiceUtil;
	
	String responseClass = "e24Kjd";
	
	String responseCheck = "<span class=\""+responseClass+"\">";
	
	
	String responseClass2 = "iKJnec";
	
	String responseCheck2 = "<span class=\""+responseClass2+"\">";
	
	String responseClass3 = "FLP8od";

	String responseCheck3 = "<a class=\""+responseClass3+"\"";
	
	String questionClass="related-question-pair";
	
	
	private String findAnswersIntoString(String xml) {
		String returnValue = "";

		Document doc = Jsoup.parse(xml);

		if (xml.contains(responseCheck)) {

			Elements x = doc.select("span." + responseClass);

			returnValue = x.text();

		} else if (xml.contains(responseCheck2)) {
			Elements x = doc.select("div." + responseClass2);
			returnValue = x.text();
		}
		
		else if (xml.contains(responseCheck3)) {
			Elements x = doc.select("a." + responseClass3);
			returnValue = x.text();
		}

		return returnValue;
	}
	
	private List<String> findRelatedQuestions(String xml){
		Document doc = Jsoup.parse(xml);
		String[] array = null;
		String text="";
		if (xml.contains(questionClass)) {
			
			Elements y= doc.select("div."+questionClass);
			
			text=y.text();
			
			array=text.split("\\?");
		}
//		String[] array= {"Who is the president of Paris","Who is the first lady of France","Why does France have a president and a prime minister"};
		if (array!=null) {
			return Arrays.asList(array);
		}
		return Collections.EMPTY_LIST;
	}

	public Collection<QueryResult> respondAndFindQuestions(String question) {
		Collection<QueryResult> results = new ArrayList<QueryResult>();

		String link=responseServiceUtil.getUrlString(question);

		try {
			Connection.Response html = Jsoup.connect(link).execute();

			String xml=html.body();
			
			String mainAnser = this.findAnswersIntoString(xml);
			
			QueryResult main= new QueryResult(question, mainAnser,0);
			
			results.add(main);
			
			List<String> questions=this.findRelatedQuestions(xml);
			
			questions.forEach(q->{
				QueryResult qr=new QueryResult(q,"",-1);
				results.add(qr);
			});
			
			html=null;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	public Collection<QueryResult> respondToAllQuestions(List<QueryResult> questions) {
		
		
			Collection<QueryResult> results = new ArrayList<QueryResult>();
			Collection<QueryResult> alreadyAnsweredQuestions=questions.parallelStream().filter(qr->!qr.getAnswer().isEmpty()).collect(Collectors.toList());
			
			Collection<QueryResult> unansweredQuestions=questions.parallelStream().filter(qr->qr.getAnswer().isEmpty()).collect(Collectors.toList());
			
			List<String> links=unansweredQuestions.parallelStream()	.map(qr -> responseServiceUtil.getUrlString(qr.getQuestion()))
															.collect(Collectors.toList());
			int[] runCount = {0};
			links.parallelStream().forEach(link->{
				try {
					
				    Connection.Response html = Jsoup.connect(link).execute();
				    
				    String x= this.findAnswersIntoString(html.body());
				    
				    String q=responseServiceUtil.decodeValue(link);
				    
				    if (q.equals(questions.get(0).getQuestion())) {
				    	
				    	results.add(new QueryResult(q, x,0));
					}else {
						results.add(new QueryResult(q, x,++runCount[0]));
					}
				    
				    
				    
				    html=null;
							
				  }catch (Exception e) {
				    e.printStackTrace();
				  }	
			});
			
			Collection<QueryResult> returnValue = new ArrayList<QueryResult>();
			if(!alreadyAnsweredQuestions.isEmpty()) {
				returnValue=Stream.concat(alreadyAnsweredQuestions.stream(),results.stream()).collect(Collectors.toList());
			}else {
				returnValue=results;
			}
			
			return returnValue;
		
	}

}
