package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.Statement;

import dao.ITurno;
import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;

public class TurnoImpl implements ITurno {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "ClinicaMedica";
	
	public ArrayList<Turno> getTurno() {
		String query = "SELECT * FROM Turno INNER JOIN Medico ON Turno.Dni_Medico = Medico.Dni AND Medico.Estado = TRUE INNER JOIN Paciente ON Turno.Dni_Paciente = Paciente.Dni AND Paciente.Estado = TRUE";
		Connection cn = null;
		
		ArrayList<Turno> Lista = new ArrayList<Turno>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Turno Tur = new Turno();
				Tur.setId(rs.getInt("Id"));
				Tur.setEspecialidad(new Especialidad());
				Tur.setEspecialidadNombre(rs.getString("Especialidad"));
				Tur.setMedico(new Medico());
				Tur.setDniMedico(rs.getInt("Dni_Medico"));
				Tur.setPaciente(new Paciente());
				Tur.setDniPaciente(rs.getInt("Dni_Paciente"));
				Tur.setDiaTurno(rs.getString("Dia_Turno"));
				Tur.setHoraTurno(rs.getString("Hora_Turno"));
				Tur.setObservacion(rs.getString("Observacion"));
				Tur.setEstado(rs.getString("Estado"));
				Lista.add(Tur);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public Turno getTurnoId(int id) {
		String query = "SELECT * FROM Turno WHERE ID = " + id;
		Connection cn = null;
		
		Turno turno = new Turno();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				turno.setEspecialidad(new Especialidad());
				turno.setEspecialidadNombre(rs.getString("Especialidad"));
				turno.setMedico(new Medico());
				turno.setDniMedico(rs.getInt("Dni_Medico"));
				turno.setPaciente(new Paciente());
				turno.setDniPaciente(rs.getInt("Dni_Paciente"));
				turno.setDiaTurno(rs.getString("Dia_Turno"));
				turno.setHoraTurno(rs.getString("Hora_Turno"));
				turno.setEstado(rs.getString("Estado"));
			}
			cn.close();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
		return turno;
	}

	public ArrayList<Turno> filtro(String consulta) {
		String query = "SELECT * FROM Turno" + consulta;
		Connection cn = null;
		
		ArrayList<Turno> Lista = new ArrayList<Turno>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Turno Tur = new Turno();
				Tur.setEspecialidad(new Especialidad());
				Tur.setEspecialidadNombre(rs.getString("Especialidad"));
				Tur.setMedico(new Medico());
				Tur.setDniMedico(rs.getInt("Dni_Medico"));
				Tur.setPaciente(new Paciente());
				Tur.setDniPaciente(rs.getInt("Dni_Paciente"));
				Tur.setDiaTurno(rs.getString("Dia_Turno"));
				Tur.setHoraTurno(rs.getString("Hora_Turno"));
				Tur.setEstado(rs.getString("Estado"));
				Lista.add(Tur);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public ArrayList<Turno> filtroUser(String consulta, int dni) {
		String query = "SELECT * FROM Turno"+consulta;
		String query2 = "SELECT * FROM Paciente INNER JOIN Turno ON Paciente.Dni = Turno.Dni_Paciente WHERE Turno.Dni_Medico = " + dni;
		Connection cn = null;
		
		ArrayList<Turno> Lista = new ArrayList<Turno>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Turno Tur = new Turno();
				Tur.setMedico(new Medico());
				Tur.setDniMedico(rs.getInt("Dni_Medico"));
				
				Statement st2 = (Statement) cn.createStatement();
				ResultSet rs2 = st2.executeQuery(query2);
				while(rs2.next()) {
					Tur.setPaciente(new Paciente());
					Tur.setDniPaciente(rs2.getInt("Dni"));
					Tur.setNombreApellidoPaciente(rs2.getString("Nombre"), rs2.getString("Apellido"));
					Tur.setTelefonoPaciente(rs2.getString("Telefono"));
				}
				
				Tur.setId(rs.getInt("Id"));
				Tur.setEspecialidad(new Especialidad());
				Tur.setEspecialidadNombre(rs.getString("Especialidad"));
				Tur.setMedico(new Medico());
				Tur.setDniMedico(rs.getInt("Dni_Medico"));
				Tur.setDiaTurno(rs.getString("Dia_Turno"));
				Tur.setHoraTurno(rs.getString("Hora_Turno"));
				Tur.setObservacion(rs.getString("Observacion"));
				Tur.setEstado(rs.getString("Estado"));
				Lista.add(Tur);
			}
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}

	public int agregarTurno(Turno turno) {
		String query = "SELECT MAX(Id) FROM Turno";
		Connection cn = null;
		int filas = 0;
	     
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	cn = DriverManager.getConnection(host + dbName, user, pass);
	    	
	    	Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				turno.setId(rs.getInt("Max(Id)") + 1);
			}
	    	
	    	PreparedStatement stmt = (PreparedStatement) cn.prepareStatement("INSERT INTO Turno (Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		    stmt.setInt(1, turno.getId());
		    stmt.setString(2, turno.getEspecialidadNombre());
		    stmt.setInt(3, turno.getMedico().getDni());
		    stmt.setInt(4, turno.getPaciente().getDni());
		    stmt.setString(5, turno.getDiaTurno());
		    stmt.setString(6, turno.getHoraTurno());
		    stmt.setString(7, turno.getObservacion());
		    stmt.setString(8, turno.getEstado());
		    
		    filas = stmt.executeUpdate();
			cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return filas;
	}
	
	/// Usuario Medico
	
	public ArrayList<Turno> getTurnosMedico(int dni) {
        String query = "SELECT * FROM Turno WHERE DNI_Medico = ?";
        String query2 = "SELECT * FROM Paciente WHERE Dni = ?";
        Connection cn = null;
        
        ArrayList<Turno> turnos = new ArrayList<>();

        try {
        	Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
        	PreparedStatement statement = cn.prepareStatement(query);
            statement.setInt(1, dni);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Turno turno = new Turno();
                turno.setId(rs.getInt("Id"));
                turno.setEspecialidad(new Especialidad());
                turno.setEspecialidadNombre(rs.getString("Especialidad"));
                turno.setMedico(new Medico());
                turno.setDniMedico(rs.getInt("Dni_Medico"));
                
                PreparedStatement statement2 = cn.prepareStatement(query2);
                statement2.setInt(1, rs.getInt("Dni_Paciente"));
                ResultSet rs2 = statement2.executeQuery();
                while(rs2.next()) {
                	 turno.setPaciente(new Paciente());
                     turno.setDniPaciente(rs.getInt("Dni_Paciente"));
                     turno.setNombreApellidoPaciente(rs2.getString("Nombre"), rs2.getString("Apellido"));
                     turno.setTelefonoPaciente(rs2.getString("Telefono"));
                }
                
                turno.setDiaTurno(rs.getString("Dia_Turno"));
                turno.setHoraTurno(rs.getString("Hora_Turno"));
                turno.setObservacion(rs.getString("Observacion"));
                turno.setEstado(rs.getString("Estado"));
                turnos.add(turno);
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turnos;
    }
	
	public ArrayList<Integer> getHorasMedico(int dni) {
		String query = "SELECT Hora_Turno FROM Turno INNER JOIN Medico ON Turno.Dni_Medico = " + dni;
		Connection cn = null;
		
		ArrayList<Integer> horas = new ArrayList<Integer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Integer hora = rs.getInt("Hora_Turno");
				horas.add(hora);
			} 
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return horas;
	}
	
	public ArrayList<Integer> getDniPacientes(int dni) {
		String query = "SELECT Dni FROM Paciente INNER JOIN Turno ON Paciente.Dni = Turno.Dni_Paciente AND Turno.Dni_Medico = " + dni;
		Connection cn = null;
		
		ArrayList<Integer> dniPacientes = new ArrayList<Integer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int dniPaciente = rs.getInt("Dni");
				dniPacientes.add(dniPaciente);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dniPacientes;
	}
	
    public void marcarPresente(int id) {
        String query = "UPDATE Turno SET Estado = 'Presente' WHERE Id = ?";
        Connection cn = null;

        try { 
        	Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
        	PreparedStatement statement = cn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void marcarAusente(int id) {
        String query = "UPDATE Turno SET Estado = 'Ausente' WHERE Id = ?";
        Connection cn = null;

        try {
        	Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
        	PreparedStatement statement = cn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void marcarObservacion(String observacion, int id, int dni) {
    	String query = "UPDATE Turno SET Observacion = ? WHERE Id = ? AND Dni_Medico = ?";
        Connection cn = null;

        try {
        	Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
        	PreparedStatement statement = cn.prepareStatement(query);
        	statement.setString(1, observacion);
            statement.setInt(2, id);
            statement.setInt(3, dni);
            statement.executeUpdate();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
