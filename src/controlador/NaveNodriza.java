package controlador;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class NaveNodriza
 */
@WebServlet("/NaveNodriza")
public class NaveNodriza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaveNodriza() {
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
			Usuario usr = new Usuario();
			usr = usr.getUserdata((int) sesion.getAttribute("cod_usr"));
			if(usr == null) {
				sesion.invalidate();
				response.sendRedirect("/OGgenius/Bienvenida");
				return ;
			}
			String rutaImagen = "perfiles/default.jpg";
			String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
			File tieneImagen = new File(directorio + sesion.getAttribute("cod_usr") + ".jpg");
			System.out.println(tieneImagen.getCanonicalFile());
			if (tieneImagen.exists()) {
				rutaImagen = "perfiles/" + sesion.getAttribute("cod_usr") + ".jpg";
			}
			System.out.println(sesion.getAttribute("cod_usr"));
			request.setAttribute("img", rutaImagen);
			request.setAttribute("usuario", usr);
			request.getRequestDispatcher("/navenodriza.jsp").forward(request, response);
		} else {
			response.sendRedirect("/OGgenius/Bienvenida");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			sesion.setAttribute("rko",1);
			sesion.setAttribute("frame",1);
			Registro register = new Registro();
			register.doPost(request, response);
		} else {
			response.sendRedirect("/OGgenius/Bienvenida");
		}
	}

}
