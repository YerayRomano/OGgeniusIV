package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

public class Cancion {
	int cod_can;
	String titulo;
	int ano;
	String duracion;
	int visible;
	int autoria;
	String descripcion;
	
	public Cancion(int cod_can, String titulo, int ano, String duracion, int visible, String descripcion,int autoria) {
		super();
		this.cod_can = cod_can;
		this.titulo = titulo;
		this.ano = ano;
		this.duracion = duracion;
		this.visible = visible;
		this.autoria = autoria;
		this.descripcion = descripcion;
	}
	public Cancion(int cod_can, String titulo, int ano, String duracion, int visible, int autoria) {
		super();
		this.cod_can = cod_can;
		this.titulo = titulo;
		this.ano = ano;
		this.duracion = duracion;
		this.visible = visible;
		this.autoria = autoria;
	}
	public Cancion() {
		
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCod_can() {
		return cod_can;
	}

	public void setCod_can(int cod_can) {
		this.cod_can = cod_can;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getAutoria() {
		return autoria;
	}

	public void setAutoria(int autoria) {
		this.autoria = autoria;
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
	public ArrayList<Cancion> getAllCanciones() {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		Statement stmt = null;
		String consulta = "SELECT * FROM canciones;";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				canciones.add(new Cancion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),rs.getString(6),rs.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return canciones;
	}
	public boolean cancionExiste(int cancion) {
		Statement stmt = null;
		String consulta = "SELECT * FROM canciones WHERE cod_can=+"+cancion+";";
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
	public Cancion getCancion(int cancion) {
		String consulta = "SELECT * FROM canciones WHERE cod_can='"+cancion+"'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			if(rs.next() == false) {
				return null;
			}
			Cancion can =new Cancion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),rs.getString(6),rs.getInt(7));
			return can;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
