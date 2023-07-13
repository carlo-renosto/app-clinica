package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.Statement;

import dao.IPaciente;
import entidad.Paciente;

	public class PacienteImpl implements IPaciente {
		private String host = "jdbc:mysql://localhost:3306/";
		private String user = "root";
		private String pass = "root";
		private String dbName = "ClinicaMedica";
	
		public ArrayList<Paciente> getPaciente() {
			String query = "SELECT * FROM Paciente WHERE Estado = '1'";
			Connection cn = null;
			
			ArrayList<Paciente> Lista = new ArrayList<Paciente>();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					Paciente Pac = new Paciente();
					Pac.setDni(rs.getInt("Dni"));
					Pac.setNombre(rs.getString("Nombre"));
					Pac.setApellido(rs.getString("Apellido"));
					Pac.setSexo(rs.getString("Sexo"));
					Pac.setNacionalidad(rs.getString("Nacionalidad"));
					Pac.setFecha_nac(rs.getString("Fecha_Nac"));
					Pac.setDireccion(rs.getString("Direccion"));
					Pac.setLocalidad(rs.getString("Localidad"));
					Pac.setCorreo_Electronico(rs.getString("Correo_Electronico"));
					Pac.setTelefono(rs.getString("Telefono"));
					Pac.setProvincia(rs.getString("Provincia"));
					Pac.setEstado(rs.getInt("Estado"));
					Lista.add(Pac);
				}
				cn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return Lista;
		}
		
		public ArrayList<Paciente> getDni() {
			String query = "SELECT Dni FROM Paciente WHERE Estado = '1'";
			Connection cn = null;
			
			ArrayList<Paciente> Lista = new ArrayList<Paciente>();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while(rs.next()) {
					Paciente Pac = new Paciente();
					Pac.setDni(rs.getInt("Dni"));
					Lista.add(Pac);
				}
				cn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return Lista;
		}
		
		public ArrayList<Paciente> filtro(String consulta) {
			String query = "SELECT * FROM Paciente" + consulta;
			Connection cn = null;
			
			ArrayList<Paciente> Lista = new ArrayList<Paciente>();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					Paciente Pac = new Paciente();
					Pac.setDni(rs.getInt("Dni"));
					Pac.setNombre(rs.getString("Nombre"));
					Pac.setApellido(rs.getString("Apellido"));
					Pac.setSexo(rs.getString("Sexo"));
					Pac.setNacionalidad(rs.getString("Nacionalidad"));
					Pac.setFecha_nac(rs.getString("Fecha_Nac"));
					Pac.setDireccion(rs.getString("Direccion"));
					Pac.setLocalidad(rs.getString("Localidad"));
					Pac.setCorreo_Electronico(rs.getString("Correo_Electronico"));
					Pac.setTelefono(rs.getString("Telefono"));
					Pac.setProvincia(rs.getString("Provincia"));
					Pac.setEstado(rs.getInt("Estado"));
					Lista.add(Pac);
				}
				cn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return Lista;
		}
		
		public int agregarPaciente(Paciente Paciente) {	
			int filas = 0;
			Connection cn = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host+dbName, user,pass);
				Statement st = (Statement) cn.createStatement();
				
				String query = "INSERT INTO Paciente(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia) VALUES(" + Paciente.getDni() +",'"+ Paciente.getNombre() +"','"+ Paciente.getApellido()+"','"+ Paciente.getSexo() +"','"+ Paciente.getNacionalidad() +"','"+ Paciente.getFecha_nac() +"','"+ Paciente.getDireccion() +"','"+ Paciente.getLocalidad() +"','"+ Paciente.getCorreo_Electronico() +"','"+ Paciente.getTelefono() +"','"+ Paciente.getProvincia() +"')";
				filas = st.executeUpdate(query);
				cn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return filas;
		}
		
		public int editarPaciente(Paciente paciente) {
			int filas = 0;
			Connection cn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement stmt =  (PreparedStatement) cn.prepareStatement("UPDATE Paciente SET Nombre = ?, Apellido = ?, Sexo= ?, Nacionalidad = ?, Fecha_nac= ?, Direccion= ?, Localidad = ?, Correo_Electronico = ?, Telefono = ?, Provincia = ?  WHERE Dni = ?");
				
		        stmt.setString(1, paciente.getNombre());
		        stmt.setString(2, paciente.getApellido());
		        stmt.setString(3, paciente.getSexo());
		        stmt.setString(4, paciente.getNacionalidad());
		        stmt.setString(5, paciente.getFecha_nac());
		        stmt.setString(6, paciente.getDireccion());
		        stmt.setString(7, paciente.getLocalidad());
		        stmt.setString(8, paciente.getCorreo_Electronico());
		        stmt.setString(9, paciente.getTelefono());
		        stmt.setString(10, paciente.getProvincia());
		        stmt.setInt(11, paciente.getDni());
		        filas = stmt.executeUpdate();
			} 
			catch (Exception e) {
			    e.printStackTrace();
			}
			return filas;
		}
		
		public int eliminarPaciente(int Dni) {
			int filas = 0;
			Connection cn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement stmt =  (PreparedStatement) cn.prepareStatement("UPDATE Paciente SET Estado = 0 WHERE Dni = ?");
				
			    stmt.setInt(1, Dni);
			    filas = stmt.executeUpdate();
			    cn.close();
			} 
			catch (Exception e) {
			    e.printStackTrace();
			}
			return filas;
		}
		
		public ArrayList<Paciente> getTodoDni(int dni) {
		    String query = "SELECT * FROM Paciente WHERE Estado = '1' AND Dni = ?";
		    Connection cn = null;
		    
		    ArrayList<Paciente> lista = new ArrayList<Paciente>();
		    
		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        cn = DriverManager.getConnection(host + dbName, user, pass);
		        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(query);
		        ps.setInt(1, dni);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            Paciente paciente = new Paciente();
		            paciente.setDni(rs.getInt("Dni"));
		            paciente.setNombre(rs.getString("Nombre"));
		            paciente.setApellido(rs.getString("Apellido"));
		            paciente.setSexo(rs.getString("Sexo"));
		            paciente.setNacionalidad(rs.getString("Nacionalidad"));
		            paciente.setFecha_nac(rs.getString("Fecha_Nac"));
		            paciente.setDireccion(rs.getString("Direccion"));
		            paciente.setLocalidad(rs.getString("Localidad"));
		            paciente.setCorreo_Electronico(rs.getString("Correo_Electronico"));
		            paciente.setTelefono(rs.getString("Telefono"));
		            paciente.setProvincia(rs.getString("Provincia"));
		            paciente.setEstado(rs.getInt("Estado"));
		            lista.add(paciente);
		        }
		        cn.close();
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		    return lista;
		}
		
		public Paciente getPacienteSelect(int dni) {
			String query = "SELECT * FROM Paciente WHERE Estado = '1' AND Dni = '" + dni + "'";
			Connection cn = null;
			
			Paciente Pac = new Paciente();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {  
					Pac.setDni(rs.getInt("Dni"));
					Pac.setNombre(rs.getString("Nombre"));
					Pac.setApellido(rs.getString("Apellido"));
					Pac.setSexo(rs.getString("Sexo"));
					Pac.setNacionalidad(rs.getString("Nacionalidad"));
					Pac.setFecha_nac(rs.getString("Fecha_Nac"));
					Pac.setDireccion(rs.getString("Direccion"));
					Pac.setLocalidad(rs.getString("Localidad"));
					Pac.setCorreo_Electronico(rs.getString("Correo_Electronico"));
					Pac.setTelefono(rs.getString("Telefono"));
					Pac.setProvincia(rs.getString("Provincia"));
					Pac.setEstado(rs.getInt("Estado"));
				}
				cn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return Pac;
		}
		
		public int existeDni(int dni) {
		    String query = "SELECT COUNT(*) AS Count FROM Paciente WHERE Estado = '1' AND Dni = ?";
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