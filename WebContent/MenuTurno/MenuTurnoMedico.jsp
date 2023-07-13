<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	String dni = session.getAttribute("dni").toString();
	
	ArrayList<Turno> listaFiltro = (ArrayList<Turno>) request.getAttribute("listaFiltro");
	%>
	<div id="div-header">
		<h2 style="margin-top: 10px; margin-left: 5px;">Clinica Medica</h2>
		<a class="menu-bar" href="${pageContext.request.contextPath}/InicioMedico.jsp">Inicio</a>
		<a class="menu-bar" href="TurnoMedicoServlet?Param=1&dniUsuario=<%= dni %>">Turnos</a>
		<p class="menu-bar" style="display:inline-block; margin-left: 45%;">Usuario: <%=usuario%></p>
		<a class="menu-bar" style="text-decoration: underline;" href="${pageContext.request.contextPath}/Login.jsp">Cambiar</a>
	</div>
	
	<form action="${pageContext.request.contextPath}/TurnoFiltroServlet?dniUsuario=<%= dni %>" method="get">
		<input type="hidden" name="dniUsuario" value='<%= dni %>'>
		
		<% 
		ArrayList<Integer> dniPacientes = null;
		if(request.getAttribute("listaPacientes") != null)
		{
			dniPacientes = (ArrayList<Integer>) request.getAttribute("listaPacientes");
		}
	 	%>
		<p class="form-filter">Paciente</p>
        <select name="ddlPaciente">
        	<option></option>
        	<%
		 	if(dniPacientes != null)
				for(Integer dniPaciente : dniPacientes) {
			%> 
			<option><%= dniPaciente %></option>
			<%		 
				}
			%>
        </select>
        
		<p class="form-filter">Día de atención</p>
		<select name="ddlDia">
			<option></option>
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
			<option></option>
			<option>Libre</option>
			<option>Ocupado</option>
			<option>Ausente</option>
			<option>Presente</option>
		</select>
		
		<input type="submit" name="btnFiltrarUser" value="Filtrar" style="margin-left: 5px;"><br>
    </form>
	
	<table id="table_id" class="display">
		<thead>
		<tr>
			<th>DNI Paciente</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Teléfono</th>
			<th>Día del turno</th>
			<th>Hora del turno</th>
			<th>Observacion</th>
			<th>Estado</th>
		</tr>
		</thead>
		<tbody>
		<% 
	    if (listaFiltro != null) {
	        for (Turno turno : listaFiltro) {
	    %>
		<tr>
			<td><%= turno.getPaciente().getDni() %></td>
			<td><%= turno.getPaciente().getNombre() %></td>
			<td><%= turno.getPaciente().getApellido() %></td>
			<td><%= turno.getPaciente().getTelefono() %></td>
			<td><%= turno.getDiaTurno() %></td>
			<td><%= turno.getHoraTurno() %></td>
			<%
			if((turno.getObservacion() == null || turno.getObservacion().isEmpty()) && turno.getEstado().equals("Presente")) {
			%>	
			<td><a href="MarcarObservacionServlet?Param=1&id=<%= turno.getId() %>&dniUsuario=<%= dni %>">Añadir</a></td>
			<%	
			} 
			else {
			%>
			<td><%= turno.getObservacion() %></td>
			<% 
			}
			%>
			<% 
			if (turno.getEstado().equals("Ocupado")) { 
			%>
			<td>
            <a href="MarcarPresenteServlet?id=<%= turno.getId() %>&dniUsuario=<%= dni %>">Marcar Presente</a>
			<a href="MarcarAusenteServlet?id=<%= turno.getId() %>&dniUsuario=<%= dni %>">Marcar Ausente</a>
			</td> 
			<% 
			}
			else { 
			%>
			<td><%= turno.getEstado() %></td>
			<% 
			} 
			%> 
        </tr>
        <% 
	        }
	    } 
	    else {
	    %>
	    <tr>
	        <td colspan="8">Sin resultados.</td>
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
</body>
</html>