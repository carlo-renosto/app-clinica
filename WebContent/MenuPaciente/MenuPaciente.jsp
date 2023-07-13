<%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.PacienteImpl" %>
<%@page import="entidad.Paciente" %>

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
<title>Pacientes</title>
</head>
<body>
	<%
	String usuario = session.getAttribute("nombre").toString();
	
	ArrayList<Paciente> listaFiltro = (ArrayList<Paciente>) request.getAttribute("listaFiltro");
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
	
	<form method="get" action="${pageContext.request.contextPath}/PacienteFiltroServlet?Param=1">
		<p class="form-filter" style="margin-left: 5px;">Filtrar por...</p>
		<% 
		ArrayList<Paciente> listaDocumento = null;
		if(request.getAttribute("listaDni")!= null)
		{
			listaDocumento = (ArrayList<Paciente>) request.getAttribute("listaDni");
		}
	
	 	%>
		<p class="form-filter">DNI</p> 
		<select name="ddlDni">
		<option > </option>
		<%
		 if(listaDocumento !=null)
			for(Paciente paci : listaDocumento) {
		%> 
		<option ><%= paci.getDni() %></option>
		<%		 
			}
		 %>
		</select>
		
		<p class="form-filter">Nombre</p> <input type="text" name="txtNombre"></input>
		<p class="form-filter">Apellido</p> <input type="text" name="txtApellido"></input>
		<p class="form-filter">Género</p> 
		<select name="ddlGenero">
			<option> </option>
			<option>Femenino</option>
			<option>Masculino</option>
		    <option>Otro</option>
		</select>
		 
		<p class="form-filter">Nacionalidad</p> 
		<select name="ddlNacionalidad">
			<option>   </option>
			<option>Argentina</option>
			<option>Uruguay</option>
			<option>Brasil</option>
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
		
		<p class="form-filter">Teléfono</p> <input type="text" name="txtTelefono" pattern="[0-9]+" title="Por favor, ingresa solo números"  style="width:100px;"></input>
		<input type="hidden" name="Param" value="1">
		<input name="btnFiltrar" class="form-filter" type="submit" value="Filtrar"></input><br><br>
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
	        </tr>
	    </thead>
	    <tbody>
	        <% 
	        if (listaFiltro != null) {
	            for (Paciente paciente : listaFiltro) {
	        %>
	        <tr>
	            <td><%= paciente.getDni()%></td>
	            <td><%= paciente.getNombre()%></td>
	            <td><%= paciente.getApellido()%></td>
	            <td><%= paciente.getSexo()%></td>
	            <td><%= paciente.getNacionalidad()%></td>
	            <td><%= paciente.getFecha_nac()%></td>
	            <td><%= paciente.getDireccion()%></td>
	            <td><%= paciente.getLocalidad()%></td>
	            <td><%= paciente.getProvincia()%></td>
	            <td><%= paciente.getCorreo_Electronico()%></td>
	            <td><%= paciente.getTelefono()%></td>
	        </tr>
	        <% 
	            }
	        } 
	        else {
	        %>
	        <tr>
	            <td colspan="11">Sin resultados.</td>
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
 
	<form action="PacienteServlet">
		<input type="hidden" name="Param" value="2">
  		<input type="submit" value="Añadir paciente" style="margin-top: 20px; margin-left: 10px; display: block;">
	</form>
	<form action="PacienteServlet">
		<input type="hidden" name="Param" value="3">
		<input type="submit" value="Editar paciente" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
	<form action="PacienteServlet">
	 	<input type="hidden" name="Param" value="4">
		<input type="submit" value="Borrar paciente" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
</body>
</html>