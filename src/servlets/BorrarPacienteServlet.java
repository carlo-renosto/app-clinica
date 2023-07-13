package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.NPacienteImpl;

@WebServlet("/BorrarPacienteServlet")
public class BorrarPacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BorrarPacienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("ddlDni");
	    
	    if(dni != null && !dni.isEmpty()) {
	        NPacienteImpl pacienteImpl = new NPacienteImpl();
	        pacienteImpl.eliminarPaciente(Integer.parseInt(dni));
	    }
	    
        RequestDispatcher rd = request.getRequestDispatcher("PacienteServlet?Param=4");
        rd.forward(request, response);
	}
}
