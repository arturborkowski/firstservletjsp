<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formularz</title>
</head>
<body>

<center><h1>Witaj w formularzu rejestracyjnym na konferencje "Java 4 US" !</h1></center>

	<form action="hello" method="POST">
		<table>
			<tr>
				<td>Imie:</td><td><input type="text" name="firstName"/></td>
			</tr>
			<tr>
				<td>Nazwisko:</td><td><input type="text" name="lastName"/></td>
			</tr>
			<tr>
				<td>Email:</td><td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td>Potwierdz email:</td><td><input type="text" name="emailConf"/></td>
			</tr>
			<tr>
				<td>Pracodawca:</td><td><input type="text" name="employer"/></td>
			</tr>
		</table>
			
		<br /><br />Skad dowiedziales sie o konferencji? <br />
		<input type="radio" name="source" value="Z ogloszenia w pracy "/> Z ogloszenia w pracy <br />
		<input type="radio" name="source" value="Z ogloszenia na uczelni"/> Z ogloszenia na uczelni <br />
		<input type="radio" name="source" value="Z facebooka"/> Z facebooka <br />
		<input type="radio" name="source" value="Od znajomych"/> Od znajomych <br />
		<input type="radio" name="source" value="others"/> Inne (jakie?) <input type="text" name="otherSource" /> <br />
		
		<br /><br />Czym sie zajmujesz? <br />
		<textarea name="whatDoYouDo" rows=5 cols=20></textarea><br />		
		<br /><input type="submit" name="submit" value="Przeslij" />
		
	</form>

</body>
</html>