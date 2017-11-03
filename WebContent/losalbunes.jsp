<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*,java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Gestion de albunes</h2>
				<%
					Object losAlbunes = request.getAttribute("albunes");
					HashMap<Integer,Album> artistas = (HashMap<Integer,Album>) losAlbunes;
					if(artistas == null) {
				%>
				No hay modafocas que administrar
				<%
					} else {
						java.util.Set<Integer> keys = artistas.keySet();
				%>
				<table>
					<tr>
						<th>foto</th>
						<th>nombre</th>
						<th>ano</th>
						<th>vetado</th>
						<th>modificar</th>
						<th>borrar</th>
					</tr>
				<%
						for(Integer key:keys) {
				%>
					<tr>
						<th>
							<%
							String rutaImagen = "perfiles/default.jpg";
							String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/caratulas/";
							File tieneImagen = new File(directorio + artistas.get(key).getCod_album() + ".jpg");
							if (tieneImagen.exists()) {
								rutaImagen = "caratulas/" + artistas.get(key).getCod_album() + ".jpg";
							}
							%>
							<img src="<%= rutaImagen %>" alt="<%= artistas.get(key).getNombre()  %>" width="128" heigth="128"/>
						</th>
						<td>&nbsp;<%= artistas.get(key).getNombre() %></th>
						<td>&nbsp;<%= artistas.get(key).getAno() %></th>
						<td>&nbsp;<%= artistas.get(key).getVetado() %></th>
						<td><form action="" method="get"><input type="hidden" name="frame" value="2"><input type="hidden" name="id" value="<%= artistas.get(key).getCod_album() %>"/><input type="submit" value="modifcar"/></form></td>
						<td><input type="image" src="recursos/aspa.jpg" alt="borrar" onclick="migo(<%= artistas.get(key).getCod_album() %>)"/></td>
					</tr>
				<%
						}
					}
				%>
				</table>
</body>
</html>