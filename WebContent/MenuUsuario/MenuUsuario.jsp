<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/styles.css?v=1" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    
    <title>Usuarios</title>
	</head>
	<body>
		<%
		String usuario = session.getAttribute("nombre").toString();
		
		ArrayList<Usuario> listaFiltro = (ArrayList<Usuario>) request.getAttribute("listaFiltro");
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
	    
	    <table id="table_id" class="display">
	    <thead>
	        <tr>
	            <th>DNI</th>
	            <th>Usuario</th>
	            <th>Tipo</th>
	        </tr>
	    </thead>
	    <tbody>
	        <% 
	        if (listaFiltro != null) {
	            for (Usuario user : listaFiltro) {
	        %>
	        <tr>
	            <td><%= user.getDni() %></td>
	            <td><%= user.getNombre() %></td>
	            <% 
	            if(user.isEsAdmin()) {
	            %>
	            <td>Admin</td>
	            <%
	            }
	            else {
	            %>
	            <td>Médico</td>
	            <%
	            }
	            %>
	        </tr>
	        <% 
	            }
	        } else {
	        %>
	        <tr>
	            <td colspan="4">Sin resultados.</td>
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
	 
	<form action="UsuarioServlet" method="get"> 
    	<input type="hidden" name="Param" value="2">
		<input type="submit" value="Añadir usuario" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
	<form action="UsuarioServlet" method="get"> 
    	<input type="hidden" name="Param" value="3">
		<input type="submit" value="Editar usuario" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
	<form action="UsuarioServlet" method="get"> 
    	<input type="hidden" name="Param" value="4">
		<input type="submit" value="Borrar usuario" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
	</body>
</html>