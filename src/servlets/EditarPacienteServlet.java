package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Paciente;
import exceptions.PacienteException;
import negocioImpl.NPacienteImpl;

@WebServlet("/EditarPacienteServlet")
public class EditarPacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarPacienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");

		if(paramValue != null && paramValue.equals("1")) {
			NPacienteImpl pacienteImpl = new NPacienteImpl();
			ArrayList<Paciente> pacientes = pacienteImpl.getTodoDni(Integer.parseInt(request.getParameter("ddlDni")));
			
			request.setAttribute("listaCompleta", pacientes);
			
			RequestDispatcher rd = request.getRequestDispatcher("PacienteServlet?Param=3");
			rd.forward(request, response);
		}

		if(paramValue != null && paramValue.equals("2")) {
			boolean aux = false;
			
			try {
				if (request.getParameter("btnEditar") != null) {
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
	                if(pacienteImpl.editarPaciente(paciente) > 0) {
	                	aux= true;
		                request.setAttribute("edito", aux);
					}
	                else {
	                	throw new PacienteException("Edicion del paciente fallida");
					}				
				} 
				else {
					throw new PacienteException("Formulario no enviado correctamente");
				}
			}
			catch(PacienteException e) {
				e.printStackTrace();
				request.setAttribute("edito", aux);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("PacienteServlet?Param=3");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

