package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import negocioImpl.NEspecialidadImpl;
import negocioImpl.NMedicoImpl;
import negocioImpl.NPacienteImpl;
import negocioImpl.NTurnoImpl;

@WebServlet("/TurnoServlet")
public class TurnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");
		
		NEspecialidadImpl especialidadImpl = new NEspecialidadImpl();
    	ArrayList<Especialidad> listaEsp = especialidadImpl.getEspecialidades();
    	request.setAttribute("listaEsp", listaEsp);
    	
    	NMedicoImpl medicoImpl = new NMedicoImpl();
        ArrayList<Medico> listaDni = medicoImpl.getDni();
        request.setAttribute("listaDni", listaDni);
        
        NPacienteImpl pacienteImpl = new NPacienteImpl();
        ArrayList<Paciente> listaDniP = pacienteImpl.getDni();
        request.setAttribute("listaDniP", listaDniP);
    	
		if(request.getAttribute("listaFiltro") == null) {
			NTurnoImpl turnoImpl = new NTurnoImpl();
    		ArrayList<Turno> listaP = turnoImpl.getTurno();
         	ArrayList<Turno> listaFiltro = new ArrayList<Turno>();

         	for(Turno turno : listaP) {
         		Medico medico = medicoImpl.getMedicoSelect(turno.getMedico().getDni());
         		turno.setMedico(medico);
         		Paciente paciente = pacienteImpl.getPacienteSelect(turno.getPaciente().getDni());
         		turno.setPaciente(paciente);
         	         		
         		listaFiltro.add(turno);
         	}
         	request.setAttribute("listaFiltro", listaFiltro);
    	}
		
		if(paramValue != null && paramValue.equals("1")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/MenuTurno.jsp");
            rd.forward(request, response);
            return;
        }
		if(paramValue != null && paramValue.equals("2")) {
            RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/AnadirTurno.jsp");
            rd.forward(request, response);
            return;
        }
		if(paramValue != null && paramValue.equals("3")) {
			String dni = request.getParameter("ddlMedico");
			
			if(dni != null && !dni.isEmpty()) {
				Medico medico = medicoImpl.getMedicoSelect(Integer.parseInt(dni));
				
				NTurnoImpl turnoImpl = new NTurnoImpl();
				ArrayList<Integer> horas = turnoImpl.getHorasMedico(Integer.parseInt(dni));
				ArrayList<Integer> horasDisponibles = new ArrayList<Integer>();
				
				for(int i=medico.getHora_inicio_atencion();i<medico.getHora_fin_atencion();i++) {
					int cont = 0;
					ListIterator<Integer> it = horas.listIterator();
					while(it.hasNext()) {
						Integer hora = it.next();
						if(i != hora) {
							cont = cont + 1;
						}
					}
					if(cont == horas.size()) horasDisponibles.add(i);
				}
				
				request.setAttribute("medico", medico);
				request.setAttribute("horasMedico", horasDisponibles);
			}
            RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/AnadirTurno.jsp");
            rd.forward(request, response);
            return;
        }
		
		if(paramValue != null && paramValue.equals("4")) {
			String dniM = request.getParameter("ddlMedicoAux");
			String especialidad = request.getParameter("txtEspecialidad");
			String dniP = request.getParameter("ddlPaciente");
			String dia = request.getParameter("ddlDia");
			String hora = request.getParameter("ddlHora");
			
			
			Turno turno = new Turno(especialidad, Integer.parseInt(dniM), Integer.parseInt(dniP), dia, hora, "", "Ocupado");
			
			NTurnoImpl turnoImpl = new NTurnoImpl();
			turnoImpl.agregarTurno(turno);

			RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/AnadirTurno.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
