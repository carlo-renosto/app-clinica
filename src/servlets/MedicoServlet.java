package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.Medico;
import negocioImpl.NEspecialidadImpl;
import negocioImpl.NMedicoImpl;

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MedicoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramValue = request.getParameter("Param");

        NMedicoImpl medicoImpl = new NMedicoImpl();
        ArrayList<Medico> listaDni = medicoImpl.getDni();
        
        request.setAttribute("listaDni", listaDni);
        
    	NEspecialidadImpl esp = new NEspecialidadImpl();
    	ArrayList<Especialidad> especialidades = esp.getEspecialidades();
    	
    	request.setAttribute("listaEsp", especialidades);
    	
    	if(request.getAttribute("listaFiltro") == null) {
    		ArrayList<Medico> listaP = medicoImpl.getMedico();
         	ArrayList<Medico> listaFiltro = new ArrayList<Medico>();

         	for(Medico medico : listaP) {
         		if (medico.getEstado() == 1) {
         	        listaFiltro.add(medico);
         	    }
         	}
         	request.setAttribute("listaFiltro", listaFiltro);
    	}
        
        if (paramValue != null && paramValue.equals("1")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuMedico/MenuMedico.jsp");
            rd.forward(request, response);
            return;
        }
        if (paramValue != null && paramValue.equals("2")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuMedico/AnadirMedico.jsp");
            rd.forward(request, response);
            return;
        }
        if (paramValue != null && paramValue.equals("3")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuMedico/EditarMedico.jsp");
            rd.forward(request, response);
            return;
        }
        if (paramValue != null && paramValue.equals("4")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuMedico/BorrarMedico.jsp");
            rd.forward(request, response);
            return;
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
