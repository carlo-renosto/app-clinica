package negocioImpl;

import java.util.ArrayList;

import daoImpl.PacienteImpl;
import entidad.Paciente;
import negocio.NPaciente;

public class NPacienteImpl implements NPaciente {
	private PacienteImpl pacienteImpl = new PacienteImpl(); 
	
	public ArrayList<Paciente> getPaciente() {
		ArrayList<Paciente> pacientes = pacienteImpl.getPaciente();
		return pacientes;
	}
	
	public ArrayList<Paciente> getDni() {
		ArrayList<Paciente> pacientes = pacienteImpl.getDni();
		return pacientes;
	}
	
	public ArrayList<Paciente> filtro(String consulta) {
		ArrayList<Paciente> pacientes = pacienteImpl.filtro(consulta);
		return pacientes;
	}
	
	public int agregarPaciente(Paciente paciente) {
		return pacienteImpl.agregarPaciente(paciente);
	}
	
	public int editarPaciente(Paciente paciente) {
		return pacienteImpl.editarPaciente(paciente);
	}
	
	public int eliminarPaciente(int dni) {
		return pacienteImpl.eliminarPaciente(dni);
	}
	
	public ArrayList<Paciente> getTodoDni(int dni) {
		ArrayList<Paciente> pacientes = pacienteImpl.getTodoDni(dni);
		return pacientes;
	}
	
	public Paciente getPacienteSelect(int dni) {
		Paciente paciente = pacienteImpl.getPacienteSelect(dni);
		return paciente;
	}
	
	public int existeDni(int dni) {
		return pacienteImpl.existeDni(dni);
	}
}
