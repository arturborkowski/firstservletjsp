package com.advancedJava.firstServlet.logic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.advancedJava.firstServlet.domain.Participant;

public class ParticipantBase {

	private HttpServletRequest request;
	private ArrayList<Participant> users;
	private int usersCounter;
	
	
	@SuppressWarnings("unchecked")
	public ParticipantBase(HttpServletRequest request) {
		
		this.request = request;
		users = (ArrayList<Participant>) request.getServletContext().getAttribute("listOfUsers");
		usersCounter = (int)request.getServletContext().getAttribute("count");
		
	}

	public void addParticipant() {
	
		users.add(new ParticipantBuilder().build(request));
		usersCounter++;
		
		request.getSession().setAttribute("name", request.getParameter("firstName"));
		request.getSession().setAttribute("lastName", request.getParameter("lastName"));
		request.getServletContext().setAttribute("count", usersCounter);
		request.getServletContext().setAttribute("listOfUsers", users);
		
	}
}
