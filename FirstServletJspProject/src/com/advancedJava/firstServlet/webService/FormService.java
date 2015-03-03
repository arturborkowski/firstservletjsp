package com.advancedJava.firstServlet.webService;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.advancedJava.firstServlet.domain.Participant;


@WebServlet(urlPatterns={"/hello"})
public class FormService extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public static int count = 0;
	private int usersLimit = 5;
	//private Participant p;
	private HttpSession session;
	private ServletContext context;
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/*p = new Participant();
		p.setFirstName(req.getParameter("firstName"));
		p.setLastName(req.getParameter("lastName"));
		p.setEmail(req.getParameter("email"));
		p.setEmployer(req.getParameter("employer"));
		p.setSource(req.getParameter("source"));
		p.setWhatDoYouDo(req.getParameter("whatDoYouDo"));
		*/
		
		
		
				// pobieram z formularza imie i nazwisko
			String userName = req.getParameter("firstName");
			String userLastName = req.getParameter("lastName");
			
				// pozyskuje za pomoca obiektu HttpServletRequest obiekty sesji i kontekstu
			session = req.getSession();
			context = req.getServletContext();
			
			
			
				// sprawdzam czy zostalo podane imie i nazwisko w formularzu. Jesli podczas
				// obecnej sesji zostalo juz wysalana taka kombinacja imienia i nazwiska
				// nastepuje przekierowanie na strone alreadySend.jsp
			if(userName != "" && userName != null) {
				if(alreadySend(userName,userLastName)) {
					resp.sendRedirect("alreadySend.jsp");
				}
					// jesli w sesji nie widnieje taka nazwa uzytkownika to sprawdzam ilu
					// uzytkownikow zostalo zapisanych w statycznej zmiennej count. Jesli liczba miesci sie
					// w limicie to ustawiam w sesji nazwe nowego uzytkownika i zwiekszam statyczny licznik.
					// Nastepnie zapisuje liczbe z licznika do kontekstu.
				else { 
					if(FormService.count<usersLimit) {
						session.setAttribute("name", userName);
						session.setAttribute("lastName", userLastName);
						FormService.count++;
						context.setAttribute("count", FormService.count);
					}
					else {
							// jesli limit zostal wyczerpany nastepuje przekierowanie na odpowiednia strone
						resp.sendRedirect("noSpace.jsp");
					}
				}
				
				resp.getWriter().println("Witaj "+ userName);
				resp.getWriter().println("Twoj id sesji to: "+  session.getAttribute("name")+" "+session.getAttribute("lastName"));
				resp.getWriter().println("Ilosc uczestnikow: "+context.getAttribute("count"));
			} 
			else {
				 // jesli imie nie zostalo podane nastepuje przekierowanie z powrotem do formularza
				resp.sendRedirect("form.jsp");
			}
			
			
				
			
		
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
	
	/*private boolean isFilled() {
		if(p.getFirstName() == null || p.getLastName() == null
				|| p.getEmail() == null || p.getSource() == null 
				|| p.getSource() == null)
			return false;
		else
			return true;
	}*/

}
