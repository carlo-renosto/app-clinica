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
import exceptions.MedicoException;
import negocioImpl.NMedicoImpl;

@WebServlet("/EditarMedicoServlet")
public class EditarMedicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarMedicoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");

		if(paramValue != null && paramValue.equals("1")) {
			NMedicoImpl medicoImpl = new NMedicoImpl();
			ArrayList<Medico> medicos = medicoImpl.getTodoDni(Integer.parseInt(request.getParameter("ddlDni")));
			
			request.setAttribute("listaCompleta", medicos);
			
			RequestDispatcher rd = request.getRequestDispatcher("MedicoServlet?Param=3");
			rd.forward(request, response);
		}

		if(paramValue != null && paramValue.equals("2")) {
			boolean aux = false;			
			
			try {
				if(request.getParameter("btnEditar") != null) {
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
		            String especialidad = request.getParameter("ddlEspecialidad");
		            String dia = request.getParameter("ddlDia");
		            String horaInicio = request.getParameter("txtHora");
		            String horaFin = request.getParameter("txtHora2");
						
		            Medico medico = new Medico(Integer.parseInt(dni), nombre, apellido, genero, nacionalidad, fechaNac, direccion, localidad, correo, telefono, provincia, especialidad, dia, Integer.parseInt(horaInicio), Integer.parseInt(horaFin));
					
					NMedicoImpl medicoImpl = new NMedicoImpl();
					if(medicoImpl.editarMedico(medico) > 0) {
						aux = true;
			            request.setAttribute("edito", aux);
					}
					else {
						throw new MedicoException("Edicion del medico fallida");
					}
				} 
				else {
					throw new MedicoException("Formulario no enviado correctamente");
				}
			}
			catch(MedicoException e) {
				e.printStackTrace();
				request.setAttribute("edito", aux);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("MedicoServlet?Param=3");
			rd.forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

