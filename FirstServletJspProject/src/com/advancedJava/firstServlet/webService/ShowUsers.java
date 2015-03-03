package com.advancedJava.firstServlet.webService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancedJava.firstServlet.domain.Participant;




@WebServlet("/ShowUsers")
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PrintWriter out;
	private Participant part;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		out = response.getWriter();
		
		for(int i=0; i < Participant.count; i++) {
			part = Participant.list[i];
			if(part!=null){
				out.println(
						"<center>ID: " + (i+1)
						+ "<br />Imie: " +part.getFirstName()
						+ "<br />Nazwisko: " +part.getLastName()
						+ "<br />Email: " +part.getEmail()
						+ "<br />Pracodawca: " +part.getEmployer()
						+ "<br />Zrodlo: " +part.getSource()
						+ "<br />Czym sie zajmuje: <br />" +part.getWhatDoYouDo()
						+ "<br /><br />");
			}
		}
		
		out.println("<br /><a href='form.jsp'>Powrot do formularza</a></center>");
		
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
