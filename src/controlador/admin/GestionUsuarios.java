package controlador.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Tema;
import modelo.Usuario;

/**
 * Servlet implementation class GestionUsuarios
 */
@WebServlet("/GestionUsuarios")
public class GestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionUsuarios() {
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
		ArrayList<String> errores = new ArrayList<String>();
		boolean bandera = false;
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
				Usuario usr = new Usuario();
				if (request.getParameter("frame") != null) {
					if (!request.getParameter("frame").equals("")) {
						try {
							int frame = Integer.parseInt(request.getParameter("frame"));
							String mevoy = "";
							switch (frame) {
							case 1:
								Tema temas = new Tema();
								request.setAttribute("temas", temas.getAllTemas());
								mevoy = "ingreso.jsp";
								break;
							case 2:
								System.out.println(request.getParameter("id"));
								if (request.getParameter("id") == null) {
									errores.add("error, no se ha especificado un usuario");
								} else {
									try {
										int frio = Integer.parseInt(request.getParameter("id"));
										usr = usr.getUserdata(Integer.parseInt(request.getParameter("id")));
										Tema temi = new Tema();
										if (usr == null) {
											errores.add("error, el usuario pedido no existe");
										} else {
											System.out.println("joey badass");
											request.setAttribute("losdatos", usr);
											request.setAttribute("temas", temi.getAllTemas());
											sesion.setAttribute("correo",usr.getMail());
											sesion.setAttribute("usuario",usr.getCod_usr());
											request.getRequestDispatcher("/actualizacion.jsp").include(request, response);
											return ;
										}
									} catch (NumberFormatException e) {
										errores.add("error, neiga");
									}
								}
								if (errores.size() != 0) {
									request.setAttribute("errores", errores);
									request.getRequestDispatcher("/errores.jsp").include(request, response);
									return ;
								}
							}
							if (!mevoy.equals("")) {
								request.getRequestDispatcher(mevoy).include(request, response);
								return;
							}
						} catch (NumberFormatException e) {

						}
					}
				}
				if (request.getParameter("del") != null) {
					try {
						if (request.getParameter("del").equals("")) {
							errores.add("error, el codigo de usuario esta vacio");
						} else {
							int migo = Integer.parseInt(request.getParameter("del"));
							if (migo == (int) sesion.getAttribute("cod_usr")) {
								errores.add("error, no te puedes borrar a ti mismo sanaca!!!");
							}
							Usuario quavo = new Usuario();
							quavo = usr.getUserdata(migo);
							if (quavo != null) {
								boolean borrado = usr.borrarUsuario(quavo.getCod_usr());
								if (borrado == false) {
									errores.add("error de borrado");
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
							} else {
								errores.add("error, el usuario no existe");
							}
						}
					} catch (NumberFormatException e) {
						errores.add("error, el codigo de usuario no es numerico");
					}
					bandera = true;
				}
				ArrayList<Usuario> usuarios = usr.mostrarUsuarios();
				request.setAttribute("usuarios", usuarios);
				if (bandera == false) {
					request.getRequestDispatcher("/adminUsuarios.jsp").include(request, response);
				} else {
					request.getRequestDispatcher("/verUsuarios.jsp").include(request, response);
				}
			}
		} else {
			response.sendRedirect("/OGgenius/Principal");
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
		boolean bandera = false;
		if (sesion.getAttribute("nombre") != null && sesion.getAttribute("apellidos") != null
				&& sesion.getAttribute("mail") != null && sesion.getAttribute("cod_usr") != null
				&& sesion.getAttribute("tema") != null) {
			if (sesion.getAttribute("rol") == null) {

			} else {
				if (!sesion.getAttribute("rol").equals("admin")) {
					response.sendError(403);
					return;
				} else {
					String frame = request.getParameter("frame");
					switch (frame) {
					case "1":
						int tema = 0;
						if (request.getParameter("nome") == null) {
							errores.add("Error, no se ha mandado el campo del nombre");
						}
						if (request.getParameter("apel") == null) {
							errores.add("Error, no se ha mandado el campo del apellido");
						}
						if (request.getParameter("mail") == null) {
							errores.add("Error, no se ha mandado el campo del mail");
						}
						if (request.getParameter("habilitado") == null) {
							errores.add("Error, no se ha mandado el campo de habilitado");
						}
						if (request.getParameter("tema") == null) {
							tema = -1;
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
							if (request.getParameter("apel").equals("")) {
								errores.add("Error, el apellido no puede estar vacio");
							} else {
								try {
									Integer.parseInt(request.getParameter("apel"));
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
									Usuario usr = new Usuario();
									Usuario youngsta = usr.getUserdata((String) sesion.getAttribute("mail"));
									if (youngsta != null) {
										errores.add("Error, el usuario ya existe");
									}
								}
							}
							if (request.getParameter("habilitado") == "") {
								errores.add("Error, el flag de habilitado no puede estar vacio");
							} else {
								try {
									int habilita = Integer.parseInt(request.getParameter("habilitado"));
									if (habilita != 0 && habilita != 1) {
										errores.add("error, el flag de habilitado solo puede ser uno o cero");
									}
								} catch (NumberFormatException e) {
									errores.add("error, el flag de habilitado debe de ser numerico");
								}
							}
							if (request.getParameter("tena") == "") {
								tema = -1;
							} else {
								try {
									tema = Integer.parseInt(request.getParameter("tema"));
									Tema temi = new Tema();
									boolean existe = temi.temaExiste(tema);
									if (!existe) {
										errores.add("error, el codigo de tema pedido no existe");
									}
								} catch (NumberFormatException e) {
									errores.add("error, el codigo de tema debe de ser numerico");
								}
							}
							if (request.getParameter("pss") == "") {
								errores.add("Error, contrase&nilde;a vacia");
							} else {
								if (request.getParameter("pss").length() < 6) {
									errores.add(
											"Error, la contrase&ntilda;a debe de tener una logitud minima de 6 caracteres");
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
							if (errores.size() != 0) {
								request.setAttribute("errores", errores);
								request.getRequestDispatcher("/errores.jsp").forward(request, response);
							} else {
								if (tema == -1) {
									tema = 0;
								}
								Usuario usr = new Usuario(0, request.getParameter("nome"), request.getParameter("apel"),
										request.getParameter("mail"), request.getParameter("pss"),
										Integer.parseInt(request.getParameter("habilitado")), tema, "cliente");
								boolean efectivo = usr.insertarUsuario(usr);
								if (efectivo == false) {
									errores.add("error, no se ha podido insertar al usuario correctamente");
								}
								if (errores.size() != 0) {
									request.setAttribute("errores", errores);
									request.getRequestDispatcher("/errores.jsp").forward(request, response);
									return;
								}
								response.sendRedirect("/OGgenius/GestionUsuarios");
							}
						}
						break;
					case "2":
						tema = 0;
						if (request.getParameter("nome") == null) {
							errores.add("Error, no se ha mandado el campo del nombre");
						}
						if (request.getParameter("apel") == null) {
							errores.add("Error, no se ha mandado el campo del apellido");
						}
						String neiga = request.getParameter("apellidos");
						if (request.getParameter("mail") == null) {
							errores.add("Error, no se ha mandado el campo del mail");
						}
						if (request.getParameter("habilitado") == null) {
							errores.add("Error, no se ha mandado el campo de habilitado");
						}
						if (request.getParameter("tema") == null) {
							tema = -1;
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
									if (!request.getParameter("mail").equals((String) sesion.getAttribute("correo"))) {
										errores.add("Error, no te puedes cambiar el correo");
									}
								}
							}
							if (request.getParameter("habilitado") == "") {
								errores.add("Error, el flag de habilitado no puede estar vacio");
							} else {
								try {
									int habilita = Integer.parseInt(request.getParameter("habilitado"));
									if (habilita != 0 && habilita != 1) {
										errores.add("error, el flag de habilitado solo puede ser uno o cero");
									}
								} catch (NumberFormatException e) {
									errores.add("error, el flag de habilitado debe de ser numerico");
								}
							}
							if (request.getParameter("tena") == "") {
								tema = -1;
							} else {
								try {
									tema = Integer.parseInt(request.getParameter("tema"));
									Tema temi = new Tema();
									boolean existe = temi.temaExiste(tema);
									if (!existe) {
										errores.add("error, el codigo de tema pedido no existe");
									}
								} catch (NumberFormatException e) {
									errores.add("error, el codigo de tema debe de ser numerico");
								}
							}
							boolean inmovil = false;
							if (request.getParameter("pss").equals("") && request.getParameter("pss0").equals("")) {
								inmovil = true;
							}
							if (!inmovil) {
								if (request.getParameter("pss") == "") {
									errores.add("Error, contrase&nilde;a vacia");
								} else {
									if (request.getParameter("pss").length() < 6) {
										errores.add(
												"Error, la contrase&ntilda;a debe de tener una logitud minima de 6 caracteres");
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
							if (errores.size() != 0) {
								request.setAttribute("errores", errores);
								request.getRequestDispatcher("/errores.jsp").forward(request, response);
								return;
							} else {
								if (tema == -1) {
									tema = 0;
								}
								Usuario usr = new Usuario();
								boolean exitoso = false;
								String apellido = request.getParameter("apel");
								if (inmovil == false) {
									exitoso = usr.actualizaUsuario(request.getParameter("nome"),
											request.getParameter("apel"), request.getParameter("pss"),
											(int) sesion.getAttribute("usuario"), Integer.parseInt("tema"),
											Integer.parseInt("habilitado"));
								} else {
									exitoso = usr.actualizaUsuarioNoPassword(request.getParameter("nome"),
											request.getParameter("apel"), (int) sesion.getAttribute("usuario"),
											Integer.parseInt(request.getParameter("tema")), Integer.parseInt(request.getParameter("habilitado")));
								}
								if (exitoso == false) {
									errores.add("Error inesperado en la actualizacion");
								}
								if (errores.size() != 0) {
									request.setAttribute("errores", errores);
									request.getRequestDispatcher("/errores.jsp").forward(request, response);
									return;
								} else {
									response.sendRedirect("/OGgenius/GestionUsuarios?frame=2&id="+sesion.getAttribute("usuario")+"");
								}
							}
							break;
						}
					default:
						errores.add("error, frame no valido modafoca");
						break;
					}
					if (errores.size() != 0) {
						request.setAttribute("errores", errores);
						request.getRequestDispatcher("/errores.jsp").forward(request, response);
					}
				}
			}
		} else {
			response.sendRedirect("/OGgenius/Principal");
		}
	}
}
