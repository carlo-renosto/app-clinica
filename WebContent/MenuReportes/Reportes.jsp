<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno" %>
<%@page import="entidad.Medico" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<title>Reporte Turnos</title>
</head>
<body>
	<%
	String usuario = session.getAttribute("nombre").toString();
	ArrayList<Turno> reporteTurnos = (ArrayList<Turno>) request.getAttribute("turnos");
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
	
	<form action="${pageContext.request.contextPath}/ReporteServlet" method="get">
		<% 
		ArrayList<Medico> listaDocumento = null;
		if(request.getAttribute("listaDni")!= null)
		{
			listaDocumento = (ArrayList<Medico>) request.getAttribute("listaDni");
		}
		%>
        <label class="form-filter" for="dniMedico" style="margin-top: 10px;">DNI del médico:</label>
        <select id="dniMedico" name="dniMedico" required>
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
        
        <p class="form-filter">Horario desde:</p>
		<select name="horarioDesde" id="txtHora" required style="margin-left: 5px;">
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
		
		<p class="form-filter">Horario hasta:</p>
		<select name="horarioHasta" id="txtHora2" required style="margin-left: 5px;">
			<option></option>
		</select>
		<script>
			var horaIniSel = document.getElementById("txtHora");
	    	var horaFinSel = document.getElementById("txtHora2");
	    	
	    	function cargarHorasFin() {
	    		var horaSel = horaIniSel.value;
	    		horaFinSel.innerHTML = "";
	    		
	    		for(var i=parseInt(horaSel)+1;i<=18;i++) {
	    			var option = document.createElement("option");
	    			option.text = i;
	    			horaFinSel.add(option);
	    		}
	    	}
	    	horaIniSel.addEventListener("change", cargarHorasFin);
		</script>

        <input type="submit" value="Generar reporte">
    </form>
    
    <h3>Reporte de Turnos</h3>
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
	        if (reporteTurnos != null) {
	            for (Turno turno : reporteTurnos) {
	        %>
	        <tr>
	            <td><%= turno.getEspecialidadNombre()%></td>
	            <td><%= turno.getMedico().getDni()%></td>
	            <td><%= turno.getPaciente().getDni()%></td>
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
</body>
</html>
