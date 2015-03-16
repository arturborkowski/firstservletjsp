package com.advancedJava.firstServlet.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DataCheck {

	private HttpServletRequest request;
	
	
	public DataCheck(HttpServletRequest request){
		this.request = request;
	}
	
	
	
	
	public boolean isAddPossible() {
		if(isFilled() && !isSend() && !isLimitReached() && isEmailCorrect()) 
			return true;
		else
			return false;
	}
	
	
	
	
	private boolean isLimitReached() {
		
		int count = (int)request.getServletContext().getAttribute("count");
		int usersLimit = (int)request.getServletContext().getAttribute("limit");
		
		if(count < usersLimit ) {
			return false;
		}
		request.setAttribute("error", "Przykro nam, nie ma juz miejsc!");
		return true;
	}
	
	
	// metoda sprawdzajaca wypelnienie pol
	private boolean isFilled() {
		if(request.getParameter("firstName").isEmpty()
				|| request.getParameter("lastName").isEmpty() 
				|| request.getParameter("emailConf").isEmpty()
				|| request.getParameter("email").isEmpty() || request.getParameter("employer").isEmpty() 
				|| request.getParameter("source") == null)
		{
			request.setAttribute("error", "Musisz wypelnic wszystkie pola!");
			return false;
		}
			
		else
			return true;
	}
	
	
	private boolean isEmailCorrect() {
		if(isFilled()) {
			if(request.getParameter("email").equals(request.getParameter("emailConf")))
				return true;	
			else {
				request.setAttribute("error", "Poprawnie powtorz email!");
				return false;
			}
		}
		return false;
	}
	
	
	
	// metoda, za pomoca ktorej sprawdzam czy w sesji widnieje nazwa uzytkownika
	private boolean isSend() {
		HttpSession session = request.getSession();
		String sessionName = session.getAttribute("name") +" "+ session.getAttribute("lastName");
		if(sessionName.equals(request.getParameter("firstName")+" "+request.getParameter("lastName"))) {
			request.setAttribute("error", "Juz raz sie zapisales!");
			return true;
		}
		else 
			return false;
	}
	

}
