package controlador.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class GestionUsuarios
 */
@WebServlet("/GestionUsuarios")
public class GestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionUsuarios() {
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
					response.sendRedirect("/OGgenius/Bienvenida");
			} else {
				if (!sesion.getAttribute("rol").equals("admin")) {
					response.sendError(403);
					return;
				}
				Usuario usr = new Usuario();
				if(request.getParameter("frame") != null) {
					if(!request.getParameter("frame").equals("")) {
						try {
							int frame = Integer.parseInt(request.getParameter("frame"));
							String mevoy = "";
							switch(frame) {
								case 1:
									mevoy = "ingreso.jsp";
								break;
							}
							if(!mevoy.equals("")) {
								request.getRequestDispatcher(mevoy).include(request, response);
								return;
							}
						} catch (NumberFormatException e) {
							
						}
					}
				}
				if(request.getParameter("del") != null) {
					try {
						if(request.getParameter("del").equals("")) {
							errores.add("error, el codigo de usuario esta vacio");
						} else {
							int migo = Integer.parseInt(request.getParameter("del"));
							if(migo == (int) sesion.getAttribute("cod_usr")) {
								errores.add("error, no te puedes borrar a ti mismo sanaca!!!");
							}
							Usuario quavo = new Usuario();
							quavo = usr.getUserdata(migo);
							if(quavo != null) {
								boolean borrado = usr.borrarUsuario(quavo.getCod_usr());
								if(borrado == false) {
									errores.add("error de borrado");
								} else {
									String rutaImagen = "perfiles/default.jpg";
									String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
									File tieneImagen = new File(directorio + sesion.getAttribute("cod_usr") + ".jpg");
									System.out.println(tieneImagen.getCanonicalFile());
									if (tieneImagen.exists()) {
										rutaImagen = "perfiles/" + sesion.getAttribute("cod_usr") + ".jpg";
										tieneImagen.delete();
									}
								}
							} else {
								errores.add("error, el usuario no existe");
							}
						}
					} catch (NumberFormatException e) {
						errores.add("error, el codigo de usuario no es numerico");
					}
					bandera = true;
				}
				ArrayList<Usuario> usuarios = usr.mostrarUsuarios();
				request.setAttribute("usuarios",usuarios);
				if(bandera == false) {
					request.getRequestDispatcher("/adminUsuarios.jsp").include(request, response);
				} else {
					request.getRequestDispatcher("/verUsuarios.jsp").include(request, response);
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
			}
		} else {
			response.sendRedirect("/OGgenius/Principal");
		}
	}
}
