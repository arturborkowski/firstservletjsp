<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="com.advancedJava.firstServlet.domain.Participant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<center>

<%
	if(request.getAttribute("listInReq") == null) {
		out.println(request.getAttribute("error"));
	}
	else {
		ArrayList<Participant> users = (ArrayList<Participant>) request.getAttribute("listInReq");
		for(Participant p: users) {
			out.println(
					"ID: " + ((users.indexOf(p)+1))
					+ "<br />Imie: " +p.getFirstName()
					+ "<br />Nazwisko: " +p.getLastName()
					+ "<br />Email: " +p.getEmail()
					+ "<br />Pracodawca: " +p.getEmployer()
					+ "<br />Zrodlo: " +p.getSource()
					+ "<br />Czym sie zajmuje: <br />" +p.getWhatDoYouDo()
					+ "<br /><br />");
		}
	}
%>

	<a href="form.jsp">Powrót do formularza</a>
</center>
</body>
</html>