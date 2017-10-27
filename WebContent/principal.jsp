<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/<%= session.getAttribute("tema") %>.css"/>
</head>
<body>
	<div class="cabecera">
		<img src="<%= request.getAttribute("img") %>" width="128" heigth="128"/>	Bienvenido/a:<%= session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos") %>	Ir a la nave nodriza&nbsp;<input type="image" src="recursos/emergencia.jpg" onclick="window.open('/OGgenius/Logout','_self')" width="128" heigth="128"/>
	</div>
	<div class="area51">
		<table>
			<tr>
				<th>Titulo</th>
				<th>Album</th>
				<th>a&ntilde;o</th>
				<th>duracion</th>
				<th>Play</th>
			</tr>
			<%
				Object lascanciones = request.getAttribute("canciones");
				ArrayList<Cancion> canciones = (ArrayList<Cancion>) lascanciones;
				Object lasautorias = request.getAttribute("autorias");
				HashMap<Integer, Autoria> autorias = (HashMap<Integer, Autoria>) lasautorias;
				Object losartistas = request.getAttribute("artista");
				HashMap<Integer, Artista> artistas = (HashMap<Integer, Artista>) losartistas;
				Object losalbunes = request.getAttribute("albunes");
				HashMap<Integer, Album> albunes = (HashMap<Integer, Album>) losalbunes;
				for (int i = 0; i < canciones.size(); i++) {
			%>
			<tr>
				<td><%=canciones.get(i).getTitulo()%></td>
				<td><%=albunes.get(autorias.get(canciones.get(i).getAutoria()).getCod_album()).getNombre()%></td>
				<td><%=canciones.get(i).getAno()%></td>
				<td><%=canciones.get(i).getDuracion()%></td>
				<td><a href="/OGgenius/Reproductor?id=<%= canciones.get(i).getCod_can()  %>">Reproducir</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>