package com.advancedJava.firstServlet.webService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advancedJava.firstServlet.domain.Participant;


@WebServlet(urlPatterns={"/hello"})
public class FormService extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	private int usersLimit = 2;
	private int alreadyListed = 0;
	private ArrayList<Participant> users;
	private String firstName="", lastName="", email="", confEmail="", employer="", source="", otherSource="", whatIsDoing="";
	private Participant p;
	private HttpSession session;
	private ServletContext context;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.sendRedirect("form.jsp");
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		firstName = req.getParameter("firstName");
		lastName = req.getParameter("lastName");
		email = req.getParameter("email");
		confEmail = req.getParameter("emailConf");
		employer = req.getParameter("employer");
		source = req.getParameter("source");
		whatIsDoing = req.getParameter("whatDoYouDo");
		
		
		
		
		
			
				// pozyskuje za pomoca obiektu HttpServletRequest obiekty sesji i kontekstu
			session = req.getSession();
			context = req.getServletContext();
			
			if(context.getAttribute("count") == null) {
				context.setAttribute("count", 0);
			} 
			else {
				alreadyListed = (int) context.getAttribute("count");
			}
			
			if(context.getAttribute("listOfUsers") == null) {
				users = new ArrayList<Participant>();
				context.setAttribute("listOfUsers", users);
			}
			else {
				users = (ArrayList<Participant>) context.getAttribute("listOfUsers");
			}
			
			
			
				// sprawdzam czy zostalo podane imie i nazwisko w formularzu. Jesli podczas
				// obecnej sesji zostalo juz wysalana taka kombinacja imienia i nazwiska
				// nastepuje przekierowanie na strone alreadySend.jsp
			if(isFilled()) {
				if(!alreadySend(firstName,lastName)) {
					if(email.equals(confEmail)) {
						if(source.equals("others"))
							source = req.getParameter("otherSource");
						if(alreadyListed<usersLimit) {
							p = new Participant();
							p.setFirstName(firstName);
							p.setLastName(lastName);
							p.setEmail(email);
							p.setEmployer(employer);
							p.setSource(source);
							p.setWhatDoYouDo(whatIsDoing);
							alreadyListed++;  // zwiekszam licznik w kontekscie
							users.add(p);     // dodaje uzytkownika do listy w kontekscie
							
							session.setAttribute("name", firstName);
							session.setAttribute("lastName", lastName);
							context.setAttribute("count", alreadyListed);
							context.setAttribute("listOfUsers", users);
						}
						else 
							resp.sendRedirect("noSpace.jsp"); // jesli limit zostal wyczerpany nastepuje przekierowanie na odpowiednia strone
					}
					else 
						resp.sendRedirect("wrongEmail.jsp");		// jesli email niepoprawny - przekierowanie na strone komunikatu	
				}
				else 
					resp.sendRedirect("alreadySend.jsp");
			} 
			else
				resp.sendRedirect("form.jsp"); // jesli imie nie zostalo podane nastepuje przekierowanie z powrotem do formularza
			

			
			
			resp.getWriter().println("Witaj "+ firstName);
			resp.getWriter().println("!<br />Twoj id sesji to: "+  session.getAttribute("name")+" "+session.getAttribute("lastName"));
			resp.getWriter().println("<br />Ilosc uczestnikow: "+context.getAttribute("count"));
			resp.getWriter().println("<br /><a href='ShowUsers'>Pokaz liste zarejestrowanych</a>");
				
			
		
	}
	
		// metoda, za pomoca ktorej sprawdzam czy w sesji widnieje nazwa uzytkownika
	private boolean alreadySend(String name, String lastName) {
		String sessionName = session.getAttribute("name") +" "+ session.getAttribute("lastName");
		if(sessionName.equals(name+" "+lastName)) {
			return true;
		}
		else 
			return false;
	}
	
	private boolean isFilled() {
		if(firstName.isEmpty()|| lastName.isEmpty() || confEmail.isEmpty()
				|| email.isEmpty() || employer.isEmpty() 
				|| source.isEmpty())
			return false;
		else
			return true;
	}

}
