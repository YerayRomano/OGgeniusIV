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
 * Servlet implementation class Regsitro
 */
@WebServlet("/Regsitro")
public class Regsitro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regsitro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("nombre") != null && sesion.getAttribute("cod_usr") != null && sesion.getAttribute("tema") != null) {
			int frame = (Integer) sesion.getAttribute("frame");
			switch(frame) {
				case 0:
					
				break;
				case 1:
					
				break;
				case 2:
					
				break;
				default:
					
				break;
			}
		} else {
			request.getRequestDispatcher("/Registro.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		ArrayList<String> errores = new ArrayList<String>();
		if(sesion.getAttribute("nombre") != null && sesion.getAttribute("cod_usr") != null && sesion.getAttribute("tema") != null) {
			int frame = (Integer) sesion.getAttribute("frame");
			switch(frame) {
				case 0:
					
				break;
				case 1:
					
				break;
				case 2:
					
				break;
				default:
					
				break;
			}
		} else {
			if(request.getParameter("nome") == null) {
				errores.add("Error, no se ha mandado el campo del nombre");
			}
			if(request.getParameter("apel") == null) {
				errores.add("Error, no se ha mandado el campo del apellido");
			}
			if(request.getParameter("mail") == null) {
				errores.add("Error, no se ha mandado el campo del mail");
			}
			if(request.getParameter("pss") == null) {
				errores.add("Error, no se ha mandado el campo de la contrase&ntilde;a");
			}
			if(request.getParameter("pss0") == null) {
				errores.add("Error, no se ha mandado la contrase&ntilde;a repetida");
			}
			if(errores.size() == 0) {
				if(request.getParameter("nome") == "") {
					errores.add("Error, el nombre no puede estar vacio");
				} else {
					try {
						Integer.parseInt(request.getParameter("nome"));
						errores.add("Error, tu nombre no puede ser un numero");
					} catch (NumberFormatException e) {
						
					}
				}
				if(request.getParameter("apel") == "") {
					errores.add("Error, el apellido no puede estar vacio");
				} else {
					try {
						Integer.parseInt(request.getParameter("apel"));
						errores.add("Error, tu apellido no puede ser un numero");
					} catch (NumberFormatException e) {
						
					}
				}
				if(request.getParameter("mail") == "") {
					errores.add("Error, no has puesto tu correo");
				} else {
					if(request.getParameter("mail").split("@").length != 2) {
						errores.add("Error, formato de correo no valido");
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
				if(request.getParameter("pss0") == "") {
					errores.add("Error, contrase&nilde;a repetida vacia");
				} else {
					if(request.getParameter("pss").length() < 6) {
						errores.add("Error, la contrase&ntilda;a repetida debe de tener una logitud minima de 6 caracteres");
					}
					try {
						Integer.parseInt(request.getParameter("pss"));
						errores.add("Error, la contrase&ntilda;a repetida no puede ser un numero");
					} catch (NumberFormatException e) {
						
					}
				}
				if(!request.getParameter("pss0").equals(request.getParameter("pss"))) {
					errores.add("Error, las contrase&nilde;as no coinciden");
				}
				if(errores.size() == 0) {
					Usuario usr = new Usuario(0,request.getParameter("nome"),request.getParameter("apel"),request.getParameter("mail"),request.getParameter("pss"),1,0);
					boolean efectivo = usr.insertarUsuario(usr);
					if(!efectivo) {
						errores.add("error interno al insertar el usuario, pruebe mas tarde");
						request.setAttribute("errores",errores);
						request.getRequestDispatcher("/errores.jsp").forward(request, response);
					} else {
						Usuario tusdatos = usr.getUserdata(request.getParameter("mail"));
						sesion.setAttribute("nombre", tusdatos.getNombre());
						sesion.setAttribute("apellidos", tusdatos.getApellidos());
						sesion.setAttribute("cod_usr", tusdatos.getCod_usr());
						sesion.setAttribute("tema", tusdatos.getTema());
						sesion.setAttribute("frame",0);
						doGet(request,response);
					}
				} else {
					request.setAttribute("errores",errores);
					request.getRequestDispatcher("/errores.jsp").forward(request, response);
				}
			}
		}
	}

}
