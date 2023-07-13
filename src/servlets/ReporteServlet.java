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
import entidad.Turno;
import negocioImpl.NMedicoImpl;
import negocioImpl.NTurnoImpl;

import java.util.List;

@WebServlet("/ReporteServlet")
public class ReporteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ReporteServlet() {
        super();
    }
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String paramValue = request.getParameter("Param");
    	
    	NMedicoImpl medicoImpl = new NMedicoImpl();
        ArrayList<Medico> listaDni = medicoImpl.getDni();
        
        request.setAttribute("listaDni", listaDni);
    	
    	if(paramValue != null && paramValue.equals("1")) {    		
            RequestDispatcher rd = request.getRequestDispatcher("MenuReportes/Reportes.jsp");
            rd.forward(request, response);
            return;
        }
    	
        String dniMedico = request.getParameter("dniMedico");
        String horarioDesde = request.getParameter("horarioDesde");
        String horarioHasta = request.getParameter("horarioHasta");
        
        List<Turno> turnos = obtenerTurnos(dniMedico, horarioDesde, horarioHasta);
        
        request.setAttribute("turnos", turnos);
        
        request.getRequestDispatcher("MenuReportes/Reportes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    private List<Turno> obtenerTurnos(String dniMedico, String horarioDesde, String horarioHasta) {
    	List<Turno> turnos = new ArrayList<>();
        
        NTurnoImpl turnoImpl = new NTurnoImpl();
        String consulta = "";
        
        if(dniMedico != null && !dniMedico.isEmpty() && horarioDesde != null && !horarioDesde.isEmpty() && horarioHasta != null && !horarioHasta.isEmpty()) {
            consulta += " WHERE Dni_Medico = '" + dniMedico + "' AND Hora_Turno >= '" + horarioDesde + "' AND Hora_Turno <= '" + horarioHasta + "'";
            
            turnos = turnoImpl.filtro(consulta);
        }
        
        return turnos;
    }
}

