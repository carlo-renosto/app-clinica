<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno" %>
<%@page import="entidad.Especialidad" %>
<%@page import="entidad.Medico" %>
<%@page import="entidad.Paciente" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    
    <title>Turnos</title>
  </head>
  <body>
  	<%
	String usuario = session.getAttribute("nombre").toString();
  	
  	ArrayList<Turno> listaFiltro = (ArrayList<Turno>) request.getAttribute("listaFiltro");
	%>
    <div id="div-header">
   		<h2 style="margin-top: 10px; margin-left: 30px">Clinica Medica</h2>
	    <a class="menu-bar" href="${pageContext.request.contextPath}/Inicio.jsp">Inicio</a>
	    <a class="menu-bar" href="PacienteServlet?Param=1">Pacientes</a>
	    <a class="menu-bar" href="MedicoServlet?Param=1">Medicos</a>
	    <a class="menu-bar" href="TurnoServlet?Param=1">Turnos</a>
	    <a class="menu-bar" href="ReporteServlet?Param=1">Reportes</a>
	    <a class="menu-bar" href="UsuarioServlet?Param=1">Usuarios</a>
	    <p class="menu-bar" style="display:inline-block; margin-left: 45%;">Usuario: <%=usuario%></p>
		<a class="menu-bar" style="text-decoration: underline;" href="${pageContext.request.contextPath}/Login.jsp">Cambiar</a>
    </div>

    <form action="${pageContext.request.contextPath}/TurnoFiltroServlet?Param=1" method="get">
		<p class="form-filter" style="margin-left: 5px;">Filtrar por...</p>
		<% 
		ArrayList<Especialidad> listaEsp = null;
		if(request.getAttribute("listaEsp")!= null)
		{
			listaEsp = (ArrayList<Especialidad>) request.getAttribute("listaEsp");
		}
	 	%>
		<p class="form-filter">Especialidad</p> 
		<select name="ddlEspecialidad">
			<option> </option> 
			<%
			 if(listaEsp !=null)
				for(Especialidad espe : listaEsp) {
			%> 
			<option><%= espe.getNombre() %></option>
			<%		 
				}
			%>
		</select>
		
		<% 
		ArrayList<Medico> listaDocumento = null;
		if(request.getAttribute("listaDni")!= null)
		{
			listaDocumento = (ArrayList<Medico>) request.getAttribute("listaDni");
		}
		%>
		<p class="form-filter">Médico</p> 
		<select name="ddlMedico">
			<option> </option>
			<%
			 if(listaDocumento !=null)
				for(Medico medi : listaDocumento) {
			%> 
			<option ><%= medi.getDni() %></option>
			<%		 
				}
			%>
		</select>
		
		<% 
		ArrayList<Paciente> listaDocumento2 = null;
		if(request.getAttribute("listaDniP")!= null)
		{
			listaDocumento2 = (ArrayList<Paciente>) request.getAttribute("listaDniP");
		}
	 	%>
		<p class="form-filter">Paciente</p> 
		<select name="ddlPaciente">
			<option></option>
			<%
			 if(listaDocumento2 !=null)
				for(Paciente paci: listaDocumento2) {
			%> 
			<option ><%= paci.getDni() %></option>
			<%		 
				}
			%>
		</select>
		<p class="form-filter">Día de atención</p>
		<select name="ddlDia">
			<option> </option>
			<option>Lunes</option>
			<option>Martes</option>
			<option>Miercoles</option>
			<option>Jueves</option>
			<option>Viernes</option>
		</select>
		
		<p class="form-filter">Hora de atención</p>
		<select name="txtHora" id="txtHora" style="margin-left: 5px;">
			<option></option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
			<option>9</option>
			<option>10</option>
			<option>11</option>
			<option>12</option>
			<option>13</option>
			<option>14</option>
			<option>15</option>
			<option>16</option>
			<option>17</option>
		</select>
		
		<p class="form-filter">Estado</p>
		<select name="ddlEstado">
			<option> </option>
			<option>Libre</option>
			<option>Ocupado</option>
			<option>Ausente</option>
			<option>Presente</option>
		</select>
		<input class="form-filter" type="submit" name="btnFiltrar" value="Filtrar"></input><br>
	</form>
	
	<table id="table_id" class="display">
	    <thead>
	        <tr>
	            <th>Especialidad</th>
	            <th>Médico</th>
	            <th>Paciente</th>
	            <th>Día Atención</th>
	            <th>Hora Atención</th>
	            <th>Estado</th>
	        </tr>
	    </thead>
	    <tbody>
	        <% 
	        if (listaFiltro != null) {
	            for (Turno turno : listaFiltro) {
	        %>
	        <tr>
	            <td><%= turno.getEspecialidadNombre()%></td>
	            <td><%= turno.getMedico().getDni()%> (<%= turno.getMedico().getNombre()%> <%=turno.getMedico().getApellido() %>)</td>
	            <td><%= turno.getPaciente().getDni()%> (<%= turno.getPaciente().getNombre()%> <%=turno.getPaciente().getApellido() %>)</td>
	            <td><%= turno.getDiaTurno()%></td>
	            <td><%= turno.getHoraTurno()%></td>
	            <td><%= turno.getEstado()%></td>
	        </tr>
	        <% 
	            }
	        } else {
	        %>
	        <tr>
	            <td colspan="6">Sin resultados.</td>
	        </tr>
	        <% 
	        }
	        %>
	    </tbody>
	</table>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id').DataTable({
	            paging: true, // Habilita la paginación
	            pageLength: 5, // Define la cantidad de filas por página
	            searching: false,
	            language: {
	                lengthMenu: 'Mostrar _MENU_ registros por página',
	                zeroRecords: 'No se encontraron resultados',
	                info: 'Mostrando página _PAGE_ de _PAGES_',
	                infoEmpty: 'No hay registros disponibles',
	                infoFiltered: '(filtrado de _MAX_ registros totales)',
	                paginate: {
	                    first: 'Primera',
	                    last: 'Última',
	                    next: 'Siguiente',
	                    previous: 'Anterior'
	                }
	            }
	        });
	    });
	</script>

    <form action="TurnoServlet"> 
    	<input type="hidden" name="Param" value="2">
		<input type="submit" value="Asignar turno" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
  </body>
</html>
