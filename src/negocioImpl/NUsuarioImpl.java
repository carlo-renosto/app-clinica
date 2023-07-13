package negocioImpl;

import java.util.ArrayList;

import daoImpl.UsuarioImpl;
import entidad.Usuario;
import negocio.NUsuario;

public class NUsuarioImpl implements NUsuario {
	private UsuarioImpl usuarioImpl = new UsuarioImpl();
	
	public ArrayList<Usuario> getUsuarios() {
		ArrayList<Usuario> usuarios = usuarioImpl.getUsuarios();
		return usuarios;
	}
	
	public ArrayList<Integer> getDni() {
		ArrayList<Integer> documentos = usuarioImpl.getDni();
		return documentos;
	}
	
	public int agregarUsuario(Usuario usuario) {
		return usuarioImpl.agregarUsuario(usuario);
	}
	
	public int editarUsuario(Usuario usuario) {
		return usuarioImpl.editarUsuario(usuario);
	}
	
	public int eliminarUsuario(int dni) {
		return usuarioImpl.eliminarUsuario(dni);
	}
	
	public Usuario getUsuarioSelect(int dni) {
		Usuario usuario = usuarioImpl.getUsuarioSelect(dni);
		return usuario;
	}
	
	public boolean existeUsuario(String usuario) {
		return usuarioImpl.existeUsuario(usuario);
	}
	
	public boolean existeDni(int dni) {
		return usuarioImpl.existeDni(dni);
	}
}
