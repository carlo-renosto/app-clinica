package negocioImpl;

import java.util.ArrayList;

import daoImpl.TurnoImpl;
import entidad.Turno;
import negocio.NTurno;

public class NTurnoImpl implements NTurno {
	private TurnoImpl turnoImpl = new TurnoImpl();
	
	public ArrayList<Turno> getTurno() {
		ArrayList<Turno> turnos = turnoImpl.getTurno();
		return turnos;
	}
	
	public Turno getTurnoId(int id) {
		Turno turno = turnoImpl.getTurnoId(id);
		return turno;
	}
	
	public ArrayList<Turno> filtro(String consulta) {
		ArrayList<Turno> turnos = turnoImpl.filtro(consulta);
		return turnos;
	}
	
	public ArrayList<Turno> filtroUser(String consulta, int dni) {
		ArrayList<Turno> turnos = turnoImpl.filtroUser(consulta, dni);
		return turnos;
	}
	
	public int agregarTurno(Turno turno) {
		return turnoImpl.agregarTurno(turno);
	}
	
	public ArrayList<Turno> getTurnosMedico(int pacienteDni) {
		ArrayList<Turno> turnos = turnoImpl.getTurnosMedico(pacienteDni);
		return turnos;
	}
	
	public ArrayList<Integer> getHorasMedico(int dni) {
		ArrayList<Integer> horas = turnoImpl.getHorasMedico(dni);
		return horas;
	}
	
	public ArrayList<Integer> getDniPacientes(int dni) {
		ArrayList<Integer> documentos = turnoImpl.getDniPacientes(dni);
		return documentos;
	}
	
	public void marcarPresente(int id) {
		turnoImpl.marcarPresente(id);
	}
	
	public void marcarAusente(int id) {
		turnoImpl.marcarAusente(id);
	}
	
	public void marcarObservacion(String observacion, int id, int dni) {
		turnoImpl.marcarObservacion(observacion, id, dni);
	}
}
