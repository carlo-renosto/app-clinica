package entidad;

public class Paciente {
	private int dni;
	private String nombre;
	private String apellido;
	private String sexo;
	private String nacionalidad;
	private String fecha_nac; 
	private String direccion;
	private String localidad;
	private String correo_electronico;
	private String telefono;
	private String provincia;
	int estado;
	
	public Paciente() {}
	
	public Paciente(int dni, String nombre, String apellido, String sexo, String nacionalidad, String fecha_nac, String direccion, String localidad, String correo_electronico, String telefono, String provincia) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fecha_nac = fecha_nac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.correo_electronico = correo_electronico;
		this.telefono = telefono;
		this.provincia = provincia;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCorreo_Electronico() {
		return correo_electronico;
	}

	public void setCorreo_Electronico(String correo_Electronico) {
		this.correo_electronico = correo_Electronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
}
