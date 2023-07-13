<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clinica - Login</title>
</head>
<body>
	<h2 style="text-align: center;">Login</h2>
	<form method="post" action="LoginServlet" style="text-align: center;">
		<p class="add-text">DNI</p> <input type="text" pattern="[0-9]+" title="Por favor, ingresa solo números" required name="txtDni" maxlength="30" style="width: 100px;"></input> <br>
		<p class="add-text">Contraseña</p> <input type="password" required name="txtContra" maxlength="30" style="width: 100px;"></input> <br><br>
		<input type="submit" value="Ingresar"></input><br>
		<% 
		if(request.getAttribute("lblError") != null) {
			String err = request.getAttribute("lblError").toString();
		%>
		<label style="color: red;"><%=err%></label>
		<% 
		} 
		%>
	</form>
</body>
</html>