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
 * Servlet implementation class Beinvenida
 */
@WebServlet("/Bienvenida")
public class Bienvenida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bienvenida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("nombre") != null && sesion.getAttribute("cod_usr") != null && sesion.getAttribute("tema") != null) {
			response.sendRedirect("/principal");
			return ;
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("nombre") != null && sesion.getAttribute("cod_usr") != null && sesion.getAttribute("tema") != null) {
			response.sendRedirect("/principal");
			return ;
		} else {
			ArrayList<String> errores = new ArrayList <String>();
			if(request.getParameter("usr") == null) {
				errores.add("Error, no se ha mandado el campo del mail");
			}
			if(request.getParameter("pss") == null) {
				errores.add("Error, no se ha mandado la contrase&ntilde;a");
			}
			if(errores.size() == 0) {
				if(request.getParameter("usr") == "") {
					errores.add("Error, mail de usuario vacio");
				} else {
					if(request.getParameter("usr").split("@").length != 2) {
						errores.add("Error, formato de correo invalido");
					}
				}
				if(request.getParameter("pss") == "") {
					errores.add("Error, contrase&nilde;a vacia");
				} else {
					if(request.getParameter("pss").length() < 6) {
						errores.add("Error, la contrase&ntilda;a debe de tener una logitud minima de 6 caracteres");
					}
					try {
						Integer.parseInt(request.getParameter("pss"));
						errores.add("Error, la contrase&ntilda;a no puede ser un numero");
					} catch (NumberFormatException e) {
						
					}
				}
				if(errores.size() == 0) {
					Usuario usr = new Usuario();
					boolean loguea = usr.loginUsuario(request.getParameter("usr"), request.getParameter("pss"));
					if(loguea == false) {
						errores.add("Error, usuario o contrase&ntilde;a incorrectos");
					} else {
						Usuario tusdatos = usr.getUserdata(request.getParameter("usr"));
						sesion.setAttribute("nombre", tusdatos.getNombre());
						sesion.setAttribute("apellidos", tusdatos.getApellidos());
						sesion.setAttribute("cod_usr", tusdatos.getCod_usr());
						sesion.setAttribute("tema", tusdatos.getTema());
						response.sendRedirect("/principal");
					}
				}
			}
			if(errores.size() != 0) {
				request.setAttribute("errores",errores);
				request.getRequestDispatcher("/errores.jsp").forward(request, response);
			}
		}
		//doGet(request, response);
	}

}
