<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Paciente" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Paciente - Editar</title>
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
    
    <form id="carga-form" action="${pageContext.request.contextPath}/EditarPacienteServlet" method="get">
         <input type="hidden" name="Param" value="1">
         
         <p class="form-filter">DNI</p>
         <select name="ddlDni" onchange="this.form.submit()">
         	<option>  </option>
	        <% 
	        ArrayList<Paciente> listaDocumento = null;
	        if(request.getAttribute("listaDni") != null) {
	            listaDocumento = (ArrayList<Paciente>) request.getAttribute("listaDni");
	        }
	        
	        if (listaDocumento != null && !listaDocumento.isEmpty()) {
	            for(Paciente paci : listaDocumento) {
	        %>
	        <option><%= paci.getDni() %></option>
	        <% 	} 
	        } 
	        %>
    	</select><br> 
    </form>
    
    <% 
    ArrayList<Paciente> listaCompleta = null;
    if(request.getAttribute("listaCompleta") != null) {
        listaCompleta = (ArrayList<Paciente>) request.getAttribute("listaCompleta");
    }
    
    if(listaCompleta != null && !listaCompleta.isEmpty()) {
        for(Paciente pa : listaCompleta) {
    %>
    <form class="add-form" id="edit-form" method="get" action="${pageContext.request.contextPath}/EditarPacienteServlet">
        <input type="hidden" name="Param" value="2">
        
        <p class="add-text">DNI</p><input type="text" value='<%=pa.getDni() %>' name="txtDni" readonly style="margin-left:90px;"><br>
        <p class="add-text">Nombre</p> <input type="text" value='<%=pa.getNombre() %>' name="txtNombre" required style="margin-left:61px;"><br>
        <p class="add-text">Apellido</p> <input type="text" value='<%=pa.getApellido() %>' name="txtApellido" required style="margin-left:60px;"><br>
        <p class="add-text">Género</p>
        <select name="ddlGenero" required style="margin-left:70px;">
		    <% 
		    String generoActual = pa.getSexo();
		    String[] generos = {"Femenino", "Masculino", "Otro"};
		
		    for (String genero : generos) {
		        if (genero.equals(generoActual)) {
		    %>
		    <option selected><%= genero %></option>
		    <% 	} 
		       	else { 
		    %>
		    <option><%= genero %></option>
		    <% 	
		    	} 
			} 
			%>
    	</select><br>
    
		<p class="add-text">Nacionalidad</p>
		<select name="ddlNacionalidad" required style="margin-left:33px;">
		    <% 
		    String nacionalidadActual = pa.getNacionalidad();
		    String[] nacionalidades = {"Argentina", "Uruguay", "Brasil"};
		
		    for (String nacionalidad : nacionalidades) {
		        if (nacionalidad.equals(nacionalidadActual)) {
		    %>
		    <option selected><%= nacionalidad %></option>
		    <% 	} 
		       	else { %>
		    <option><%= nacionalidad %></option>
		    <% 	} 
			} 
			%>
		</select><br>
		
        <p class="add-text">Fecha Nacimiento</p> <input class="add-input" type="text"  pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es valida DD/MM/AAAA" name="txtFecha" value=<%=pa.getFecha_nac() %> required style="width: 90px;"><br>
        <p class="add-text">Dirección</p> <input type="text" name="txtDireccion" value='<%=pa.getDireccion() %>' required style="margin-left:54px;"><br>
        
        <p class="add-text">Provincia</p>
		<select name="ddlProvincia" id="ddlProvincia" required style="margin-left:57px;">
		    <option><%= pa.getProvincia() %></option>
		    <option>Buenos Aires</option>
		    <option>Cordoba</option>
		    <option>Mendoza</option>
		</select>

		<script>
		    var provinciasAgregadas = new Set();
		    var selectProvincia = document.getElementById("ddlProvincia");
		    var opcionesProvincia = selectProvincia.getElementsByTagName("option");
		    for (var i = 0; i < opcionesProvincia.length; i++) {
		        var provincia = opcionesProvincia[i].innerText;
		        if (provinciasAgregadas.has(provincia)) {
		            selectProvincia.removeChild(opcionesProvincia[i]);
		            i--; 
		        } else {
		            provinciasAgregadas.add(provincia);
		        }
		    }
		
		    var localidadActual = '<%= pa.getLocalidad() %>';
		
		    cargarLocalidades(localidadActual);
		</script><br>
		
		<p class="add-text">Localidad</p>
		<select name="ddlLocalidad" id="ddlLocalidad" required style="margin-left:57px;">
		</select>
		
		<script>
		    var provinciaSelect = document.getElementById("ddlProvincia");
		    var localidadSelect = document.getElementById("ddlLocalidad");
		
		    var opcionesLocalidad = {
		        "Buenos Aires": ["Pilar", "Benavidez", "Moreno"],
		        "Cordoba": ["Montecristo", "La Calera", "Los Cedros"],
		        "Mendoza": ["Godoy Cruz", "Guaymallen", "La Paz"]
		    };
		
		    function cargarLocalidades(localidadActual) {
		        var provinciaSeleccionada = provinciaSelect.value;
		        var localidades = opcionesLocalidad[provinciaSeleccionada];
		
		        var localidadesFiltradas = localidades.filter(function (localidad) {
		            return localidad !== localidadActual && !localidadSelect.querySelector('option[value="' + localidad + '"]');
		        });
	
		        localidadSelect.innerHTML = "";
		
		        for (var i = 0; i < localidadesFiltradas.length; i++) {
		            var option = document.createElement("option");
		            option.value = localidadesFiltradas[i];
		            option.text = localidadesFiltradas[i];
		            localidadSelect.add(option);
		        }
		
		        if (localidadActual) {
		            var opcionActual = document.createElement("option");
		            opcionActual.value = localidadActual;
		            opcionActual.text = localidadActual;
		            opcionActual.selected = true;
		            localidadSelect.add(opcionActual);
		        }
		    }
		
		    cargarLocalidades('<%= pa.getLocalidad() %>');
		
		    provinciaSelect.addEventListener("change", function () {
		        cargarLocalidades('');
		    });
		</script><br>
		
        <p class="add-text">Correo</p> <input type="email" name="txtCorreo" value= <%=pa.getCorreo_Electronico() %> required style="margin-left:74px;"><br>
        <p class="add-text">Teléfono</p> <input type="text" name="txtTelefono" pattern="[0-9]+" title="Por favor, ingresa solo números" value=<%=pa.getTelefono() %> required style="margin-left: 59px; width:100px;"><br>
        
        <input style="margin-top: 10px; margin-left: 150px; margin-bottom: 10px;" type="submit" name="btnEditar" value="Editar">
    </form>
    <%	}
     } 
     %>

	<script>
	    <% Boolean edito = (Boolean) request.getAttribute("edito"); %>
	    <% if (edito != null && edito) { %>
	        window.onload = function() {
	            alert("Paciente editado exitosamente.");
	        };
	    <% } else if (edito != null && !edito) { %>
	        window.onload = function() {
	            alert("Paciente no editado. (Error)");
	        };
	    <% } %>
	</script>
</body>
</html>