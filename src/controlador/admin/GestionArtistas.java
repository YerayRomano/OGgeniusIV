package controlador.admin;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import modelo.Artista;
import modelo.Tema;

/**
 * Servlet implementation class GestionArtistas
 */
@WebServlet("/GestionArtistas")
public class GestionArtistas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionArtistas() {
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
				if (request.getParameter("frame") == null) {
					errores.add("error, no se ha mandado frame");
				} else {
					if (request.getParameter("frame") == "") {
						errores.add("error, el frame esta vacio");
					} else {
						try {
							int frame = Integer.parseInt(request.getParameter("frame"));
							switch (frame) {
							case 1:
								request.setAttribute("errores", errores);
								request.getRequestDispatcher("/addArtista.jsp").forward(request, response);
							break;
							case 2:
								if (request.getParameter("id") == null) {
									errores.add("error, no se ha especificado un usuario");
								} else {
									try {
										Artista aof = new Artista();
										int frio = Integer.parseInt(request.getParameter("id"));
										aof = aof.getArtista(Integer.parseInt(request.getParameter("id")));
										Tema temi = new Tema();
										if (aof == null) {
											errores.add("error, el usuario pedido no existe");
										} else {
											System.out.println("joey badass");
											request.setAttribute("aof",aof);
											sesion.setAttribute("noma",aof.getNombre());
											request.getRequestDispatcher("/modArtista.jsp").include(request, response);
											return ;
										}
									} catch (NumberFormatException e) {
										errores.add("error, neiga");
									}
								}
							break;
							default:
								errores.add("error, frame no valido");
								break;
							}
						} catch (NumberFormatException e) {
							errores.add("error, el frame debe de ser numerico");
						}
					}
					if (errores.size() != 0) {
						request.setAttribute("errores", errores);
						request.getRequestDispatcher("/errores.jsp").forward(request, response);
						return;
					}
				}
				if (request.getParameter("del") != null) {
					flag = true;
					if (request.getParameter("del") == "") {
						errores.add("error, no se ha mandado el artista a borrar");
					} else {
						Artista aof = new Artista();
						try {
							aof = aof.getArtista(Integer.parseInt(request.getParameter("del")));
							if (aof == null) {
								errores.add("Error, el artista que intentas borrar no existe");
							} else {
								boolean echado = aof.borrarArtista(aof.getCod_modafoca());
								if (!echado) {
									errores.add("Error de borrado, intentalo mas tarde");
								}
							}
						} catch (NumberFormatException e) {
							errores.add("Error, el codigo de artista debe de ser numerico");
						}
					}
				}
				Artista artista = new Artista();
				HashMap<Integer, Artista> artistas = artista.getAllArtistas();
				request.setAttribute("artistas", artistas);
				request.setAttribute("errores", errores);
				if (flag == false)
					request.getRequestDispatcher("/artistas.jsp").include(request, response);
				else
					request.getRequestDispatcher("/listaaof.jsp").forward(request, response);
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
		boolean flag = false;
		ArrayList<String> errores = new ArrayList<String>();
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null
				&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			if (!sesion.getAttribute("rol").equals("admin")) {
				response.sendError(404);
				return;
			}
			if (request.getParameter("frame") == null) {
				errores.add("error, no se ha mandado frame");
			} else {
				if (request.getParameter("frame") == "") {
					errores.add("error, el frame esta vacio");
				} else {
					try {
						int frame = Integer.parseInt(request.getParameter("frame"));
						switch (frame) {
						case 1:
							if (request.getParameter("nome") == null) {
								errores.add("error, no se ha mandado el nombre");
							}
							if (request.getParameter("ano") == null) {
								errores.add("error, no se ha mandado el nombre");
							}
							if (request.getParameter("veto") == null) {
								errores.add("error, no se ha mandado el nombre");
							}
							if (request.getParameter("descripta") == null) {
								errores.add("error, no se ha mandado la descipcion");
							}
							if (errores.size() == 0) {
								if (request.getParameter("nome") == "") {
									errores.add("error, has mandado un nombre vacio");
								} else {
									Artista aof = new Artista();
									boolean existe = aof.artistaExiste(request.getParameter("nome"));
									if (existe) {
										errores.add("error, ya hay un artista");
									}
								}
								if (request.getParameter("ano") == "") {
									errores.add("error, no has mandado el a&ntilde;o");
								} else {
									try {
										int anual = Integer.parseInt(request.getParameter("ano"));
										if (anual < 1950 || anual > 2000) {
											errores.add("error, el a&ntilde;o debe de estar entre 1950 y 2000");
										}
									} catch (Exception e) {
										errores.add("error, el a&ntilde;o no es numerico");
									}
								}
								if (request.getParameter("veto") == "") {
									errores.add("error, no has mandado veto");
								} else {
									int anual = Integer.parseInt(request.getParameter("ano"));
									if (anual < 1950 || anual > 2000) {
										errores.add("error, el veto solo puede ser si o no (0 o 1)");
									}

								}
								Part fotoPart = request.getPart("imagen");
								boolean subida = false;
								int fotoSize = 0;
								try {
									fotoSize = (int) fotoPart.getSize();
									if(fotoSize > 0) {
										subida = true;
										if(fotoSize > (1024*2048)) {
											errores.add("error, no se puede subir imagenes superiores a 2mb");
										}
										String tipo = fotoPart.getContentType().split("/")[0];
										if(!tipo.equals("image")) {
											errores.add("error, intentas subir un fichero que no es una imagen");
										}
									}
								} catch (NullPointerException e) {
									errores.add("error, no has mandado la foto");
								}
								if (errores.size() == 0) {
									Artista aof = new Artista();
									boolean insertado = aof.addArtista(request.getParameter("nome"),
											Integer.parseInt(request.getParameter("ano")),
											Integer.parseInt(request.getParameter("veto")),
											request.getParameter("descripta"));
									if (!insertado) {
										errores.add("error de guardado, prueba mas tarde");
									} else {
										if(subida == true) {
											Artista thaModaFoca = aof.getArtista(request.getParameter("nome"));
											byte[] foto = new byte[fotoSize];
											try (DataInputStream dis = new DataInputStream(fotoPart.getInputStream())) {
												dis.readFully(foto);
												// InputStream fileContent = dis;
												File file = new File("C:\\Users\\alumno_t\\eclipse-workspace\\OGgenius\\WebContent\\modafocas\\", thaModaFoca.getCod_modafoca() + ".jpg");
												FileOutputStream salida = new FileOutputStream(file);
												salida.write(foto);
												salida.close();
												request.getRequestDispatcher("/artistas.jsp").include(request, response);
											}
										}
									}
								}
							}
							break;
						case 2:
							if (request.getParameter("nome") == null) {
								errores.add("error, no se ha mandado el nombre");
							}
							if (request.getParameter("ano") == null) {
								errores.add("error, no se ha mandado el nombre");
							}
							if (request.getParameter("veto") == null) {
								errores.add("error, no se ha mandado el nombre");
							}
							if (request.getParameter("descripta") == null) {
								errores.add("error, no se ha mandado la descipcion");
							}
							if(sesion.getAttribute("noma") == null) {
								errores.add("no intentes inyectarnos");
							}
							if (errores.size() == 0) {
								if (request.getParameter("nome") == "") {
									errores.add("error, has mandado un nombre vacio");
								} else {
									Artista aof = new Artista();
									String noma = (String) sesion.getAttribute("noma");
									if (!noma.equals(request.getParameter("nome"))) {
										boolean existe = aof.artistaExiste(request.getParameter("nome"));
										if (existe) {
											errores.add("error, ya hay un artista");
										} 
									}
								}
								if (request.getParameter("ano") == "") {
									errores.add("error, no has mandado el a&ntilde;o");
								} else {
									try {
										int anual = Integer.parseInt(request.getParameter("ano"));
										if (anual < 1950 || anual > 2000) {
											errores.add("error, el a&ntilde;o debe de estar entre 1950 y 2000");
										}
									} catch (Exception e) {
										errores.add("error, el a&ntilde;o no es numerico");
									}
								}
								if (request.getParameter("veto") == "") {
									errores.add("error, no has mandado veto");
								} else {
									int anual = Integer.parseInt(request.getParameter("ano"));
									if (anual < 1950 || anual > 2000) {
										errores.add("error, el veto solo puede ser si o no (0 o 1)");
									}

								}
								Part fotoPart = request.getPart("imagen");
								boolean subida = false;
								int fotoSize = 0;
								try {
									fotoSize = (int) fotoPart.getSize();
									if(fotoSize > 0) {
										subida = true;
										if(fotoSize > (1024*2048)) {
											errores.add("error, no se puede subir imagenes superiores a 2mb");
										}
										String tipo = fotoPart.getContentType().split("/")[0];
										if(!tipo.equals("image")) {
											errores.add("error, intentas subir un fichero que no es una imagen");
										}
									}
								} catch (NullPointerException e) {
									errores.add("error, no has mandado la foto");
								}
								if (errores.size() == 0) {
									Artista aof = new Artista();
									boolean insertado = aof.modArtista(request.getParameter("nome"),
											Integer.parseInt(request.getParameter("ano")),
											Integer.parseInt(request.getParameter("veto")),
											request.getParameter("descripta"));
									if (!insertado) {
										errores.add("error de guardado, prueba mas tarde");
									} else {
										if(subida == true) {
											Artista thaModaFoca = aof.getArtista(request.getParameter("nome"));
											byte[] foto = new byte[fotoSize];
											try (DataInputStream dis = new DataInputStream(fotoPart.getInputStream())) {
												dis.readFully(foto);
												// InputStream fileContent = dis;
												File file = new File("C:\\Users\\alumno_t\\eclipse-workspace\\OGgenius\\WebContent\\modafocas\\", thaModaFoca.getCod_modafoca() + ".jpg");
												FileOutputStream salida = new FileOutputStream(file);
												salida.write(foto);
												salida.close();
												request.getRequestDispatcher("/artistas.jsp").include(request, response);
											}
										}
									}
								}
							}
						break;
						default:
							errores.add("error, frame no valido");
							break;
						}
					} catch (NumberFormatException e) {
						errores.add("error, el frame debe de ser numerico");
					}
					if(errores.size() != 0) {
						request.setAttribute("errores", errores);
						request.getRequestDispatcher("/errores.jsp").forward(request, response);
					}
				}
			}
		} else {
			response.sendRedirect("/OGgenius/Bienvenida");
		}
	}
}
