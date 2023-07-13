<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Observación</title>
</head>
<body>
	<%
	String id = request.getAttribute("id").toString();
	String dniUsuario = request.getAttribute("dniUsuario").toString();
	%>
	<p>Observación:</p>
	<form action="MarcarObservacionServlet" method="get">
		<input type="hidden" name="Param" value="2">
		<input type="hidden" name="id" value="<%= id %>">
		<input type="hidden" name="dniUsuario" value=<%= dniUsuario %>>
		<input type="text" name="txtObservacion" style="width: 200px;"><br>
		<input type="submit" value="Guardar" style="margin-top: 5px;">
	</form>
	<form action="MarcarObservacionServlet" method="get">
		<input type="hidden" name="Param" value="3">
		<input type="hidden" name="id" value="<%= id %>">
		<input type="hidden" name="dniUsuario" value=<%= dniUsuario %>>
		<input type="submit" value="Cancelar" style="margin-top: 5px;">
	</form>
</body>
</html>