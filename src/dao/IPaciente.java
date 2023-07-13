package dao;

import java.util.ArrayList;
import entidad.Paciente;

public interface IPaciente {
	public ArrayList<Paciente> getPaciente();
	public ArrayList<Paciente> getDni();
	public ArrayList<Paciente> filtro(String consulta);
	public int agregarPaciente(Paciente paciente);
	public int editarPaciente(Paciente paciente);
	public int eliminarPaciente(int dni);
	public ArrayList<Paciente> getTodoDni(int dni);
	public Paciente getPacienteSelect(int dni);
	public int existeDni(int dni);
}
