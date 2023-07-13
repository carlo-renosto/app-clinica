package negocioImpl;

import java.util.ArrayList;

import daoImpl.EspecialidadImpl;
import entidad.Especialidad;
import negocio.NEspecialidad;

public class NEspecialidadImpl implements NEspecialidad {
	private EspecialidadImpl esp = new EspecialidadImpl();
	
	public ArrayList<Especialidad> getEspecialidades() {
    	ArrayList<Especialidad> especialidades = esp.getEspecialidades();
    	
    	return especialidades;
	}
}
