package com.advancedJava.firstServlet.domain;

public class Participant {

	public static int count = 0;
	public static Participant[] list = new Participant[5];
	
	String firstName;
	String lastName;
	String email;
	String employer;
	String source;
	String whatDoYouDo;
	
	
	
	public Participant() {
		Participant.list[count] = this;
		Participant.count++;
	}
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getWhatDoYouDo() {
		return whatDoYouDo;
	}
	public void setWhatDoYouDo(String whatDoYouDo) {
		this.whatDoYouDo = whatDoYouDo;
	}
	
	

}
