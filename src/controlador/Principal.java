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

import modelo.Album;
import modelo.Artista;
import modelo.Autoria;
import modelo.Cancion;

/**
 * Servlet implementation class Principal
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			String rutaImagen = "perfiles/default.jpg";
			String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
			File tieneImagen = new File(directorio + sesion.getAttribute("cod_usr") + ".jpg");
			System.out.println(tieneImagen.getCanonicalFile());
			if (tieneImagen.exists()) {
				rutaImagen = "perfiles/" + sesion.getAttribute("cod_usr") + ".jpg";
			}
			System.out.println(sesion.getAttribute("cod_usr"));
			request.setAttribute("img", rutaImagen);
			Cancion can = new Cancion();
			Autoria auto = new Autoria();
			Artista artist = new Artista();
			Album album = new Album();
			ArrayList <Cancion> elcanto = can.getAllCanciones();
			ArrayList <Integer> paraBorrar = new ArrayList<Integer>();
			if(elcanto != null) {
				for(int i=0;i<elcanto.size();i++) {
					Autoria latabla = auto.getAllAutorias().get(elcanto.get(i).getAutoria());
					if(latabla == null) {
						paraBorrar.add(i);
					} else {
						Album elAlbum = album.getAllAlbunes().get(latabla.getCod_album());
						if(elAlbum == null) {
							paraBorrar.add(i);
						} else {
							Artista thaModafoca = artist.getAllArtistas().get(latabla.getCod_modafoca());
							if(thaModafoca == null) {
								paraBorrar.add(i);
							}
						}
					}
				}
			}
			for(int i=0;i<paraBorrar.size();i++) {
				elcanto.remove(paraBorrar.get(i));
			}
			request.setAttribute("canciones", elcanto);
			request.setAttribute("autorias", auto.getAllAutorias());
			request.setAttribute("albunes", album.getAllAlbunes());
			request.setAttribute("artista", artist.getAllArtistas());
			request.getRequestDispatcher("/principal.jsp").forward(request, response);
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
