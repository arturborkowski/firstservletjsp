package com.advancedJava.firstServlet.webService;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advancedJava.firstServlet.domain.Participant;


//@WebServlet(urlPatterns={"/hello.do"})
public class FormService extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	private int usersLimit = 2;
	private int alreadyListed = 0;
	private ArrayList<Participant> users;
	private String firstName, lastName, email, confEmail, employer, source, whatIsDoing;
	private Participant p;
	private HttpSession session;
	private ServletContext context;



	

	
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
		
		
		
		
			session = req.getSession();
			context = req.getServletContext();
			
			// pobieram wartosc licznika uzytkownika z kontekstu. Jesli atrybut nie istnieje - tworze go i ustawiam na 0
			if(context.getAttribute("count") == null)
				context.setAttribute("count", alreadyListed);
			else
				alreadyListed = (int) context.getAttribute("count");
			
			// pobieram z kontekstu obiekt listy uzytkownikow. jesli nie istnieje - tworze liste i dodaje do kontekstu
			if(context.getAttribute("listOfUsers") == null) {
				users = new ArrayList<Participant>();
				context.setAttribute("listOfUsers", users);
			}
			else 
				users = (ArrayList<Participant>) context.getAttribute("listOfUsers");
			
			
			
			if(isFilled()) {
				if(!isSend(firstName,lastName)) {
					if(email.equals(confEmail)) {
						if(alreadyListed<usersLimit) {
							// jesli ilosc zapisanych jest ponizej limitu dodaje nowego
							p = new Participant();
							p.setFirstName(firstName);
							p.setLastName(lastName);
							p.setEmail(email);
							p.setEmployer(employer);
							if(source.equals("others"))
								source = req.getParameter("otherSource");
							p.setSource(source);
							p.setWhatDoYouDo(whatIsDoing);
							
							alreadyListed++;  // zwiekszam licznik w kontekscie
							users.add(p);     // dodaje uzytkownika do listy w kontekscie
							
							// ustawiam nowe atrybuty globalne (uzytkownika w sesji i powiekszona liste i licznik w kontekscie)
							session.setAttribute("name", firstName);
							session.setAttribute("lastName", lastName);
							context.setAttribute("count", alreadyListed);
							context.setAttribute("listOfUsers", users);
							
							req.getRequestDispatcher("/hello.jsp").forward(req, resp);
						}
						
						else {
							req.setAttribute("error", "Przykro nam, nie ma juz miejsc!");
							req.getRequestDispatcher("/form.jsp").forward(req, resp);
						}
					}
					else {
						req.setAttribute("error", "Poprawnie powtorz email!");
						req.getRequestDispatcher("/form.jsp").forward(req, resp);
					}	
				}
				else {
					req.setAttribute("error", "Juz raz sie zapisales!");
					req.getRequestDispatcher("/form.jsp").forward(req, resp);
				}
			} 
			else {
				req.setAttribute("error", "Musisz wypelnic wszystkie pola!");
				req.getRequestDispatcher("/form.jsp").forward(req, resp);				
			}
		
	}
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.sendRedirect("form.jsp");
		
	}
	
	
		// metoda, za pomoca ktorej sprawdzam czy w sesji widnieje nazwa uzytkownika
	private boolean isSend(String name, String lastName) {
		String sessionName = session.getAttribute("name") +" "+ session.getAttribute("lastName");
		if(sessionName.equals(name+" "+lastName)) {
			return true;
		}
		else 
			return false;
	}
	
		// metoda sprawdzajaca wypelnienie pol
	private boolean isFilled() {
		if(firstName.isEmpty()|| lastName.isEmpty() || confEmail.isEmpty()
				|| email.isEmpty() || employer.isEmpty() 
				|| source == null)
			return false;
		else
			return true;
	}

}
