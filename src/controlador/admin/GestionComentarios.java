package controlador.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Comentado;
import modelo.Comentario;
import modelo.Usuario;

/**
 * Servlet implementation class GestionComentarios
 */
@WebServlet("/GestionComentarios")
public class GestionComentarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionComentarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		ArrayList<String> errores = new ArrayList<String>();
		boolean bandera = false;
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null
				&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			if (sesion.getAttribute("rol") == null) {

			} else {
				if (!sesion.getAttribute("rol").equals("admin")) {
					response.sendError(403);
					return;
				}
				Comentario comen = new Comentario();
				Comentado comer = new Comentado();
				Usuario usr = new Usuario();
				if(request.getParameter("reload") != null) {
					bandera = true;
				}
				if(request.getParameter("del") != null) {
					try {
						int cod_comen = Integer.parseInt(request.getParameter("del"));
						Comentario lodicho = comen.obtenerComentario(cod_comen);
						if(lodicho == null) {
							errores.add("error, el comentario no existe");
						} else {
							Comentado tombi = comer.getComentado(cod_comen);
							boolean [] buenos = {true,true};
							if(tombi != null) {
								buenos [1] = tombi.borrarComentado(cod_comen);
							}
							buenos [0] = lodicho.borrarComentario(cod_comen);
							if(buenos [0] == false || buenos [1] == false) {
								errores.add("error de borrado, contacte con el administrador de la base de datos");
							}
						}
					} catch (NumberFormatException e) {
						errores.add("error, el codigo de comentario debe de ser un numero");
					}
					bandera = true;
				}
				HashMap<Integer, Comentario> comentarios = comen.getAllComentarios();
				ArrayList<Comentado> comentados = comer.getAllComentadosByHashMapComentario(comentarios);
				ArrayList<Usuario> usuarios = usr.getUsuariosByArrayListComentados(comentados);
				if (comentarios != null && comentados != null && usuarios != null) {
					if ((comentarios.size() != comentados.size()) || (comentarios.size() != usuarios.size())
							|| (usuarios.size() != comentados.size())) {
						comentarios = null;
						comentados = null;
						usuarios = null;
					}
				}
				request.setAttribute("comentarios", comentarios);
				request.setAttribute("comentados", comentados);
				request.setAttribute("usuarios", usuarios);
				request.setAttribute("errores", errores);
				System.out.println("nigga");
				if(bandera == false) {
					request.getRequestDispatcher("/admin.jsp").include(request, response);
				} else {
					request.getRequestDispatcher("/losComentarios.jsp").include(request, response);
				}
			}
		} else {
			response.sendRedirect("/OGgenius/Principal");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
