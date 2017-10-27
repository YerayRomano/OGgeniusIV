package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

public class Album {
	int cod_album;
	String nombre;
	int ano;
	int vetado;
	public Album(int cod_album, String nombre, int ano, int vetado) {
		super();
		this.cod_album = cod_album;
		this.nombre = nombre;
		this.ano = ano;
		this.vetado = vetado;
	}
	public Album() {
		
	}
	public int getCod_album() {
		return cod_album;
	}
	public void setCod_album(int cod_album) {
		this.cod_album = cod_album;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getVetado() {
		return vetado;
	}
	public void setVetado(int vetado) {
		this.vetado = vetado;
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
	public HashMap<Integer,Album> getAllAlbunes() {
		HashMap<Integer,Album> albunes = new HashMap<Integer,Album>();
		Statement stmt = null;
		String consulta = "SELECT * FROM albunes WHERE vetado=0";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				albunes.put(rs.getInt(1),new Album(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return albunes;
	}
	public Album getAlbum(int album) {
		String consulta = "SELECT * FROM albunes WHERE cod_album='"+album+"'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			if(rs.next() == false) {
				return null;
			}
			Album albuma = new Album(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			return albuma;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
