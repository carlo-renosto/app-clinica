package negocio;

import java.util.ArrayList;

import entidad.Usuario;

public interface NUsuario {
	public ArrayList<Usuario> getUsuarios();
	public ArrayList<Integer> getDni();
	public int agregarUsuario(Usuario usuario);
	public int editarUsuario(Usuario usuario);
	public int eliminarUsuario(int dni);
	public Usuario getUsuarioSelect(int dni);
	public boolean existeUsuario(String usuario);
	public boolean existeDni(int dni);
}
