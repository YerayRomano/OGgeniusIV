package modelo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class Momento {
	int comen;
	String nombre;
	int inicio;
	int fin;
	int habilitado;
	int cod_can;
	public Momento(int comen, String nombre, int inicio, int fin, int habilitado, int cod_can) {
		super();
		this.comen = comen;
		this.nombre = nombre;
		this.inicio = inicio;
		this.fin = fin;
		this.habilitado = habilitado;
		this.cod_can = cod_can;
	}
	public Momento() {
		
	}
	public int getComen() {
		return comen;
	}
	public void setComen(int comen) {
		this.comen = comen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	public int getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	public int getCod_can() {
		return cod_can;
	}
	public void setCod_can(int cod_can) {
		this.cod_can = cod_can;
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
	public ArrayList<Momento> getAllMometosByCancion(int cod_can) {
		ArrayList<Momento> momentos = new ArrayList<Momento>();
		Statement stmt = null;
		String consulta = String.format("SELECT * FROM usuarios WHERE cod_can=%d",cod_can);
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while(rs.next()) {
				momentos.add(new Momento(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
			}
			return momentos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(momentos.size() == 0) {
			momentos = null;
		}
		return momentos;
	}
}
