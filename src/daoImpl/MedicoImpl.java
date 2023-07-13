package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.Statement;

import dao.IMedico;
import entidad.Especialidad;
import entidad.Medico;

public class MedicoImpl implements IMedico {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "ClinicaMedica";
	
	public ArrayList<Medico> getMedico() {
		String query = "SELECT * FROM Medico where Estado = '1'";
		Connection cn = null;
		
		ArrayList<Medico> Lista = new ArrayList<Medico>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Medico Med = new Medico();
				Med.setDni(rs.getInt("Dni"));
				Med.setNombre(rs.getString("Nombre"));
				Med.setApellido(rs.getString("Apellido"));
				Med.setSexo(rs.getString("Sexo"));
				Med.setNacionalidad(rs.getString("Nacionalidad"));
				Med.setFecha_nac(rs.getString("Fecha_Nac"));
				Med.setDireccion(rs.getString("Direccion"));
				Med.setLocalidad(rs.getString("Localidad"));
				Med.setCorreo_Electronico(rs.getString("Correo_Electronico"));
				Med.setTelefono(rs.getString("Telefono"));
				Med.setProvincia(rs.getString("Provincia"));
				Med.setEspecialidad(new Especialidad());
				Med.setEspecialidadNombre(rs.getString("Especialidad"));
				Med.setDia_atencion(rs.getString("Dia_Atencion"));
				Med.setHora_inicio_atencion(rs.getInt("Hora_Inicio_Atencion"));
				Med.setHora_fin_atencion(rs.getInt("Hora_Fin_Atencion"));
				Med.setEstado(rs.getInt("Estado"));
				Lista.add(Med);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public ArrayList<Medico> getDni() {
		String query = "SELECT Dni FROM Medico WHERE Estado = '1'";
		Connection cn = null;
		
		ArrayList<Medico> Lista = new ArrayList<Medico>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
		
				Medico Med = new Medico();
				Med.setDni(rs.getInt("Dni"));
				Lista.add(Med);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public ArrayList<Medico> filtro(String consulta) {
		String query = "SELECT * FROM Medico" + consulta;
		Connection cn = null;
		
		ArrayList<Medico> Lista = new ArrayList<Medico>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Medico Med = new Medico();
				Med.setDni(rs.getInt("Dni"));
				Med.setNombre(rs.getString("Nombre"));
				Med.setApellido(rs.getString("Apellido"));
				Med.setSexo(rs.getString("Sexo"));
				Med.setNacionalidad(rs.getString("Nacionalidad"));
				Med.setFecha_nac(rs.getString("Fecha_Nac"));
				Med.setDireccion(rs.getString("Direccion"));
				Med.setLocalidad(rs.getString("Localidad"));
				Med.setCorreo_Electronico(rs.getString("Correo_Electronico"));
				Med.setTelefono(rs.getString("Telefono"));
				Med.setProvincia(rs.getString("Provincia"));
				Med.setEspecialidad(new Especialidad());
				Med.setEspecialidadNombre(rs.getString("Especialidad"));
				Med.setDia_atencion(rs.getString("Dia_Atencion"));
				Med.setHora_inicio_atencion(rs.getInt("Hora_Inicio_Atencion"));
				Med.setHora_fin_atencion(rs.getInt("Hora_Fin_Atencion"));
				Med.setEstado(rs.getInt("Estado"));
				Lista.add(Med);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public int agregarMedico(Medico medico) {
	    int filas = 0;
	    Connection cn = null;
	    
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        cn = DriverManager.getConnection(host + dbName, user, pass);
	        PreparedStatement stmt = (PreparedStatement) cn.prepareStatement("INSERT INTO Medico (Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        
	        stmt.setInt(1, medico.getDni());
	        stmt.setString(2, medico.getNombre());
	        stmt.setString(3, medico.getApellido());
	        stmt.setString(4, medico.getSexo());
	        stmt.setString(5, medico.getNacionalidad());
	        stmt.setString(6, medico.getFecha_nac());
	        stmt.setString(7, medico.getDireccion());
	        stmt.setString(8, medico.getLocalidad());
	        stmt.setString(9, medico.getCorreo_Electronico());
	        stmt.setString(10, medico.getTelefono());
	        stmt.setString(11, medico.getProvincia());
	        stmt.setString(12, medico.getEspecialidad().getNombre());
	        stmt.setString(13, medico.getDia_atencion());
	        stmt.setInt(14, medico.getHora_inicio_atencion());
	        stmt.setInt(15, medico.getHora_fin_atencion());

	        filas = stmt.executeUpdate();
			cn.close();
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return filas;
	}
	
	public int editarMedico(Medico medico) {
		int filas = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		
		    PreparedStatement stmt =  (PreparedStatement) cn.prepareStatement("UPDATE Medico SET Nombre = ?, Apellido = ?, Sexo = ?, Nacionalidad = ?, Fecha_nac = ?, Direccion = ?, Localidad = ?, Correo_Electronico = ?, Telefono = ?, Provincia = ?, Especialidad = ?, Dia_Atencion = ?, Hora_Inicio_Atencion = ?, Hora_Fin_Atencion = ?  WHERE Dni = ?"); 
		    
	        stmt.setString(1, medico.getNombre());
	        stmt.setString(2, medico.getApellido());
	        stmt.setString(3, medico.getSexo());
	        stmt.setString(4, medico.getNacionalidad());
	        stmt.setString(5, medico.getFecha_nac());
	        stmt.setString(6, medico.getDireccion());
	        stmt.setString(7, medico.getLocalidad());
	        stmt.setString(8, medico.getCorreo_Electronico());
	        stmt.setString(9, medico.getTelefono());
	        stmt.setString(10, medico.getProvincia());
	        stmt.setString(11, medico.getEspecialidad().getNombre());
	        stmt.setString(12, medico.getDia_atencion());
	        stmt.setInt(13, medico.getHora_inicio_atencion());
	        stmt.setInt(14, medico.getHora_fin_atencion());
	        stmt.setInt(15, medico.getDni());
	        filas = stmt.executeUpdate();
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		return filas;
	}
	
	public int eliminarMedico(int Dni) {
		int filas = 0;
		Connection cn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement stmt =  (PreparedStatement) cn.prepareStatement("UPDATE Medico SET Estado = 0 WHERE Dni = ?");
			
		    stmt.setInt(1, Dni);
		    filas = stmt.executeUpdate();
			cn.close();
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		return filas;
	}
	
	public ArrayList<Medico> getTodoDni(int Dni) {
		String query = "SELECT * FROM Medico Where Estado = '1' AND Dni ='" + Dni + "'";
		Connection cn = null;
		
		ArrayList<Medico> Lista = new ArrayList<Medico>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Medico Med = new Medico();
				Med.setDni(rs.getInt("Dni"));
				Med.setNombre(rs.getString("Nombre"));
				Med.setApellido(rs.getString("Apellido"));
				Med.setSexo(rs.getString("Sexo"));
				Med.setNacionalidad(rs.getString("Nacionalidad"));
				Med.setFecha_nac(rs.getString("Fecha_Nac"));
				Med.setDireccion(rs.getString("Direccion"));
				Med.setLocalidad(rs.getString("Localidad"));
				Med.setCorreo_Electronico(rs.getString("Correo_Electronico"));
				Med.setTelefono(rs.getString("Telefono"));
				Med.setProvincia(rs.getString("Provincia"));
				Med.setEspecialidad(new Especialidad());
				Med.setEspecialidadNombre(rs.getString("Especialidad"));
				Med.setDia_atencion(rs.getString("Dia_Atencion"));
				Med.setHora_inicio_atencion(rs.getInt("Hora_Inicio_Atencion"));
				Med.setHora_fin_atencion(rs.getInt("Hora_Fin_Atencion"));
				Med.setEstado(rs.getInt("Estado"));
				Lista.add(Med);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public Medico getMedicoSelect(int dni) {
		String query = "SELECT * FROM Medico WHERE Estado = '1' AND Dni = '" + dni + "'";
		Connection cn = null;
		
		Medico Med = new Medico();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {  
				Med.setDni(rs.getInt("Dni"));
				Med.setNombre(rs.getString("Nombre"));
				Med.setApellido(rs.getString("Apellido"));
				Med.setSexo(rs.getString("Sexo"));
				Med.setNacionalidad(rs.getString("Nacionalidad"));
				Med.setFecha_nac(rs.getString("Fecha_Nac"));
				Med.setDireccion(rs.getString("Direccion"));
				Med.setLocalidad(rs.getString("Localidad"));
				Med.setCorreo_Electronico(rs.getString("Correo_Electronico"));
				Med.setTelefono(rs.getString("Telefono"));
				Med.setProvincia(rs.getString("Provincia"));
				Med.setEspecialidad(new Especialidad());
				Med.setEspecialidadNombre(rs.getString("Especialidad"));
				Med.setDia_atencion(rs.getString("Dia_Atencion"));
				Med.setHora_inicio_atencion(rs.getInt("Hora_Inicio_Atencion"));
				Med.setHora_fin_atencion(rs.getInt("Hora_Fin_Atencion"));
				Med.setEstado(rs.getInt("Estado"));
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Med;
	}
	
	public int existeDni(int dni) {
	    String query = "SELECT COUNT(*) AS Count FROM Medico WHERE Estado = '1' AND Dni = ?";
	    Connection cn = null;
	    int count = 0;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        cn = DriverManager.getConnection(host + dbName, user, pass);
	        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(query);
	        ps.setInt(1, dni);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt("Count");
	        }
	        cn.close();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
}
