package dao;

import java.util.ArrayList;

import entidad.Turno;

public interface ITurno {
	public ArrayList<Turno> getTurno();
	public Turno getTurnoId(int id);
	public ArrayList<Turno> filtro(String consulta);
	public ArrayList<Turno> filtroUser(String consulta, int dni);
	public int agregarTurno(Turno turno);
	public ArrayList<Turno> getTurnosMedico(int pacienteDni);
	public ArrayList<Integer> getHorasMedico(int dni);
	public ArrayList<Integer> getDniPacientes(int dni);
	public void marcarPresente(int id);
	public void marcarAusente(int id);
	public void marcarObservacion(String observacion, int id, int dni);
}
