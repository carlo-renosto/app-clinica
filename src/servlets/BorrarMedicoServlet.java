package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.NMedicoImpl;

@WebServlet("/BorrarMedicoServlet")
public class BorrarMedicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BorrarMedicoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String dni = request.getParameter("ddlDni");
	    
	    if(dni != null && !dni.isEmpty()) {
	        NMedicoImpl medicoImpl = new NMedicoImpl();
	        medicoImpl.eliminarMedico(Integer.parseInt(dni));
	    }
	    
        RequestDispatcher rd = request.getRequestDispatcher("MedicoServlet?Param=4");
        rd.forward(request, response);
	}
}