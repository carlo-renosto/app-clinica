<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno" %>
<%@page import="entidad.Especialidad" %>
<%@page import="entidad.Medico" %>
<%@page import="entidad.Paciente" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="${pageContext.request.contextPath}/css/styles.css?v=1"
	rel="stylesheet" type="text/css">
<title>Turnos</title>
</head>
<body>
	<%
	String usuario = session.getAttribute("nombre").toString();
	
	Medico med = null;
	%>
	<div id="div-header">
		<h2 style="margin-top: 10px; margin-left: 30px;">Clinica Medica</h2>
		<a class="menu-bar" href="${pageContext.request.contextPath}/Inicio.jsp">Inicio</a> 
		<a class="menu-bar" href="PacienteServlet?Param=1">Pacientes</a> 
		<a class="menu-bar" href="MedicoServlet?Param=1">Medicos</a> 
		<a class="menu-bar" href="TurnoServlet?Param=1">Turnos</a>
		<a class="menu-bar" href="ReporteServlet?Param=1">Reportes</a>
		<a class="menu-bar" href="UsuarioServlet?Param=1">Usuarios</a>
		<p class="menu-bar" style="display: inline-block; margin-left: 45%;"> Usuario:<%=usuario%></p>
		<a class="menu-bar" style="text-decoration: underline;" href="${pageContext.request.contextPath}/Login.jsp">Cambiar</a>
	</div>
	
	<div class="add-form" id="add-form">
	<form action="${pageContext.request.contextPath}/TurnoServlet?" method="get">
		<input type="hidden" name="Param" value="3">
		
		<% 
		ArrayList<Medico> listaDocumento = null;
		if(request.getAttribute("listaDni")!= null)
		{
			listaDocumento = (ArrayList<Medico>) request.getAttribute("listaDni");
		}
		%>
		<% 
		if(request.getAttribute("medico")!= null)
		{
			med = (Medico) request.getAttribute("medico");
		}
		%>
		<label class="add-text" style="margin-top: 10px;">Médico</label> 
		<select name="ddlMedico" id="ddlMedico" required style="margin-left: 33px;" onchange="this.form.submit()">
			<%
			if(request.getAttribute("medico") != null) {
			%>
			<option><%= med.getDni()%></option>
			<%
			}
			else {
			%>
			<option></option>
			<%
			}
			%>
			<%
			if(listaDocumento != null)
				for(Medico medi : listaDocumento) { 
			%>
			<option ><%= medi.getDni() %></option>
			<%		 
				}
			%>
		</select><br/><br/>
	</form>
	
	<form action="TurnoServlet" method="get">
		<% 
		if(request.getAttribute("medico")!= null) { 
		%>
		<input type="hidden" name="ddlMedicoAux" value="<%= med.getDni()%>" required>
		<% 
		} 
		%>
		<% 
		if(request.getAttribute("medico") == null) {
		%>
		<p class="add-text">Especialidad </p> <input type="text" name="txtEspecialidad" Value='' readonly required style="width: 100px;">
		<%
		}
		else {
		%>
		<p class="add-text">Especialidad </p> <input type="text" name="txtEspecialidad" Value='<%= med.getEspecialidad().getNombre() %>' readonly required style="width: 100px;">
		<% 
		}
		%>
		<br><br>

		<label class="add-text">Paciente</label> 
		<% 
		ArrayList<Paciente> listaDocumento2 = null;
		if(request.getAttribute("listaDniP")!= null) {
			listaDocumento2 = (ArrayList<Paciente>) request.getAttribute("listaDniP");
		}
	 	%>
		<select name="ddlPaciente" id="ddlPaciente" required style="margin-left: 27px;">
			<option></option>
			<%
		 	if(listaDocumento2 != null)
				for(Paciente paci: listaDocumento2) {
			%> 
			<option ><%= paci.getDni() %></option>
			<%		 
				}
			%>
		</select><br/> 
		
		<p class="add-text">Día</p>
		<select name="ddlDia" id="ddlDia" required style="margin-left: 59px;">
			<% 
			if(request.getAttribute("medico") == null) {
			%>
			<option></option>
			<%
			}
			else {
			%>
			<option><%= med.getDia_atencion()%></option>
			<%
			}
			%>
		</select><br/><br/>
		
		<% 
		ArrayList<Integer> listaHoras = null;
		if(request.getAttribute("horasMedico")!= null) {
			listaHoras = (ArrayList<Integer>) request.getAttribute("horasMedico");
		}
	 	%>
		<label class="add-text">Horario</label> 
		<select name= "ddlHora" id="ddlHora" required style="margin-left: 32px;">
			<option></option>
			<%
		 	if(listaHoras != null)
				for(Integer hora : listaHoras) {
			%> 
			<option ><%= hora%></option>
			<%		 
				}
			%>
		</select><br/> 
		
		<input type="hidden" name="Param" value="4">
		<input type="submit" value="Asignar Turno" style="margin-top: 10px; margin-left: 85px; margin-bottom: 10px;"></input>
	</form>
	</div>
</body>
</html>
