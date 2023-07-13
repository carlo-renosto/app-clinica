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

@WebServlet("/TurnoFiltroServlet")
public class TurnoFiltroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnoFiltroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String consulta = "";
		
		if(request.getParameter("btnFiltrar") != null) {
			String valorEspecialidad = request.getParameter("ddlEspecialidad");
			String valorDniMedico = request.getParameter("ddlMedico");
			String valorDniPaciente = request.getParameter("ddlPaciente");
			String valorDiaTurno = request.getParameter("ddlDia");
			String valorHoraTurno = request.getParameter("txtHora");
			String valorEstado = request.getParameter("ddlEstado");
			
			int cont = 0;
			
			if(valorEspecialidad != null && !valorEspecialidad.isEmpty() || valorDniMedico != null && !valorDniMedico.isEmpty() || valorDniPaciente != null && !valorDniPaciente.isEmpty() 
				|| valorDiaTurno != null && !valorDiaTurno.isEmpty() || valorHoraTurno != null && !valorHoraTurno.isEmpty() || valorEstado != null && !valorEstado.isEmpty()) {
				
    			consulta += " WHERE ";

    			if(valorEspecialidad != null && !valorEspecialidad.isEmpty()) {
    				consulta += " Especialidad LIKE '%" + valorEspecialidad + "%'";
    				cont = cont + 1;
    			}
    			if(valorDniMedico != null && !valorDniMedico.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Dni_Medico = '" + valorDniMedico + "'";
    				cont = cont + 1;
    			}
    			if(valorDniPaciente != null && !valorDniPaciente.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Dni_Paciente = '" + valorDniPaciente + "'";
    				cont = cont + 1;
    			}
    			if(valorDiaTurno != null && !valorDiaTurno.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Dia_Turno LIKE '%" + valorDiaTurno + "%'";
    				cont = cont + 1;
    			}
    			if(valorHoraTurno != null && !valorHoraTurno.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Hora_Turno = '" + valorHoraTurno + "'";
    				cont = cont + 1;
    			}
    			if(valorEstado != null && !valorEstado.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Estado LIKE '%" + valorEstado + "%'";
    				cont = cont + 1;
    			}
			}
			
			NTurnoImpl turnoImpl = new NTurnoImpl();
			ArrayList<Turno> turnos = turnoImpl.filtro(consulta);
			ArrayList<Turno> listaFiltro = new ArrayList<Turno>();
			
			for(Turno turno : turnos) {
				listaFiltro.add(turno);
			}
			
			request.setAttribute("listaFiltro", listaFiltro);
			
        	RequestDispatcher rd = request.getRequestDispatcher("TurnoServlet?Param=1");
        	rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarUser") != null) {
			String dniUsuario = request.getParameter("dniUsuario");
			
			String valorDniPaciente = request.getParameter("ddlPaciente");
			String valorDiaTurno = request.getParameter("ddlDia");
			String valorHoraTurno = request.getParameter("txtHora");
			String valorEstado = request.getParameter("ddlEstado");
			
			int cont = 0;
			
			if(valorDniPaciente != null && !valorDniPaciente.isEmpty() || valorDiaTurno != null && !valorDiaTurno.isEmpty()  || valorHoraTurno != null && !valorHoraTurno.isEmpty() 
				|| valorEstado != null && !valorEstado.isEmpty()) {
				
    			consulta += " WHERE ";

    			if(valorDniPaciente != null && !valorDniPaciente.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Dni_Paciente = '" + valorDniPaciente + "'";
    				cont = cont + 1;
    			}
    			if(valorDiaTurno != null && !valorDiaTurno.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Dia_Turno LIKE '%" + valorDiaTurno + "%'";
    				cont = cont + 1;
    			}
    			if(valorHoraTurno != null && !valorHoraTurno.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Hora_Turno = '" + valorHoraTurno + "'";
    				cont = cont + 1;
    			}
    			if(valorEstado != null && !valorEstado.isEmpty()) {
    				if(cont > 0) consulta += " AND ";
    				consulta += " Estado LIKE '%" + valorEstado + "%'";
    				cont = cont + 1;
    			}
    			
    			consulta += " AND Dni_Medico = " + dniUsuario;
    			
    			NTurnoImpl turnoImpl = new NTurnoImpl();
    			ArrayList<Turno> listaTurno = turnoImpl.filtroUser(consulta, Integer.parseInt(dniUsuario));
    			ArrayList<Integer> dniPacientes = turnoImpl.getDniPacientes(Integer.parseInt(dniUsuario));
    						
    			request.setAttribute("listaFiltro", listaTurno);
    			request.setAttribute("listaPacientes", dniPacientes);
    			
    			RequestDispatcher rd = request.getRequestDispatcher("MenuTurno/MenuTurnoMedico.jsp");
            	rd.forward(request, response);
            	return;
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("TurnoMedicoServlet?Param=1&dniUsuario=" + dniUsuario);
            	rd.forward(request, response);
            	return;
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
