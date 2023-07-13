package dao;

import java.util.ArrayList;
import entidad.Medico;

public interface IMedico {
	public ArrayList<Medico> getMedico();
	public ArrayList<Medico> getDni();
	public ArrayList<Medico> filtro(String consulta);
	public int agregarMedico(Medico medico);
	public int editarMedico(Medico medico);
	public int eliminarMedico(int dni);
	public ArrayList<Medico> getTodoDni(int dni);
	public Medico getMedicoSelect(int dni);
	public int existeDni(int dni);
}
