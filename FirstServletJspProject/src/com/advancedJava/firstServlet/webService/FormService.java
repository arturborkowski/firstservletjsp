package com.advancedJava.firstServlet.webService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancedJava.firstServlet.domain.Participant;
import com.advancedJava.firstServlet.logic.DataCheck;
import com.advancedJava.firstServlet.logic.ParticipantBase;


//@WebServlet(urlPatterns={"/hello.do"})
public class FormService extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	private final int USERS_LIMIT = 2;



	@Override
	public void init() throws ServletException {
		
		getServletContext().setAttribute("limit", this.USERS_LIMIT);
		getServletContext().setAttribute("count", 0);
		getServletContext().setAttribute("listOfUsers", new ArrayList<Participant>());
		
		super.init();
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
				if(new DataCheck(req).isAddPossible()) {
					new ParticipantBase(req).addParticipant();							
					req.getRequestDispatcher("/hello.jsp").forward(req, resp);
				}
						
				else
					req.getRequestDispatcher("/form.jsp").forward(req, resp);						
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.sendRedirect("form.jsp");
		
	}
	

}
