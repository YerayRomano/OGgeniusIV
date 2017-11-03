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

import modelo.Album;
import modelo.Artista;

/**
 * Servlet implementation class GestionAlbunes
 */
@WebServlet("/GestionAlbunes")
public class GestionAlbunes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionAlbunes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		boolean flag = false;
		ArrayList<String> errores = new ArrayList<String>();
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
				if (request.getParameter("del") != null) {
					flag = true;
					if (request.getParameter("del") == "") {
						errores.add("error, no se ha mandado el artista a borrar");
					} else {
						Album albuma = new Album();
						try {
							albuma = albuma.getAlbum(Integer.parseInt(request.getParameter("del")));
							if (albuma == null) {
								errores.add("Error, el artista que intentas borrar no existe");
							} else {
								boolean echado = albuma.borrarAlbum(albuma.getCod_album());
								if (!echado) {
									errores.add("Error de borrado, intentalo mas tarde");
								}
							}
						} catch (NumberFormatException e) {
							errores.add("Error, el codigo de artista debe de ser numerico");
						}
					}
				}
				Album albuma = new Album();
				HashMap<Integer, Album> albunes = albuma.getAllAlbunes();
				request.setAttribute("albunes",albunes);
				if(flag == false)
					request.getRequestDispatcher("/albunes.jsp").forward(request, response);
				else
					request.getRequestDispatcher("/losalbunes.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("/OGgenius/Bienvenida");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
