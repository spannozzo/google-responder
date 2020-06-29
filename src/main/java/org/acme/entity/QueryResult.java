package org.acme.entity;

public class QueryResult {

	String question;
	String response;

	public String getQuestion() {
		return question;
	}

	public String getResponse() {
		return response;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public QueryResult() {
		// TODO Auto-generated constructor stub
	}

	public QueryResult(String question, String response) {
		super();
		this.question = question;
		this.response = response;
	}

}