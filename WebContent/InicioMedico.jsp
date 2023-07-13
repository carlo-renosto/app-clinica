<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>TP Integrador - Clinica</title>
</head>
<body>
	<%
	String usuario = session.getAttribute("nombre").toString();
	String dni = session.getAttribute("dni").toString();
	%>
	<div id="div-header">
		<h2 style="margin-top: 10px; margin-left: 5px;">Clinica Medica</h2>
		<a class="menu-bar" href="InicioMedico.jsp">Inicio</a>
		<a class="menu-bar" href="TurnoMedicoServlet?Param=1&dniUsuario=<%= dni %>">Turnos</a>
		<p class="menu-bar" style="display:inline-block; margin-left: 65%;">Usuario: <%=usuario%></p>
		<a class="menu-bar" style="text-decoration: underline;" href="Login.jsp">Cambiar</a>
	</div>
	<h2>Bienvenido/a, <%= usuario %></h2>
</body>
</html>