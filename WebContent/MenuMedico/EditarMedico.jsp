<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Medico" %>
<%@page import="entidad.Especialidad" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Medico - Editar</title>
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
    
    <form id="carga-form" method="get" action="${pageContext.request.contextPath}/EditarMedicoServlet">
		<input type="hidden" name="Param" value="1">
         
		<p class="form-filter">DNI</p>
    	<select name="ddlDni" onchange="this.form.submit()">
			<option>  </option>
	        <% 
	        ArrayList<Medico> listaDocumento = null;
	        if(request.getAttribute("listaDni") != null) {
	            listaDocumento = (ArrayList<Medico>) request.getAttribute("listaDni");
	        }
	        
	        if (listaDocumento != null && !listaDocumento.isEmpty()) {
	            for(Medico medi : listaDocumento) {
	        %>
	        <option><%= medi.getDni() %></option>
	        <% 	} 
			} 
			%>
		</select><br> 
    </form>
    
    <% 
    ArrayList<Medico> listaCompleta = null;
    if(request.getAttribute("listaCompleta") != null) {
        listaCompleta = (ArrayList<Medico>) request.getAttribute("listaCompleta");
    }
    
    if(listaCompleta != null && !listaCompleta.isEmpty()) {
        for(Medico me : listaCompleta) {
    %>
    <form class="add-form" id="edit-form" method="get" action="${pageContext.request.contextPath}/EditarMedicoServlet">
        <input type="hidden" name="Param" value="2">
       
        <p class="add-text">DNI</p><input type="text" value='<%=me.getDni() %>' name="txtDni" readonly style="margin-left:90px;"><br>
        <p class="add-text">Nombre</p> <input type="text" value='<%=me.getNombre() %>' name="txtNombre" required style="margin-left:61px;"><br>
        <p class="add-text">Apellido</p> <input type="text" value='<%=me.getApellido() %>' name="txtApellido" required style="margin-left:60px;"><br>
		<p class="add-text">Género</p>
		<select name="ddlGenero" required style="margin-left:70px;">
			<% 
		    String generoActual = me.getSexo();
		    String[] generos = {"Femenino", "Masculino", "Otro"};
		    
		    for (String genero : generos) {
		        if (genero.equals(generoActual)) {
		    %>
		    <option selected><%= genero %></option>
		    <% 	} 
		        else { 
			%>
		    <option><%= genero %></option>
		    <% 	} 
			} 
			%>
		</select><br>
		
        <p class="add-text">Nacionalidad</p>
		<select name="ddlNacionalidad" required style="margin-left:33px;">
		    <% 
		    String nacionalidadActual = me.getNacionalidad();
		    String[] nacionalidades = {"Argentina", "Uruguay", "Brasil"};
		    
		    for (String nacionalidad : nacionalidades) {
		        if (!nacionalidad.equals(nacionalidadActual)) {
		    %>
		    <option><%= nacionalidad %></option>
		    <% 	} 
		    } 
		    %>
		</select><br>
		
        <p class="add-text">Fecha Nacimiento</p> <input class="add-input" type="text" name="txtFecha" pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es valida DD/MM/AAAA" value=<%=me.getFecha_nac() %> required style="width: 90px;"><br>
        <p class="add-text">Dirección</p> <input type="text" name="txtDireccion" value='<%=me.getDireccion() %>' required style="margin-left:54px;"><br>
      	<p class="add-text">Provincia</p>
		<select name="ddlProvincia" id="ddlProvincia" required style="margin-left:57px;">
		    <option><%= me.getProvincia() %></option>
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
		
		    var localidadActual = '<%= me.getLocalidad() %>';
		
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
		
		    cargarLocalidades('<%= me.getLocalidad() %>');
		
		    provinciaSelect.addEventListener("change", function () {
		        cargarLocalidades('');
		    });
		</script><br>
		
        <p class="add-text">Correo</p> <input type="email" name="txtCorreo" value= <%=me.getCorreo_Electronico() %> required style="margin-left:74px;"><br>
        <p class="add-text">Teléfono</p> <input type="text" name="txtTelefono" pattern="[0-9]+" title="Por favor, ingresa solo números" value=<%=me.getTelefono() %> required style="margin-left: 59px; width:100px;"><br>
        
        <% 
        ArrayList<Especialidad> listaEsp = null;
		if(request.getAttribute("listaEsp")!= null)
		{
			listaEsp = (ArrayList<Especialidad>) request.getAttribute("listaEsp");
		}
	 	%>
		<p class="add-text">Especialidad</p>
		<select name="ddlEspecialidad" required style="margin-left: 39px;">
	    	<option><%= me.getEspecialidad().getNombre() %></option>
		    <% 
		    if (listaEsp != null) {
		        for (Especialidad espe : listaEsp) {
		            if (!espe.getNombre().equals(me.getEspecialidad().getNombre())) {
		    %>
		    <option><%= espe.getNombre() %></option>
		    <%		 
		            }
		        }
		    }
		    %>
		</select><br>
		
		<p class="add-text">Dia de atencion</p>
		<select name="ddlDia" required style="margin-left: 17px;" onchange="showSelectedOption(this)">
			<% 
			String diaSeleccionado = me.getDia_atencion();
			String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
			boolean isOptionSelected = false;
			    
			for (String dia : dias) {
				if (dia.equals(diaSeleccionado)) {
					if (!isOptionSelected) {
						isOptionSelected = true;
			%>
			<option><%= dia %></option>
			<% 
					}
				} else {
			%>
			<option><%= dia %></option>
			<% 
				}
			}
			%>
		</select><br>
		
		<script>
			function showSelectedOption(selectElement) {
				var selectedOption = selectElement.value;
				var options = selectElement.options;
				var isOptionSelected = false;
				
				for (var i = 0; i < options.length; i++) {
					if (options[i].value === selectedOption) {
						if (!isOptionSelected) {
							isOptionSelected = true;
						} else {
							selectElement.remove(i);
							break;
						}
					}
				}
			}
		</script>
	
		<p class="add-text">Hora inicio</p>
		<select name="txtHora" id="txtHora" required style="margin-left: 45px;" onchange="cargarHorasFin()">
		    <% 
		    int horaInicio = me.getHora_inicio_atencion();
		    int[] horas = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
		    
		    for (int hora : horas) {
		        %>
		        <option <%= hora == horaInicio ? "selected" : "" %>><%= hora %></option>
		        <% 
		    } 
		    %>
		</select><br>
		
		<p class="add-text">Hora fin</p>
		<select name="txtHora2" id="txtHora2" required style="margin-left: 63px;"></select><br>
		
		<script>
		    var horaIniSel = document.getElementById("txtHora");
		    var horaFinSel = document.getElementById("txtHora2");
		    
		    function cargarHorasFin() {
		        var horaSel = parseInt(horaIniSel.value);
		        horaFinSel.innerHTML = "";
		        
		        for (var i = horaSel + 1; i <= 18; i++) {
		            var option = document.createElement("option");
		            option.text = i;
		            horaFinSel.add(option);
		        }
		    }
		    
		    cargarHorasFin();
		</script>
        <input style="margin-top: 10px; margin-left: 150px; margin-bottom: 10px;" type="submit" name="btnEditar" value="Editar">
    </form>
    <% 
    }
    	} 
    %>
    
    <script>
	    <% Boolean edito = (Boolean) request.getAttribute("edito"); %>
	    <% if (edito != null && edito) { %>
	        window.onload = function() {
	            alert("Médico editado exitosamente.");
	        };
	    <% } else if (edito != null && !edito) { %>
	        window.onload = function() {
	            alert("Médico no editado. (Error)");
	        };
	    <% } %>
	</script>
</body>
</html>