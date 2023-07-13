package daoImpl;

import dao.IUsuario;
import entidad.Usuario;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class UsuarioImpl implements IUsuario {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "ClinicaMedica";
	
	public ArrayList<Usuario> getUsuarios() {
		String query = "SELECT * FROM Usuario WHERE Estado = 1";
		Connection cn = null;
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Usuario user = new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
				usuarios.add(user);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public ArrayList<Integer> getDni() {
		String query = "SELECT Dni FROM Usuario WHERE Estado = 1";
		Connection cn = null;
		
		ArrayList<Integer> documentos = new ArrayList<Integer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int dni = rs.getInt("Dni");
				documentos.add(dni);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return documentos;
	}

	public int agregarUsuario(Usuario usuario) {
		int filas = 0;
		Connection cn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT MAX(Id) FROM Usuario";
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				usuario.setId(rs.getInt("Max(Id)") + 1);
			}

			PreparedStatement stmt = (PreparedStatement) cn.prepareStatement("INSERT INTO Usuario(Id, Dni, Nombre, Contra, EsAdmin) VALUES(?, ?, ?, ?, ?)");
			stmt.setInt(1, usuario.getId());
			stmt.setInt(2, usuario.getDni());
			stmt.setString(3, usuario.getNombre());
			stmt.setString(4, usuario.getContra());
			stmt.setBoolean(5, usuario.isEsAdmin());
			filas = stmt.executeUpdate();
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	public int editarUsuario(Usuario usuario) {
		int filas = 0;
		Connection cn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement stmt =  (PreparedStatement) cn.prepareStatement("UPDATE Usuario SET Nombre = ?, Contra = ?, EsAdmin = ? WHERE Dni = ?");
			
			stmt.setString(1, usuario.getNombre());
			stmt.setString(2, usuario.getContra());
			stmt.setBoolean(3, usuario.isEsAdmin());
			stmt.setInt(4, usuario.getDni());
			
			filas = stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	
	public int eliminarUsuario(int dni) {
		int filas = 0;
		Connection cn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement stmt =  (PreparedStatement) cn.prepareStatement("UPDATE Usuario SET Estado = 0 WHERE Dni = ?");
			
		    stmt.setInt(1, dni);
		    filas = stmt.executeUpdate();
		    cn.close();
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		return filas;
	}
	
	public Usuario getUsuarioSelect(int dni) {
		String query = "SELECT * FROM Usuario WHERE Dni = " + dni + " AND Estado = 1";
		Connection cn = null;
		
		Usuario usuario = new Usuario();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				usuario.setId(rs.getInt("Id"));
				usuario.setDni(rs.getInt("Dni"));
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setContra(rs.getString("Contra"));
				usuario.setEsAdmin(rs.getBoolean("EsAdmin"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public boolean existeUsuario(String usuario) {
		Connection cn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT Id FROM Usuario WHERE Nombre = '" + usuario + "'";
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()) {
				return true;
			}
			
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean existeDni(int dni) {
		Connection cn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT Id FROM Usuario WHERE dni = " + dni;
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()) {
				return true;
			}
			
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
