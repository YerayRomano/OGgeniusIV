package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

public class Artista {
	int cod_modafoca;
	String nombre;
	int ano;
	int vetado;
	String descripcion;

	public Artista(int cod_modafoca, String nombre, int ano, int vetado, String descripcion) {
		super();
		this.cod_modafoca = cod_modafoca;
		this.nombre = nombre;
		this.ano = ano;
		this.vetado = vetado;
		this.descripcion = descripcion;
	}

	public Artista() {

	}

	public int getCod_modafoca() {
		return cod_modafoca;
	}

	public void setCod_modafoca(int cod_modafoca) {
		this.cod_modafoca = cod_modafoca;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public HashMap<Integer, Artista> getAllArtistas() {
		HashMap<Integer, Artista> artistas = new HashMap<Integer, Artista>();
		Statement stmt = null;
		String consulta = "SELECT * FROM artistas WHERE vetado=0";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				artistas.put(rs.getInt(1),
						new Artista(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return artistas;
	}

	public Artista getArtista(int artista) {
		String consulta = "SELECT * FROM artistas WHERE cod_modafoca='" + artista + "'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			// preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			if (rs.next() == false) {
				return null;
			}
			Artista aof = new Artista(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			return aof;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean borrarArtista(int cod_artista) {
		Statement stmt = null;
		String consulta = "DELETE FROM artistas WHERE cod_modafoca=" + cod_artista + "; ";
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

	public boolean artistaExiste(String nombre) {
		String consulta = "SELECT * FROM artistas WHERE nombre='" + nombre + "'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			// preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}
	public boolean addArtista(String nombre,int ano,int vetado,String descpcion) {
		String consulta = String.format("INSERT INTO artistas VALUES(0,'%s',%d,%d,'%s')",nombre,ano,vetado,descpcion);
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
	public Artista getArtista(String nombre) {
		String consulta = "SELECT * FROM artistas WHERE nombre='" + nombre + "'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			// preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			rs.next();
			Artista aof = new Artista(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
			return aof;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	public boolean modArtista(String nombre,int ano,int vetado,String descpcion) {
		String consulta = String.format("UPDATE artistas SET nombre='%s',ano=%d,vetado=%d,descripcion='%s' WHERE nombre='%s'",nombre,ano,vetado,descpcion,nombre);
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
}
