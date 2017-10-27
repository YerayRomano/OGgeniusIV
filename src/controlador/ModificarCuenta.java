package controlador;

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
 * Servlet implementation class ModificarCuenta
 */
@WebServlet("/ModificarCuenta")
public class ModificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarCuenta() {
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
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null
				&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			Usuario usr = new Usuario();
			usr = usr.getUserdata((int) sesion.getAttribute("cod_usr"));
			if (usr == null) {
				sesion.invalidate();
				response.sendRedirect("/OGgenius/Bienvenida");
			}
			request.setAttribute("tusDatos", usr);
			request.getRequestDispatcher("/modData.jsp").forward(request, response);
		} else {
			response.sendRedirect("/OGgenius/Bienvenida");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null
				&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			ArrayList<String> errores = new ArrayList<String>();
			if (request.getParameter("nome") == null) {
				errores.add("Error, no se ha mandado el campo del nombre");
			}
			if (request.getParameter("apellidos") == null) {
				errores.add("Error, no se ha mandado el campo del apellido");
			}
			String neiga = request.getParameter("apellidos");
			if (request.getParameter("mail") == null) {
				errores.add("Error, no se ha mandado el campo del mail");
			}
			if (request.getParameter("pss") == null) {
				errores.add("Error, no se ha mandado el campo de la contrase&ntilde;a");
			}
			if (request.getParameter("pss0") == null) {
				errores.add("Error, no se ha mandado la contrase&ntilde;a repetida");
			}
			if (errores.size() == 0) {
				if (request.getParameter("nome") == "") {
					errores.add("Error, el nombre no puede estar vacio");
				} else {
					try {
						Integer.parseInt(request.getParameter("nome"));
						errores.add("Error, tu nombre no puede ser un numero");
					} catch (NumberFormatException e) {

					}
				}
				if (request.getParameter("apellidos") == "") {
					errores.add("Error, el apellido no puede estar vacio");
				} else {
					try {
						Integer.parseInt(request.getParameter("apellidos"));
						errores.add("Error, tu apellido no puede ser un numero");
					} catch (NumberFormatException e) {

					}
				}
				if (request.getParameter("mail") == "") {
					errores.add("Error, no has puesto tu correo");
				} else {
					if (request.getParameter("mail").split("@").length != 2) {
						errores.add("Error, formato de correo no valido");
					} else {
						if(!request.getParameter("mail").equals((String) sesion.getAttribute("mail"))) {
							errores.add("Error, no te puedes cambiar el correo");
						}
					}
				}
				boolean inmovil = false;
				if(request.getParameter("pss").equals("") && request.getParameter("pss0").equals("")) {
					inmovil = true;
				}
				if (!inmovil) {
					if (request.getParameter("pss") == "") {
						errores.add("Error, contrase&nilde;a vacia");
					} else {
						if (request.getParameter("pss").length() < 6) {
							errores.add("Error, la contrase&ntilda;a debe de tener una logitud minima de 6 caracteres");
						}
						try {
							Integer.parseInt(request.getParameter("pss"));
							errores.add("Error, la contrase&ntilda;a no puede ser un numero");
						} catch (NumberFormatException e) {

						}
					}
					if (request.getParameter("pss0") == "") {
						errores.add("Error, contrase&nilde;a repetida vacia");
					} else {
						if (request.getParameter("pss").length() < 6) {
							errores.add(
									"Error, la contrase&ntilda;a repetida debe de tener una logitud minima de 6 caracteres");
						}
						try {
							Integer.parseInt(request.getParameter("pss"));
							errores.add("Error, la contrase&ntilda;a repetida no puede ser un numero");
						} catch (NumberFormatException e) {

						}
					}
					if (!request.getParameter("pss0").equals(request.getParameter("pss"))) {
						errores.add("Error, las contrase&nilde;as no coinciden");
					} 
				}
				if(errores.size() != 0) {
					request.setAttribute("errores",errores);
					request.getRequestDispatcher("/errores.jsp").forward(request, response);
					return ;
				}
				Usuario usr = new Usuario();
				boolean exitoso = false;
				String apellido = request.getParameter("apel");
				if(inmovil == false) {
					exitoso = usr.actualizaUsuario(request.getParameter("nome"),request.getParameter("apellidos"), request.getParameter("pss"), (int) sesion.getAttribute("cod_usr"));
				} else {
					exitoso = usr.actualizaUsuarioNoPassword(request.getParameter("nome"),request.getParameter("apellidos"), (int) sesion.getAttribute("cod_usr"));
				}
				if(exitoso == false) {
					errores.add("Error inesperado en la actualizacion");
				}
				if(errores.size() != 0) {
					request.setAttribute("errores",errores);
					request.getRequestDispatcher("/errores.jsp").forward(request, response);
					return ;
				} else {
					sesion.removeAttribute("nome");
					sesion.removeAttribute("apellidos");
					sesion.setAttribute("nombre",request.getParameter("nome"));
					sesion.setAttribute("apellidos",request.getParameter("apellidos"));
					doGet(request,response);
				}
			} else {
				response.sendRedirect("/OGgenius/Bienvenida");
			}
		}
	}

}
