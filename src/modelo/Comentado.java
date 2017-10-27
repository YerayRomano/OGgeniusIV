package modelo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.mysql.jdbc.Connection;

public class Comentado {
	int cod_comentado;
	int cod_usr;
	int cod_comen;
	int cod_can;
	public Comentado(int cod_comentado, int cod_usr, int cod_comen, int cod_can) {
		super();
		this.cod_comentado = cod_comentado;
		this.cod_usr = cod_usr;
		this.cod_comen = cod_comen;
		this.cod_can = cod_can;
	}
	public Comentado() {
		
	}
	public int getCod_comentado() {
		return cod_comentado;
	}
	public void setCod_comentado(int cod_comentado) {
		this.cod_comentado = cod_comentado;
	}
	public int getCod_usr() {
		return cod_usr;
	}
	public void setCod_usr(int cod_usr) {
		this.cod_usr = cod_usr;
	}
	public int getCod_comen() {
		return cod_comen;
	}
	public void setCod_comen(int cod_comen) {
		this.cod_comen = cod_comen;
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
	public ArrayList<Comentado> getAllComentadosCancion(int cancion) {
		ArrayList<Comentado> comentados = new ArrayList<Comentado>();
		Statement stmt = null;
		String consulta = "SELECT * FROM comentados WHERE cod_can="+cancion+";";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				comentados.add(new Comentado(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
			}
			return comentados;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return comentados;
	}
	public boolean insertarComentado(int cod_usr,int cod_comen,int cod_can) {
		String consulta = String.format("INSERT INTO comentados VALUES (0,'%d','%d','%d')",cod_usr,cod_comen,cod_can);
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
	public ArrayList<Comentado> getAllComentadosByHashMapComentario(HashMap<Integer,Comentario> comentarios) {
		ArrayList<Comentado> comentados = new ArrayList<Comentado>();
		if(comentarios == null) {
			return null;
		}
		if(comentarios.size() == 0) {
			return null;
		}
		java.util.Set<Integer> keys = comentarios.keySet();
		for(Integer key:keys) {
			Comentario elcomentario = comentarios.get(key);
			if(elcomentario != null) {
				Comentado comentado = this.getComentado(elcomentario.getCodcamen());
				if(comentado != null)
					comentados.add(this.getComentado(elcomentario.getCodcamen()));
			}
		}
		if(comentados.size() == 0) {
			return null;
		}
		return comentados;
	}
	public Comentado getComentado(int cod_comentado) {
		Statement stmt = null;
		String consulta = "SELECT * FROM comentados WHERE cod_comen="+cod_comentado+";";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			boolean bueno = rs.next();
			if(bueno == false) {
				return null;
			}
			Comentado comen = (new Comentado(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
			return comen;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	public Comentado obtenerComentado(int cod_comentado) {
		Statement stmt = null;
		String consulta = "SELECT * FROM comentados WHERE cod_comen="+cod_comentado+"; ";
		try {
			Connection con = this.conexion();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			if(!rs.next()) {
				return null;
			}
			Comentado comentado = new Comentado(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
			return comentado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	public boolean borrarComentado(int cod_comen) {
		Statement stmt = null;
		String consulta = "DELETE FROM comentados WHERE cod_comen="+cod_comen+"; ";
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
