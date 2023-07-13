 package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Paciente;
import exceptions.PacienteException;
import negocioImpl.NPacienteImpl;

@WebServlet("/AgregarPacienteServlet")
public class AgregarPacienteServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

    public AgregarPacienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    boolean agrego = false;
	    
	    try {
		    if(request.getParameter("btnAgregar") != null) {
		        String dni = request.getParameter("txtDni");
		        String nombre = request.getParameter("txtNombre");
		        String apellido = request.getParameter("txtApellido");
		        String genero = request.getParameter("ddlGenero");
		        String nacionalidad = request.getParameter("ddlNacionalidad");
		        String fechaNac = request.getParameter("txtFecha");
		        String direccion = request.getParameter("txtDireccion");
		        String localidad = request.getParameter("ddlLocalidad");
		        String correo = request.getParameter("txtCorreo");
		        String telefono = request.getParameter("txtTelefono");
		        String provincia = request.getParameter("ddlProvincia");
		        
		        Paciente paciente = new Paciente(Integer.parseInt(dni), nombre, apellido, genero, nacionalidad, fechaNac, direccion, localidad, correo, telefono, provincia);
		        
		        NPacienteImpl pacienteImpl = new NPacienteImpl();
		        if(pacienteImpl.existeDni(paciente.getDni()) > 0) {
		        	throw new PacienteException("DNI ya registrado");
		        } 
		        else {
		        	if(pacienteImpl.agregarPaciente(paciente) != 0) {
		        		agrego = true;
		        		request.setAttribute("agrego", agrego);
		        	}
		        	else {
		        		request.setAttribute("agrego", agrego);
		        	}
		        }
		    }
		    else {
	            throw new PacienteException("Formulario no enviado correctamente");
	        }  
	    }
	    catch(PacienteException e) {
	    	e.printStackTrace();
            request.setAttribute("agrego", agrego);
	    }
	    
	    RequestDispatcher rd = request.getRequestDispatcher("PacienteServlet?Param=2");
        rd.forward(request, response);
	}
}