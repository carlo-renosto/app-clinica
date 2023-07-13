<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Usuarios - Añadir</title>
</head>
<body>
	<%
	String usuario = session.getAttribute("nombre").toString();
	%>
	<div id="div-header">
		<h2 style="margin-top: 10px; margin-left: 30px;">Clinica Medica</h2>
		<a class="menu-bar" href="${pageContext.request.contextPath}/Inicio.jsp">Inicio</a>
		<a class="menu-bar" href="PacienteServlet?Param=1">Pacientes</a>
		<a class="menu-bar" href="MedicoServlet?Param=1">Medicos</a>
		<a class="menu-bar" href="TurnoServlet?Param=1">Turnos</a>
		<a class="menu-bar" href="ReporteServlet?Param=1">Reportes</a>
		<a class="menu-bar" href="UsuarioServlet?Param=1">Usuarios</a>
		<p class="menu-bar" style="display:inline-block; margin-left: 45%;">Usuario: <%=usuario%></p>
		<a class="menu-bar" style="text-decoration: underline;" href="${pageContext.request.contextPath}/Login.jsp">Cambiar</a>
	</div>
	
	<form class="add-form" action="${pageContext.request.contextPath}/AgregarUsuarioServlet" method="post" >
		<p class="add-text">DNI</p> <input type="text" pattern="[0-9]+" title="Por favor, ingresa solo números" required name="txtDni" style="width: 100px; margin-left: 43px;"></input><br>
		<p class="add-text">Usuario</p> <input type="text" required name="txtUsuario" maxlength="20" style="width: 100px; margin-left:20px;"></input><br>
		<p class="add-text">Tipo</p> <select name="ddlTipo" style="margin-left: 40px;"><option>Admin</option><option>Medico</option></select><br>
		<p class="add-text">Contraseña</p> <input type="password" required name="txtContrasena" maxlength="20" style="width: 100px;"></input> <br>
		<p class="add-text">Confirmar</p> <input type="password" required name="txtContrasena2" maxlength="20" style="width: 100px; margin-left: 5px;"></input><br><br>
		
		<input type="submit" value="Registrar" style="margin-left: 90px; margin-bottom: 10px;"></input><br>
		<% String err = request.getAttribute("lblError").toString(); %>
		<label style="color: red; margin-left: 50px;"><%=err%></label>
	</form>
</body>
</html>