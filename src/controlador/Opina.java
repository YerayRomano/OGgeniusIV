package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Cancion;
import modelo.GustaNoGusta;
import modelo.Usuario;

/**
 * Servlet implementation class Opina
 */
@WebServlet("/Opina")
public class Opina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Opina() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			int cancion = (int) sesion.getAttribute("cod_can");
			Cancion can = new Cancion();
			can = can.getCancion(cancion);
			if (can != null) {
				Integer [] tipos = {0,0};
				if(request.getParameter("op") != null) {
					Usuario usr = new Usuario();
					usr = usr.getUserdata((int) sesion.getAttribute("cod_usr"));
					if(usr == null) {
						response.sendError(404);
						return ;
					}
					try {
						GustaNoGusta lodicho = new GustaNoGusta();
						boolean trampa = lodicho.getHaOpinadoYaAsi(cancion, (int) sesion.getAttribute("cod_usr"),Integer.parseInt(request.getParameter("op")));
						Integer [] inverso = {1,0};
						System.out.println(trampa);
						if (!trampa) {
							boolean restar = lodicho.getHaOpinadoYaAsi(cancion, (int) sesion.getAttribute("cod_usr"),inverso[Integer.parseInt(request.getParameter("op"))]);
							System.out.println(restar);
							if(restar) {
								lodicho.cambioOpinion(cancion,(int) sesion.getAttribute("cod_usr"),Integer.parseInt(request.getParameter("op")));
							} else {
								lodicho.insertarOpinion(cancion, (int) sesion.getAttribute("cod_usr"),Integer.parseInt(request.getParameter("op")));
							}
						} else {
							lodicho.cambioOpinion(cancion,(int) sesion.getAttribute("cod_usr"),Integer.parseInt(request.getParameter("op")));
						}
					
					} catch (NumberFormatException e) {
						
					}
				}
				GustaNoGusta lodicho = new GustaNoGusta();
				ArrayList<GustaNoGusta> opiniones = lodicho.getAllOpinionesCancion(cancion);
				if (opiniones != null) {
					for (int i = 0; i < opiniones.size(); i++) {
						if (opiniones.get(i).getTipo() > -1
								&& opiniones.get(i).getTipo() < 2) {
							tipos[opiniones.get(i).getTipo()]++;
						}
					}
				}
				request.setAttribute("opiniones", tipos);
				request.getRequestDispatcher("/opina.jsp").forward(request, response);
			}
		} else {
			response.sendError(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405);
	}

}
