<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Usuarios - Editar</title>
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
    
    <form id="carga-form" action="${pageContext.request.contextPath}/EditarUsuarioServlet?Param=1" method="post">
         <p class="form-filter">DNI</p>
         <select name="ddlDni" onchange="this.form.submit()">
         	<option></option>
	        <% 
	        ArrayList<Integer> listaDocumento = null;
	        if(request.getAttribute("listaDni") != null) {
	            listaDocumento = (ArrayList<Integer>) request.getAttribute("listaDni");
	        }
	        
	        if (listaDocumento != null && !listaDocumento.isEmpty()) {
	            for(Integer dni : listaDocumento) {
	        %>
	        <option><%= dni %></option>
	        <% 	} 
	        } 
	        %>
    	</select><br> 
    </form>
    
    <% 
    Usuario user = null;
    if(request.getAttribute("usuario") != null) {
    	user = (Usuario) request.getAttribute("usuario");
    }
    
    if(user != null) {
    %>
    <form class="add-form" id="edit-form" method="post" action="${pageContext.request.contextPath}/EditarUsuarioServlet?Param=2">
    	<input type="hidden" value='<%=user.getId()%>' name="txtId">
        <p class="add-text">DNI</p><input type="text" value='<%=user.getDni() %>' name="txtDni" readonly style="margin-left:50px;"><br>
        <p class="add-text">Usuario</p><input type="text" value= '<%=user.getNombre()%>' name="txtUsuario" style="margin-left:30px;"><br>
        <p class="add-text">Contraseña</p><input type="password" value='<%=user.getContra()%>' name="txtContrasena" style="margin-left:10px;"><br>
        <p class="add-text">Tipo</p>
        <select name="ddlTipo" style="margin-left:50px;">
        	<%
        	if(user.isEsAdmin()) {
        	%>
        	<option>Admin</option>
        	<option>Medico</option>
        	<% 	
        	}
        	else {
        	%>
        	<option>Medico</option>
        	<option>Admin</option>
			<%
        	}
			%>
        </select><br>
        
        <input style="margin-top: 10px; margin-left: 150px; margin-bottom: 10px;" type="submit" name="btnEditar" value="Editar">
    </form>
    <%	
     } 
     %>
     
     <script>
	    <% Boolean edito = (Boolean) request.getAttribute("edito"); %>
	    <% if (edito != null && edito) { %>
	        window.onload = function() {
	            alert("Usuario editado exitosamente.");
	        };
	    <% } else if (edito != null && !edito) { %>
	        window.onload = function() {
	            alert("Usuario no editado. (Error)");
	        };
	    <% } %>
	</script>
</body>
</html>