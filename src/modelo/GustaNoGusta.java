package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class GustaNoGusta {
	int cod_opinion;
	int cod_can;
	int cod_usr;
	int tipo;
	public GustaNoGusta(int cod_opinion, int cod_can, int cod_usr, int tipo) {
		super();
		this.cod_opinion = cod_opinion;
		this.cod_can = cod_can;
		this.cod_usr = cod_usr;
		this.tipo = tipo;
	}
	public GustaNoGusta() {
		
	}
	public int getCod_opinion() {
		return cod_opinion;
	}
	public void setCod_opinion(int cod_opinion) {
		this.cod_opinion = cod_opinion;
	}
	public int getCod_can() {
		return cod_can;
	}
	public void setCod_can(int cod_can) {
		this.cod_can = cod_can;
	}
	public int getCod_usr() {
		return cod_usr;
	}
	public void setCod_usr(int cod_usr) {
		this.cod_usr = cod_usr;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public ArrayList <GustaNoGusta> getAllOpinionesCancion(int cod_can) {
		ArrayList <GustaNoGusta> opniones = new ArrayList<GustaNoGusta>();
		String consulta = "SELECT * FROM gustanogusta WHERE cod_can='"+cod_can+"'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			while(rs.next()) {
				opniones.add(new GustaNoGusta(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
			}
			return opniones;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean insertarOpinion(int cod_can,int cod_usr,int tipo) {
		String consulta = String.format("INSERT INTO gustanogusta VALUES (0,'%d','%d','%d')",cod_can,cod_usr,tipo);
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
	public boolean getHaOpinadoYaAsi(int cod_can,int cod_usr,int tipo) {
		String consulta = String.format("SELECT * FROM gustanogusta WHERE cod_can='%d' AND cod_usr='%d' AND tipo='%d'",cod_can,cod_usr,tipo);
		System.out.println(consulta);
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean cambioOpinion(int cod_can,int cod_usr,int tipo) {
		String consulta = String.format("UPDATE gustanogusta SET tipo='%d' WHERE cod_can='%d' AND cod_usr='%d'",tipo,cod_can,cod_usr);
		System.out.println(consulta);
		try {
			Connection con = this.conexion();
			Statement stm = con.createStatement();
			stm.executeUpdate(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
