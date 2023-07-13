<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Medico" %>
<%@page import="entidad.Especialidad" %>

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
<title>Medicos</title>
</head>
<body>
	<%
	String usuario = session.getAttribute("nombre").toString();
	
	ArrayList<Medico> listaFiltro = (ArrayList<Medico>) request.getAttribute("listaFiltro");
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
	
	<form action="${pageContext.request.contextPath}/MedicoFiltroServlet?Param=1" method="get">
		<p class="form-filter" style="margin-left: 5px;">Filtrar por...</p>
		<% 
		ArrayList<Medico> listaDocumento = null;
		if(request.getAttribute("listaDni")!= null)
		{
			listaDocumento = (ArrayList<Medico>) request.getAttribute("listaDni");
		}
	 	%>
		<p class="form-filter">DNI</p> 
		<select name="ddlDni">
			<option > </option>
			<%
			 if(listaDocumento !=null)
				for(Medico medi : listaDocumento) {
			%> 
			<option ><%= medi.getDni() %></option>
			<%		 
				}
			 %>
		</select>
		
		<p class="form-filter">Nombre</p> <input type="text" name="txtNombre"></input>
		<p class="form-filter">Apellido</p> <input type="text" name="txtApellido"></input>
		<p class="form-filter">Género</p> 
		
		<select name="ddlGenero">
			<option > </option>
			<option >Femenino</option>
			<option >Masculino</option>
		    <option >Otro</option>
		</select>
		
		<p class="form-filter">Nacionalidad</p>
		<select name="ddlNacionalidad">
			<option >   </option>
			<option >Argentina</option>
			<option >Uruguay</option>
			<option >Brasil   </option>
		</select>
		
		<p class="form-filter">Provincia</p>
		<select name="ddlProvincia" id="ddlProvincia">
		    <option></option>
		    <option>Buenos Aires</option>
		    <option>Cordoba</option>
		    <option>Mendoza</option>
		</select>
		
		<p class="form-filter">Localidad</p>
		<select name="ddlLocalidad" id="ddlLocalidad">
		</select>
		<script>
	    	var provinciaSelect = document.getElementById("ddlProvincia");
	    	var localidadSelect = document.getElementById("ddlLocalidad");
	
	    	var opcionesLocalidad = {
	        "": [],
	        "Buenos Aires": ["Pilar", "Benavidez", "Moreno"],
	        "Cordoba": ["Montecristo", "La Calera", "Los Cedros"],
	        "Mendoza": ["Godoy Cruz", "Guaymallen", "La Paz"]
	    	};
	
		    function cargarLocalidades() {
		        var provinciaSeleccionada = provinciaSelect.value;
		        localidadSelect.innerHTML = "";
		        var localidades = opcionesLocalidad[provinciaSeleccionada];
		        for (var i = 0; i < localidades.length; i++) {
		            var option = document.createElement("option");
		            option.text = localidades[i];
		            localidadSelect.add(option);
		        }
		    }
		    provinciaSelect.addEventListener("change", cargarLocalidades);
		</script>

		<p class="form-filter">Teléfono</p> <input type="text" name="txtTelefono" pattern="[0-9]+" title="Por favor, ingresa solo números" style="width:100px;"></input>
		
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
			<option ><%= espe.getNombre() %></option>
			<%		 
				}
			%>
		</select>
		
		<p class="form-filter">Dia de atencion</p> 
		<select name="ddlDia">
			<option> </option>
			<option>Lunes</option>
			<option>Martes</option>
			<option>Miercoles</option>
			<option>Jueves</option>
			<option>Viernes</option>
		</select>
		<p class="form-filter">Hora inicio de atencion</p>
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
		
		<p class="form-filter">Hora fin de atencion</p>
		<select name="txtHora2" id="txtHora2" style="margin-left: 5px;">
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
		
		<input name="btnFiltrar" class="form-filter" type="submit" value="Filtrar"></input>
	</form>
	
	<table id="table_id" class="display">
	    <thead>
	        <tr>
	            <th>DNI</th>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>Género</th>
	            <th>Nacionalidad</th>
	            <th>Fecha Nacimiento</th>
	            <th>Dirección</th>
	            <th>Localidad</th>
	            <th>Provincia</th>
	            <th>Correo</th>
	            <th>Teléfono</th>
	            <th>Especialidad</th>
	            <th>Día Atención</th>
	            <th>Hora Atención</th>
	        </tr>
	    </thead>
	    <tbody>
	        <% 
	        if (listaFiltro != null) {
	            for (Medico medico : listaFiltro) {
	        %>
	        <tr>
	            <td><%= medico.getDni()%></td>
	            <td><%= medico.getNombre()%></td>
	            <td><%= medico.getApellido()%></td>
	            <td><%= medico.getSexo()%></td>
	            <td><%= medico.getNacionalidad()%></td>
	            <td><%= medico.getFecha_nac()%></td>
	            <td><%= medico.getDireccion()%></td>
	            <td><%= medico.getLocalidad()%></td>
	            <td><%= medico.getProvincia()%></td>
	            <td><%= medico.getCorreo_Electronico()%></td>
	            <td><%= medico.getTelefono()%></td>
	            <td><%= medico.getEspecialidad().getNombre()%></td>
	            <td><%= medico.getDia_atencion()%></td>
	            <td><%= medico.getHora_inicio_atencion()%>:00-<%= medico.getHora_fin_atencion()%>:00</td>
	        </tr>
	        <% 
	            }
	        } 
	        else {
	        %>
	        <tr>
	            <td colspan="14">Sin resultados.</td>
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
	
	<form action="MedicoServlet"> 
		<input type="hidden" name="Param" value="2">
		<input type="submit" value="Añadir médico" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
	<form action="MedicoServlet">
		<input type="hidden" name="Param" value="3">
		<input type="submit" value="Editar médico" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
	<form action="MedicoServlet">
		 <input type="hidden" name="Param" value="4">
		<input type="submit" value="Borrar médico" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
</body>
</html>