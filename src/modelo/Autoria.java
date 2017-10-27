package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

public class Autoria {
	int cod_autoria;
	int cod_modafoca;
	int cod_album;
	public Autoria(int cod_autoria, int cod_modafoca, int cod_artista) {
		super();
		this.cod_autoria = cod_autoria;
		this.cod_modafoca = cod_modafoca;
		this.cod_album = cod_artista;
	}
	public Autoria() {
		
	}
	public int getCod_autoria() {
		return cod_autoria;
	}
	public void setCod_autoria(int cod_autoria) {
		this.cod_autoria = cod_autoria;
	}
	public int getCod_modafoca() {
		return cod_modafoca;
	}
	public void setCod_modafoca(int cod_modafoca) {
		this.cod_modafoca = cod_modafoca;
	}
	public int getCod_album() {
		return cod_album;
	}
	public void setCod_album(int cod_artista) {
		this.cod_album = cod_artista;
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
	public HashMap<Integer,Autoria> getAllAutorias() {
		HashMap<Integer,Autoria> autorias = new HashMap<Integer,Autoria>();
		Statement stmt = null;
		String consulta = "SELECT * FROM autoria";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				autorias.put(rs.getInt(1),new Autoria(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return autorias;
	}
	public ArrayList<Autoria> getAutoria(int autoria) {
		ArrayList<Autoria> losModafocas = new ArrayList<Autoria>();
		String consulta = "SELECT * FROM autoria WHERE cod_autoria='"+autoria+"'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			while(rs.next() == true) {
				losModafocas.add(new Autoria(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return losModafocas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
