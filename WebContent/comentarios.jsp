<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*,auxiliar.Snippets,java.io.File"%>
	<%
	Object erros = request.getAttribute("errores");
	ArrayList <String> errores = (ArrayList <String>) erros;
	Object juicyj = request.getAttribute("comentarios");
	ArrayList<Comentario> comentarios = (ArrayList<Comentario>) juicyj;
	Object youngBuck = request.getAttribute("usuarios");
	ArrayList<Usuario> losUsuarios = (ArrayList <Usuario>) youngBuck;
	Usuario usr = new Usuario();
	String rutaImagen = "perfiles/default.jpg";
	String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
	File tieneImagen = new File(directorio +session.getAttribute("cod_usr") + ".jpg");
	if (tieneImagen.exists()) {
		rutaImagen = "perfiles/" + session.getAttribute("cod_usr") + ".jpg";
	}
	%>
<p><img src="<%= rutaImagen %>" width="64" heigth="64"/> <%= session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos") + " ("+session.getAttribute("mail")+")" %><br/>
		<form action="/OGgenius/Reproductor" method="post">
			¿Que opinas?:<br/>
			<textarea name="texto"></textarea>
			<input type="submit" value="postear"/>
		</form>
		<%
			if (comentarios == null) {
		%>
		<h6>Nadie ha comentado nada, se el primero en dar tu opinion</h6>
		<%
			} else {
				if (comentarios.size() == 0) {
		%>
		<h6>Nadie ha comentado nada, se el primero en dar tu opinion</h6>
		<%
				} else {
					for(int i = (comentarios.size() -1); i > 0; i--) {
						rutaImagen = "perfiles/default.jpg";
						directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
						tieneImagen = new File(directorio + losUsuarios.get(i).getCod_usr() + ".jpg");
						if (tieneImagen.exists()) {
							rutaImagen = "perfiles/" + losUsuarios.get(i).getCod_usr() + ".jpg";
						}
		%>
		<p><img src="<%= rutaImagen %>" width="64" heigth="64"/> <%= losUsuarios.get(i).getNombre() + "\t" + losUsuarios.get(i).getApellidos() + " ("+losUsuarios.get(i).getMail()+")" %><br/><%= comentarios.get(i).getContenido() %></p>
		<%
					}
				}
			}
		%>