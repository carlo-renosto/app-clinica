package negocioImpl;

import java.util.ArrayList;

import daoImpl.MedicoImpl;
import entidad.Medico;
import negocio.NMedico;

public class NMedicoImpl implements NMedico {	
	private MedicoImpl medicoImpl = new MedicoImpl();
	
	public ArrayList<Medico> getMedico() {
		ArrayList<Medico> medicos = medicoImpl.getMedico();
		return medicos;
	}
	
	public ArrayList<Medico> getDni() {
		ArrayList<Medico> medicos = medicoImpl.getDni();
		return medicos;
	}
		
	public ArrayList<Medico> filtro(String consulta) {
		ArrayList<Medico> medicos = medicoImpl.filtro(consulta);
		return medicos;
	}
	
	public int agregarMedico(Medico medico) {	
		return medicoImpl.agregarMedico(medico);
	}
	
	public int editarMedico(Medico medico) {
		return medicoImpl.editarMedico(medico);
	}
	
	public int eliminarMedico(int dni) {
		return medicoImpl.eliminarMedico(dni);
	}
	
	public ArrayList<Medico> getTodoDni(int dni) {
		ArrayList<Medico> medicos = medicoImpl.getTodoDni(dni);
		return medicos;
	}
	
	public Medico getMedicoSelect(int dni) {
		Medico medico = medicoImpl.getMedicoSelect(dni);
		return medico;
	}
	
	public int existeDni(int dni) {
		return medicoImpl.existeDni(dni);
	}
}
