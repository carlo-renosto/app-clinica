package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Statement;

import dao.IEspecialidad;
import entidad.Especialidad;

public class EspecialidadImpl implements IEspecialidad {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "ClinicaMedica";
	
	public ArrayList<Especialidad> getEspecialidades() {
		String query = "SELECT * FROM Especialidad";
		Connection cn = null;
		
		ArrayList<Especialidad> Lista = new ArrayList<Especialidad>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
		
				Especialidad Esp = new Especialidad();
				Esp.setId(rs.getInt("Id"));
				Esp.setNombre(rs.getString("Nombre"));
				Lista.add(Esp);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
}
