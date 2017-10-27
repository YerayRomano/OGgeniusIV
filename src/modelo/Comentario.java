package modelo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

public class Comentario {
	int codcamen;
	String contenido;
	public Comentario(int codcamen, String contenido) {
		super();
		this.codcamen = codcamen;
		this.contenido = contenido;
	}
	public Comentario() {
		
	}
	public int getCodcamen() {
		return codcamen;
	}
	public void setCodcamen(int codcamen) {
		this.codcamen = codcamen;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
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
	public HashMap<Integer,Comentario> getAllComentarios() {
		HashMap<Integer,Comentario> comentarios = new HashMap<Integer,Comentario>();
		Statement stmt = null;
		String consulta = "SELECT * FROM comentarios ORDER BY cod_comen DESC";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				comentarios.put(rs.getInt(1),new Comentario(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return comentarios;
	}
	public boolean insertarComentario(String contenido) {
		if(contenido == null) {
			return false;
		}
		String consulta = String.format("INSERT INTO comentarios VALUES (0,'%s')",contenido);
		System.out.println(consulta);
		try {
			Connection con = this.conexion();
			Statement stm = con.createStatement();
			stm.executeUpdate(consulta);
			//preparedStatement.setString(1,mail);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public int obtenerUltimoComentario() {
		Statement stmt = null;
		String consulta = "SELECT cod_comen FROM comentarios ORDER BY cod_comen DESC; ";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			if(rs.next()) {
				return rs.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return -1;
	}
	public Comentario obtenerComentario(int cod_comen) {
		Statement stmt = null;
		String consulta = "SELECT * FROM comentarios WHERE cod_comen="+cod_comen+"; ";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			if(!rs.next()) {
				return null;
			}
			Comentario comenta = new Comentario(rs.getInt(1),rs.getString(2));
			return comenta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	public boolean borrarComentario(int cod_comen) {
		Statement stmt = null;
		String consulta = "DELETE FROM comentarios WHERE cod_comen="+cod_comen+"; ";
		try {
			Connection con = this.conexion();
			Statement stm = con.createStatement();
			stm.executeUpdate(consulta);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}
}
