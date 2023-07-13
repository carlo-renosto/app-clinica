package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import exceptions.MedicoException;
import negocioImpl.NMedicoImpl;

@WebServlet("/AgregarMedicoServlet")
public class AgregarMedicoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AgregarMedicoServlet() {
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
                String especialidad = request.getParameter("ddlEspecialidad");
                String dia = request.getParameter("ddlDia");
                String horaInicio = request.getParameter("txtHora");
                String horaFin = request.getParameter("txtHora2");

                Medico medico = new Medico(Integer.parseInt(dni), nombre, apellido, genero, nacionalidad, fechaNac, direccion, localidad, correo, telefono, provincia, especialidad, dia, Integer.parseInt(horaInicio), Integer.parseInt(horaFin));

                NMedicoImpl medicoImpl = new NMedicoImpl();
                if(medicoImpl.existeDni(medico.getDni()) > 0) {
                    throw new MedicoException("DNI ya registrado");
                }
                else {
                    if(medicoImpl.agregarMedico(medico) != 0) {
                    	agrego = true;
        	            request.setAttribute("agrego", agrego);
                    }
                }
            } 
            else {
                throw new MedicoException("Formulario no enviado correctamente");
            }   
        } 
        catch(MedicoException e) {
            e.printStackTrace();
            request.setAttribute("agrego", agrego);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("MedicoServlet?Param=2");
        rd.forward(request, response);
    }
}
