<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Paciente - Añadir</title>
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
	
	<form class="add-form" id="add-form" action="${pageContext.request.contextPath}/AgregarPacienteServlet"  method="post" >
		<p class="add-text">Documento (DNI)</p> <input type="text" name="txtDni" maxlength="8" pattern="[0-9]+" title="Por favor, ingresa solo números" required style="width: 70px;"></input> <br>
		<p class="add-text">Nombre</p> <input type="text" name="txtNombre" required style="margin-left:65px;"></input> <br> 
		<p class="add-text">Apellido</p> <input type="text" name="txtApellido" required style="margin-left:60px;"></input> <br>
		<p class="add-text">Género</p> 
		
		<select name="ddlGenero" required style="margin-left:70px;">
			<option> </option>
			<option>Masculino</option>
			<option>Femenino</option>
			<option>Otro</option>
		</select><br>
		
		<p class="add-text">Nacionalidad</p> 
		<select name="ddlNacionalidad" required style="margin-left:33px;">
			<option>   </option>
			<option >Argentina</option>
			<option >Uruguay</option>
			<option >Brasil   </option>
		</select> <br>
		
		<p class="add-text">Fecha Nacimiento</p> <input class="add-input" type="text" name="txtFecha" pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es valida DD/MM/AAAA" required style="width: 90px;"></input> <br>
		<p class="add-text">Dirección</p> <input type="text" name="txtDireccion" required style="margin-left:54px;"></input> <br>
		
		<p class="add-text">Provincia</p>
		<select name="ddlProvincia" id="ddlProvincia" required style="margin-left:57px;">
		    <option value="">Seleccionar</option>
		    <option value="Buenos Aires">Buenos Aires</option>
		    <option value="Cordoba">Córdoba</option>
		    <option value="Mendoza">Mendoza</option>
		</select><br>
	
		<p class="add-text">Localidad</p>
		<select name="ddlLocalidad" id="ddlLocalidad" required style="margin-left:57px;">
		</select><br>
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
			
		<p class="add-text">Correo</p> <input type="email" name="txtCorreo" required style="margin-left:74px;"></input> <br>
		<p class="add-text">Teléfono</p> <input type="text" name="txtTelefono" pattern="[0-9]+" title="Por favor, ingresa solo números" required style="margin-left: 59px; width:100px;"></input> <br>
			
		<input style="margin-top: 10px; margin-left: 150px; margin-bottom: 10px;" type="submit" name= "btnAgregar" value="Añadir"></input>
	</form>
	<script>
	    <% Boolean agrego = (Boolean) request.getAttribute("agrego"); %>
	    <% if (agrego != null && agrego) { %>
	        window.onload = function() {
	            alert("Paciente registrado exitosamente.");
	        };
	    <% } else if (agrego != null && !agrego) { %>
	        window.onload = function() {
	            alert("El paciente no se ha registrado. DNI ya existente.");
	        };
	    <% } %>
	</script>
</body>
</html>