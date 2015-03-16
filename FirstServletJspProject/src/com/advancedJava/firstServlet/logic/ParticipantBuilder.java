package com.advancedJava.firstServlet.logic;

import javax.servlet.http.HttpServletRequest;

import com.advancedJava.firstServlet.domain.Participant;

public class ParticipantBuilder {

	
	public Participant build(HttpServletRequest request) {
		Participant part = new Participant();
		part.setFirstName(request.getParameter("firstName"));
		part.setLastName(request.getParameter("lastName"));
		part.setEmail(request.getParameter("email"));
		part.setEmployer(request.getParameter("employer"));
		part.setWhatDoYouDo(request.getParameter("whatDoYouDo"));
		if(request.getParameter("source").equals("others"))
			part.setSource(request.getParameter("otherSource"));
		else
			part.setSource(request.getParameter("source"));
		
		return part;
	}

}
