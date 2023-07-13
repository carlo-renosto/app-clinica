package servlets;

import entidad.Turno;
import negocioImpl.NTurnoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MarcarAusenteServlet")
public class MarcarAusenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int turnoId = Integer.parseInt(request.getParameter("id"));
        int medicoDni = Integer.parseInt(request.getParameter("dniUsuario"));

        NTurnoImpl turnoImpl = new NTurnoImpl();
        Turno turno = turnoImpl.getTurnoId(turnoId);

        if(turno != null) {
        	turnoImpl.marcarAusente(turnoId);
        } 
        
        RequestDispatcher rd = request.getRequestDispatcher("TurnoMedicoServlet?Param=1&dniUsuario=" + medicoDni);
	    rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

