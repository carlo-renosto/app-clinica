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

@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PacienteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramValue = request.getParameter("Param");
        
        NPacienteImpl pacienteImpl = new NPacienteImpl();
        ArrayList<Paciente> listaDni = pacienteImpl.getDni();
        
        request.setAttribute("listaDni", listaDni);
        
        if(request.getAttribute("listaFiltro") == null) {
            ArrayList<Paciente> listaP = pacienteImpl.getPaciente();
        	ArrayList<Paciente> listaFiltro = new ArrayList<Paciente>();

        	for(Paciente paciente : listaP) {
        	    if (paciente.getEstado() == 1) {
        	        listaFiltro.add(paciente);
        	    }
        	}
        	request.setAttribute("listaFiltro", listaFiltro);
        }
        
        if (paramValue != null && paramValue.equals("1")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuPaciente/MenuPaciente.jsp");
            rd.forward(request, response);
            return;
        }
        
        if (paramValue != null && paramValue.equals("2")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuPaciente/AnadirPaciente.jsp");
            rd.forward(request, response);
            return;
        }
        
        
        if (paramValue != null && paramValue.equals("3")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuPaciente/EditarPaciente.jsp");
            rd.forward(request, response);
            return;
        }
        
        if (paramValue != null && paramValue.equals("4")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuPaciente/BorrarPaciente.jsp");
            rd.forward(request, response);
            return;
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}