package controlador;

import java.io.File;
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
import modelo.Autoria;
import modelo.Cancion;
import modelo.Comentario;
import modelo.GustaNoGusta;
import modelo.Momento;
import modelo.Usuario;
import modelo.Comentado;

/**
 * Servlet implementation class Reproductor
 */
@WebServlet("/Reproductor")
public class Reproductor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reproductor() {
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
			String rutaImagen = "perfiles/default.jpg";
			String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
			File tieneImagen = new File(directorio + sesion.getAttribute("cod_usr") + ".jpg");
			System.out.println(tieneImagen.getCanonicalFile());
			if (tieneImagen.exists()) {
				rutaImagen = "perfiles/" + sesion.getAttribute("cod_usr") + ".jpg";
			}
			request.setAttribute("img", rutaImagen);
			Cancion canto = new Cancion();
			ArrayList<String> errores = new ArrayList<String>();
			if (request.getParameter("id") == null) {
				errores.add("error,no se ha mandado un id");
			} else {
				if (request.getParameter("id") == "") {
					errores.add("error,se ha mandado un id vacio");
				} else {
					try {
						boolean existe = canto.cancionExiste(Integer.parseInt(request.getParameter("id")));
						if (!existe) {
							errores.add("error, la cancion no existe");
						} else {
							int canciony = Integer.parseInt(request.getParameter("id"));
							Cancion canci = canto.getCancion(canciony);
							if (canci == null) {
								errores.add("error interno, no se ha podido cargar la cancion");
							} else {
								Autoria auto = new Autoria();
								ArrayList<Autoria> losModafocas = auto.getAutoria(canci.getAutoria());
								if (losModafocas == null) {
									errores.add("error interno, no se ha podido cargar la cancion");
								} else {
									if (losModafocas.size() == 0) {
										errores.add("error interno, no se ha podido cargar la cancion");
									} else {
										auto = losModafocas.get(0);
										Artista aof = new Artista();
										ArrayList<Artista> losCulpables = new ArrayList<Artista>();
										boolean error = false;
										for (int i = 0; i < losModafocas.size(); i++) {
											Artista losPeligrosDeUnNegro = aof
													.getArtista(losModafocas.get(i).getCod_modafoca());
											if (losPeligrosDeUnNegro != null) {
												losCulpables.add(losPeligrosDeUnNegro);
											}
										}
										if (losCulpables.size() == 0) {
											errores.add("error interno, no se ha podido cargar la cancion");
										}
										if (error == true) {
											errores.add("error interno, no se ha podido cargar la cancion");
										} else {
											Album album = new Album();
											Album albuma = album.getAlbum(auto.getCod_album());
											if (albuma == null) {
												errores.add("error interno, no se ha podido cargar la cancion");
											} else {
												Comentario comen = new Comentario();
												Comentado comens = new Comentado();
												Usuario usr = new Usuario();
												ArrayList<Comentario> loscomentarios = new ArrayList<Comentario>();
												ArrayList<Usuario> losUsuarios = new ArrayList<Usuario>();
												HashMap<Integer, Comentario> comentarios = comen.getAllComentarios();
												ArrayList<Comentado> comentados = comens.getAllComentadosCancion(
														Integer.parseInt(request.getParameter("id")));
												if (comentados != null) {
													if (comentados.size() != 0) {
														for (int i = 0; i < comentados.size(); i++) {
															Comentario comida = comentarios
																	.get(comentados.get(i).getCod_comen());
															if (comida != null) {
																Usuario laVoz = usr
																		.getUserdata(comentados.get(i).getCod_usr());
																if (laVoz != null) {
																	loscomentarios.add(comida);
																	losUsuarios.add(laVoz);
																}
															}
														}
													}
												}
												GustaNoGusta opina = new GustaNoGusta();
												ArrayList<GustaNoGusta> opiniones = opina.getAllOpinionesCancion(
														(Integer.parseInt(request.getParameter("id"))));
												Integer[] tipos = { 0, 0 };
												if (opiniones != null) {
													for (int i = 0; i < opiniones.size(); i++) {
														if (opiniones.get(i).getTipo() > -1
																&& opiniones.get(i).getTipo() < 2) {
															tipos[opiniones.get(i).getTipo()]++;
														}
													}
												}
												rutaImagen = "caratulas/default.jpg";
												directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/caratulas/";
												tieneImagen = new File(directorio + albuma.getCod_album() + ".jpg");
												System.out.println(tieneImagen.getCanonicalFile());
												if (tieneImagen.exists()) {
													rutaImagen = "caratulas/" + albuma.getCod_album() + ".jpg";
												}
												if (loscomentarios.size() != losUsuarios.size()) {
													loscomentarios = null;
													losUsuarios = null;
												}
												request.setAttribute("caratula", rutaImagen);
												request.setAttribute("cancion", canci);
												request.setAttribute("artistas", losCulpables);
												request.setAttribute("album", albuma);
												request.setAttribute("comentarios", loscomentarios);
												request.setAttribute("usuarios", losUsuarios);
												sesion.setAttribute("cod_can", canci.getCod_can());
												request.setAttribute("opiniones", tipos);
											}
										}
									}
								}
							}
						}
					} catch (NumberFormatException e) {
						errores.add("error, el id debe de ser numerico");
					}
				}
			}
			if (errores.size() != 0) {
				request.setAttribute("errores", errores);
				request.getRequestDispatcher("/errores.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/reproductor.jsp").forward(request, response);
			}
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
		ArrayList<String> errores = new ArrayList<String>();
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null
				&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			if (sesion.getAttribute("cod_can") != null) {
				try {
					int cancion = (int) sesion.getAttribute("cod_can");
					Cancion can = new Cancion();
					can = can.getCancion(cancion);
					if (can != null) {
						if (request.getParameter("texto") != null) {
							System.out.println("neiga");
							Comentario comen = new Comentario();
							boolean insertado = comen.insertarComentario(request.getParameter("texto"));
							if (insertado == false) {
								errores.add("error interno, no se ha podido postear el comentario");
							} else {
								int theLastBlade = comen.obtenerUltimoComentario();
								if (theLastBlade == -1) {
									errores.add("error interno, no se ha podido postear el comentario");
								} else {
									Comentado comer = new Comentado();
									Object usuario = sesion.getAttribute("cod_usr");
									Object canci = sesion.getAttribute("cod_can");
									Integer usr = (Integer) usuario;
									Integer canciony = (Integer) canci;
									comer.insertarComentado((int) usr, theLastBlade, (int) canciony);
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Comentario comen = new Comentario();
			Comentado comens = new Comentado();
			Usuario usr = new Usuario();
			ArrayList<Comentario> loscomentarios = new ArrayList<Comentario>();
			ArrayList<Usuario> losUsuarios = new ArrayList<Usuario>();

			HashMap<Integer, Comentario> comentarios = comen.getAllComentarios();
			ArrayList<Comentado> comentados = comens.getAllComentadosCancion((int) sesion.getAttribute("cod_can"));
			if (comentados != null) {
				if (comentados.size() != 0) {
					for (int i = 0; i < comentados.size(); i++) {
						Comentario comida = comentarios.get(comentados.get(i).getCod_comen());
						if (comida != null) {
							Usuario laVoz = usr.getUserdata(comentados.get(i).getCod_usr());
							if (laVoz != null) {
								loscomentarios.add(comida);
								losUsuarios.add(laVoz);
							}
						}
					}
				}
			}
			Momento momento = new Momento();
			ArrayList<Momento> momentos = null;
			//System.out.println(momentos.size());
			request.setAttribute("errores", errores);
			request.setAttribute("comentarios", loscomentarios);
			request.setAttribute("usuarios", losUsuarios);
			request.getRequestDispatcher("/comentarios.jsp").forward(request, response);
		} else {
			response.sendRedirect("/OGGçgenius/Principal");
		}
	}

}
