package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.NTurnoImpl;

@WebServlet("/MarcarObservacionServlet")
public class MarcarObservacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MarcarObservacionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");
		String id = request.getParameter("id");
		String dniUsuario = request.getParameter("dniUsuario");
		
		if(paramValue != null && paramValue.equals("1")) {
			request.setAttribute("id", id);
			request.setAttribute("dniUsuario", dniUsuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/MarcarObservacion.jsp");
            rd.forward(request, response);
            return;
		}
		
		if(paramValue != null && paramValue.equals("2")) {
			String observacion = request.getParameter("txtObservacion");
			
			NTurnoImpl turnoImpl = new NTurnoImpl();
			turnoImpl.marcarObservacion(observacion, Integer.parseInt(id), Integer.parseInt(dniUsuario));
			
			RequestDispatcher rd = request.getRequestDispatcher("TurnoMedicoServlet?Param=1&dniUsuario=" + dniUsuario);
            rd.forward(request, response);
            return;
		}
		
		if(paramValue != null && paramValue.equals("3")) {
			RequestDispatcher rd = request.getRequestDispatcher("TurnoMedicoServlet?Param=1&dniUsuario=" + dniUsuario);
            rd.forward(request, response);
            return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
