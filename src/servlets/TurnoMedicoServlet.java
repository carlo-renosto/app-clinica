package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Turno;
import negocioImpl.NTurnoImpl;

@WebServlet("/TurnoMedicoServlet")
public class TurnoMedicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TurnoMedicoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");
		
		if(paramValue != null && paramValue.equals("1")) {
			if(request.getAttribute("listaFiltro") == null) {
				String dniMed = request.getParameter("dniUsuario");
				
				NTurnoImpl turnoImpl = new NTurnoImpl();
				ArrayList<Turno> turnos = turnoImpl.getTurnosMedico(Integer.parseInt(dniMed));
				ArrayList<Integer> dniPacientes = turnoImpl.getDniPacientes(Integer.parseInt(dniMed));
				
				request.setAttribute("listaFiltro", turnos);
				request.setAttribute("listaPacientes", dniPacientes);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/MenuTurnoMedico.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
