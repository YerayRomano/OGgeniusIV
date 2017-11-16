package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class Usuario {
	private int cod_usr;
	private String nombre;
	private String apellidos;
	private String mail;
	private String pss;
	private int activo;
	private int tema;
	public Usuario(int cod_usr, String nombre, String apellidos, String mail, String pss, int activo, int tema) {
		super();
		this.cod_usr = cod_usr;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.mail = mail;
		this.pss = pss;
		this.activo = activo;
		this.tema = tema;
	}
	public Usuario() {
		
	}
	public int getCod_usr() {
		return cod_usr;
	}
	public void setCod_usr(int cod_usr) {
		this.cod_usr = cod_usr;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPss() {
		return pss;
	}
	public void setPss(String pss) {
		this.pss = pss;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public int getTema() {
		return tema;
	}
	public void setTema(int tema) {
		this.tema = tema;
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
	public ArrayList<Usuario> mostrarUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Statement stmt = null;
		String consulta = "SELECT * FROM usuarios";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
		
	}
	public boolean loginUsuario(String mail,String pss) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String consulta = "SELECT * FROM usuarios WHERE mail='"+ mail +"' AND pss=md5('"+pss+"')";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			//preparedStatement.setString(2,pss);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			try {
				if(rs.next() == false) {
					return false;
				}
				return true;
			} catch (NullPointerException e) {
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Usuario getUserdata(String mail) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String consulta = "SELECT * FROM usuarios WHERE mail='"+mail+"'";
		try {
			Connection con = this.conexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			//preparedStatement.setString(1,mail);
			ResultSet rs = preparedStatement.executeQuery(consulta);
			if(rs.next() == false) {
				return null;
			}
			Usuario usuario =new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
			return usuario;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean insertarUsuario(Usuario elMetido) {
		if(elMetido == null) {
			return false;
		}
		String consulta = String.format("INSERT INTO usuarios VALUES (0,'%s','%s','%s',md5('%s'),1,0)",elMetido.getNombre(),elMetido.getApellidos(),elMetido.getMail(),elMetido.getPss());
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
