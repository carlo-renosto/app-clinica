package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import negocioImpl.NMedicoImpl;

@WebServlet("/MedicoFiltroServlet")
public class MedicoFiltroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MedicoFiltroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String consulta = "";
    	
    	if(request.getParameter("btnFiltrar") != null) {
    		String valorNombre = request.getParameter("txtNombre");
        	String valorApellido = request.getParameter("txtApellido");
        	String valorTelefono = request.getParameter("txtTelefono");
        	String valorGenero = request.getParameter("ddlGenero");
        	String valorNacionalidad = request.getParameter("ddlNacionalidad");
        	String valorProvincia = request.getParameter("ddlProvincia");
        	String valorLocalidad = request.getParameter("ddlLocalidad");
        	String valorDni = request.getParameter("ddlDni");
        	String valorEspecialidad = request.getParameter("ddlEspecialidad");
        	String valorDia = request.getParameter("ddlDia");
        	String valorHora = request.getParameter("txtHora");
        	String valorHora2 = request.getParameter("txtHora2");
        	
        	int cont = 0;
        	boolean condicionesFiltrado = false;

        	if((valorNombre != null && !valorNombre.isEmpty()) || (valorApellido != null && !valorApellido.isEmpty()) || (valorTelefono != null && !valorTelefono.isEmpty()) ||
            	(valorGenero != null && !valorGenero.isEmpty()) || (valorNacionalidad != null && !valorNacionalidad.isEmpty()) || (valorProvincia != null && !valorProvincia.isEmpty()) ||
            	(valorLocalidad != null && !valorLocalidad.isEmpty()) || (valorDni != null && !valorDni.isEmpty()) || (valorEspecialidad != null && !valorEspecialidad.isEmpty()) ||
            	(valorDia != null && !valorDia.isEmpty()) || (valorHora != null && !valorHora.isEmpty()) || (valorHora2 != null && !valorHora2.isEmpty())) {
            	    
        		consulta += " WHERE ";
            	    
        		if(valorDni != null && !valorDni.isEmpty()) {
            	    consulta += " Dni = '" + valorDni + "'";
            	    cont = cont + 1;
            	}
        		if(valorNombre != null && !valorNombre.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Nombre LIKE '%" + valorNombre + "%'";
            	    cont = cont + 1;
        		}
            	if(valorApellido != null && !valorApellido.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Apellido LIKE '%" + valorApellido + "%'";
            	    cont = cont + 1;
            	}
            	if(valorGenero != null && !valorGenero.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Sexo = '" + valorGenero + "'";
            	    cont = cont + 1;
            	}
            	if(valorNacionalidad != null && !valorNacionalidad.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Nacionalidad = '" + valorNacionalidad + "'";
            	    cont = cont + 1;
            	}
            	if((valorProvincia != null && !valorProvincia.isEmpty()) && (valorLocalidad != null && !valorLocalidad.isEmpty())) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Provincia = '" + valorProvincia + "' AND Localidad = '" + valorLocalidad + "'";
            	    cont = cont + 1;
            	}
            	if(valorTelefono != null && !valorTelefono.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Telefono LIKE '%" + valorTelefono + "%'";
            	    cont = cont + 1;
            	}
            	if(valorEspecialidad != null && !valorEspecialidad.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Especialidad LIKE '%" + valorEspecialidad + "%'";
            	    cont = cont + 1;
            	}
            	if(valorDia != null && !valorDia.isEmpty()) {
            		if(cont > 0) consulta += " AND ";
            	    consulta += " Dia_Atencion LIKE '%" + valorDia + "%'";
            	    cont = cont + 1;
            	}
            	if(valorHora != null && !valorHora.isEmpty()) {
            	    if(cont > 0) consulta += " AND ";
            	    consulta += " Hora_Inicio_Atencion >= '" + valorHora + "'";
            	    cont = cont + 1;
            	}
            	if(valorHora2 != null && !valorHora2.isEmpty()) {
            		if(cont > 0) consulta += " AND ";
            		consulta += " Hora_Fin_Atencion <= '" + valorHora2 + "' AND Hora_Inicio_Atencion >= '" + valorHora + "'";
            		cont = cont + 1;
            	}
            	consulta += " AND Estado = '1'";
            	condicionesFiltrado = true; 
        	}      	    
        
        	if (!condicionesFiltrado) {
                consulta += " WHERE Estado = '1'";
            }
        }

    	NMedicoImpl medicoImpl = new NMedicoImpl();
    	ArrayList<Medico> lista = medicoImpl.filtro(consulta);
    	
        request.setAttribute("listaFiltro", lista);
        	
        RequestDispatcher rd = request.getRequestDispatcher("MedicoServlet?Param=1");
        rd.forward(request, response);
	}	
}

