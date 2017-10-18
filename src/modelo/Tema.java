package modelo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class Tema {
	int codTema;
	String nombre;
	public Tema(int codTema, String nombre) {
		super();
		this.codTema = codTema;
		this.nombre = nombre;
	}
	public Tema() {
		
	}
	public int getCodTema() {
		return codTema;
	}
	public void setCodTema(int codTema) {
		this.codTema = codTema;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private Connection conexion() {

		String USUARIO = "root";
		String PASS = "";
		String URL_BD = "jdbc:mysql://127.0.0.1:3306/youngsta";
		try {
			return (Connection) DriverManager.getConnection(URL_BD, USUARIO, PASS);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ClassNotFoundException) {
				System.err.println("VERIFIQUE Si EL DRIVER DE LA BD ESTA EN  CLASSPATH");
			} else {
				System.err.println("ESTA ARRANCANDO MYSQL ?, lAS CREDENCIALES ESTÁN BIEN ?");
			}
			System.exit(0);
			// el programa termina y devuelve el control al S.O.
			return null;
		}
	}
	public ArrayList<Tema> getAllTemas() {
		ArrayList<Tema> temas = new ArrayList<Tema>();
		Statement stmt = null;
		String consulta = "SELECT * FROM temas";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while(rs.next()) {
				temas.add(new Tema(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(temas.size() == 0) {
			return null;
		}
		return temas;
	}
	public boolean temaExiste(int tema) {
		ArrayList<Tema> temas = new ArrayList<Tema>();
		Statement stmt = null;
		String consulta = "SELECT * FROM temas";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
