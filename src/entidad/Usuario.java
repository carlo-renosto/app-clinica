package entidad;

public class Usuario {
	private int id;
	private int dni;
	private String nombre;
	private String contra;
	private boolean esAdmin;
	
	public Usuario() {}
	
	public Usuario(int id, int dni, String nombre, String contra, boolean esAdmin) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.contra = contra;
		this.esAdmin = esAdmin;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public int getDni( ) {
		return dni;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setContra(String contra) {
		this.contra = contra;
	}
	
	public String getContra() {
		return contra;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	
	public boolean isEsAdmin() {
		return esAdmin;
	}

}
