package org.acme.entity;

public class QueryResult implements Comparable<QueryResult>{

	
	String question;
	String answer;
	
	Integer order;
	
	

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
	public QueryResult() {
		// TODO Auto-generated constructor stub
	}

	public QueryResult(String question, String response) {
		super();
		this.question = question;
		this.answer = response;
	}

	
	public QueryResult(String question, String answer, Integer order) {
		super();
		this.question = question;
		this.answer = answer;
		this.order = order;
	}

	@Override
	public int compareTo(QueryResult qr) {
		int x=0;
		x = (order<=qr.getOrder()) ?  -1 : 1;
		
		
		
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QueryResult) {
			
			return ((QueryResult) obj).getAnswer().equals(this.getAnswer()) && ((QueryResult) obj).getQuestion().equals(this.getQuestion()) ;
			
		}
		return false;
		
	}

	@Override
	public String toString() {
		return "QueryResult [question=" + question + ", answer=" + answer + ", order=" + order + "]";
	}
	
}