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
import negocioImpl.NPacienteImpl;

@WebServlet("/PacienteFiltroServlet")
public class PacienteFiltroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PacienteFiltroServlet() {
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
            
            int cont = 0;
            boolean condicionesFiltrado = false;

            if((valorNombre != null && !valorNombre.isEmpty()) || (valorApellido != null && !valorApellido.isEmpty()) || (valorTelefono != null && !valorTelefono.isEmpty()) ||
            	(valorGenero != null && !valorGenero.isEmpty()) || (valorNacionalidad != null && !valorNacionalidad.isEmpty()) || (valorProvincia != null && !valorProvincia.isEmpty()) ||
            	(valorLocalidad != null && !valorLocalidad.isEmpty()) || (valorDni != null && !valorDni.isEmpty())) {

            	consulta += " WHERE ";

                if(valorDni != null && !valorDni.isEmpty()) {
                    consulta += " Dni = '" + valorDni + "'";
                    cont = cont + 1;
                }
                if(valorNombre != null && !valorNombre.isEmpty()) {
                    if (cont > 0) consulta += " AND ";
                    consulta += " Nombre LIKE '%" + valorNombre + "%'";
                    cont = cont + 1;
                }
                if(valorApellido != null && !valorApellido.isEmpty()) {
                    if (cont > 0) consulta += " AND ";
                    consulta += " Apellido LIKE '%" + valorApellido + "%'";
                    cont = cont + 1;
                }
                if(valorGenero != null && !valorGenero.isEmpty()) {
                    if (cont > 0) consulta += " AND ";
                    consulta += " Sexo = '" + valorGenero + "'";
                    cont = cont + 1;
                }
                if(valorNacionalidad != null && !valorNacionalidad.isEmpty()) {
                    if (cont > 0) consulta += " AND ";
                    consulta += " Nacionalidad = '" + valorNacionalidad + "'";
                    cont = cont + 1;
                }
                if((valorProvincia != null && !valorProvincia.isEmpty()) && (valorLocalidad != null && !valorLocalidad.isEmpty())) {
                    if (cont > 0) consulta += " AND ";
                    consulta += " Provincia = '" + valorProvincia + "' AND Localidad = '" + valorLocalidad + "'";
                    cont = cont + 1;
                }
                if(valorTelefono != null && !valorTelefono.isEmpty()) {
                    if (cont > 0) consulta += " AND ";
                    consulta += " Telefono LIKE '%" + valorTelefono + "%'";
                    cont = cont + 1;
                }

                consulta += " AND Estado = '1'";
                condicionesFiltrado = true; 
            }

            if(!condicionesFiltrado) {
            	consulta += " WHERE Estado = '1'";
            }
        }
        
        NPacienteImpl pacienteImpl = new NPacienteImpl();
        ArrayList<Paciente> medicos = pacienteImpl.filtro(consulta);
        
        request.setAttribute("listaFiltro", medicos);
        
        RequestDispatcher rd = request.getRequestDispatcher("PacienteServlet?Param=1");
        rd.forward(request, response);
    }	
}

