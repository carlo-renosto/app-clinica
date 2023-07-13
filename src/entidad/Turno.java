package entidad;

public class Turno {
	private int id;
	private Especialidad especialidad;
	private Medico medico;
	private Paciente paciente;
	private String diaTurno;
	private String horaTurno;
	private String observacion;
	private String estado;
	
	public Turno() {}
	
	public Turno(String especialidad, int dniMedico, int dniPaciente, String diaTurno, String horaTurno, String observacion, String estado) {
		this.especialidad = new Especialidad();
		this.especialidad.setNombre(especialidad);
		this.medico = new Medico();
		this.medico.setDni(dniMedico);
		this.paciente = new Paciente();
		this.paciente.setDni(dniPaciente);
		this.diaTurno = diaTurno;
		this.horaTurno = horaTurno;
		this.observacion = observacion;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public String getEspecialidadNombre() {
		return especialidad.getNombre();
	}
	public void setEspecialidadNombre(String nombre) {
		this.especialidad.setNombre(nombre);
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public void setDniMedico(int dniMedico) {
		this.medico.setDni(dniMedico);
	}
	public void setNombreApellidoMedico(String nombre, String apellido) {
		this.medico.setNombre(nombre);
		this.medico.setApellido(apellido);
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public void setDniPaciente(int dniPaciente) {
		this.paciente.setDni(dniPaciente);;
	}
	public void setNombreApellidoPaciente(String nombre, String apellido) {
		this.paciente.setNombre(nombre);
		this.paciente.setApellido(apellido);
	}
	public void setTelefonoPaciente(String telefono) {
		this.paciente.setTelefono(telefono);
	}
	public String getDiaTurno() {
		return diaTurno;
	}
	public void setDiaTurno(String diaTurno) {
		this.diaTurno = diaTurno;
	}
	public String getHoraTurno() {
		return horaTurno;
	}
	public void setHoraTurno(String horaTurno) {
		this.horaTurno = horaTurno;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
