package com.advancedJava.firstServlet.webService;

import java.io.IOException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//@WebServlet("/ShowUsers.do")
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletContext context;

	
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {

		context = request.getServletContext();
		
		
		if(context.getAttribute("listOfUsers") != null) 
			request.setAttribute("listInReq", context.getAttribute("listOfUsers"));		
		else
			request.setAttribute("error", "The list is empty");
		
		request.getRequestDispatcher("/show.jsp").forward(request, response);
	}
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}





/*package com.advancedJava.firstServlet.webService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancedJava.firstServlet.domain.Participant;




//@WebServlet("/ShowUsers")
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PrintWriter out;
	private ServletContext context;
	private ArrayList<Participant> users = new ArrayList<Participant>();
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		out = response.getWriter();
		context = request.getServletContext();
		
		
		if(context.getAttribute("listOfUsers") != null) {
			users = (ArrayList<Participant>) context.getAttribute("listOfUsers");
			
			for(Participant part: users) {
					out.println(
							"<center>ID: " + ((users.indexOf(part)+1))
							+ "<br />Imie: " +part.getFirstName()
							+ "<br />Nazwisko: " +part.getLastName()
							+ "<br />Email: " +part.getEmail()
							+ "<br />Pracodawca: " +part.getEmployer()
							+ "<br />Zrodlo: " +part.getSource()
							+ "<br />Czym sie zajmuje: <br />" +part.getWhatDoYouDo()
							+ "<br /><br />");
				
			}
		}
		else {
			out.println("<center><h2> The list is empty! </h2>");
		}
		
		 
		out.println("<br /><a href='form.jsp'>Powrot do formularza</a></center>");
		
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
*/