<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Medico" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Medico - Borrar</title>
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
    
    <form action="${pageContext.request.contextPath}/BorrarMedicoServlet" method="post">
    	<% 
        ArrayList<Medico> listaDocumento = null;
        if(request.getAttribute("listaDni")!= null) {
            listaDocumento = (ArrayList<Medico>) request.getAttribute("listaDni");
        }
    
    	%>
        <p class="add-text">DNI</p>
        <select name="ddlDni" style="margin-left: 10px;">
	        <option> </option>
	        <%
	        if(listaDocumento !=null) {
				for(Medico medi : listaDocumento) {
	        %> 
	        <option><%= medi.getDni() %></option>
	        <%         
	            }
	         }
	         %>
        </select>
        <input type="hidden" name="Param" value="4">
        <button type="submit" onclick="return confirmarEliminacion()">Borrar</button>
    </form>
   <script>
	   function confirmarEliminacion() {
	   		var ddlDni = document.getElementsByName("ddlDni")[0];
		    if (ddlDni.value === "") {
		    alert("Seleccionar un médico.");
			return false; // Detener el envío del formulario
		  }
		    
	      var confirmacion = window.confirm("¿Confirmar la eliminación de este médico?");
	      
	      if (!confirmacion) {
	        alert("Eliminación cancelada.");
	        return false; // Detener el envío del formulario
	      }
	      
	      alert("Médico eliminado exitosamente.");
	      return true; // Permitir el envío del formulario
	  	}
  	</script>
</body>
</html>