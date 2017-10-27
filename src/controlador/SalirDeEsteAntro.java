package controlador;

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
 * Servlet implementation class SalirDeEsteAntro
 */
@WebServlet("/SalirDeEsteAntro")
public class SalirDeEsteAntro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalirDeEsteAntro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		ArrayList<String> errores = new ArrayList<String>();
		boolean borrado = false;
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			System.out.println("migo");
			if(request.getParameter("op") != null) {
				try {
					int opcion = Integer.parseInt(request.getParameter("op"));
					if(opcion == 1) {
						Usuario usr = new Usuario();
						borrado = usr.borrarUsuario((int) sesion.getAttribute("cod_usr"));
						if(borrado == false) {
							request.setAttribute("errores", errores);
							request.getRequestDispatcher("/errores.jsp").forward(request, response);
							return ;
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
						sesion.invalidate();
					}
				} catch (NumberFormatException e) {
					
				}
			}
			request.getRequestDispatcher("/despedida.jsp").forward(request, response);
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
