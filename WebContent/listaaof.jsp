<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*,java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script>
	function migo(aof) {
		$.ajax({
			url : '/OGgenius/GestionArtistas?del='+aof,
			type : 'get',
			dataType : 'html', //expect return data as html from server
			data : '',
			success : function(response, textStatus, jqXHR) {
				$('#lista').html(response);
				return false;//select the id and put the response in the html
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error(s):' + textStatus, errorThrown);
			}
		});
		return false;
	}
	</script>
<title>Insert title here</title>
</head>
<body>
	<h2>Gestion de artitas</h2>
				<%
					Object losArtistas = request.getAttribute("artistas");
					HashMap<Integer,Artista> artistas = (HashMap<Integer,Artista>) losArtistas;
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
							String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/modafocas/";
							File tieneImagen = new File(directorio + artistas.get(key).getCod_modafoca() + ".jpg");
							if (tieneImagen.exists()) {
								rutaImagen = "modafocas/" + artistas.get(key).getCod_modafoca() + ".jpg";
							}
							%>
							<img src="<%= rutaImagen %>" alt="<%= artistas.get(key).getNombre()  %>" width="128" heigth="128"/>
						</th>
						<td>&nbsp;<%= artistas.get(key).getNombre() %></th>
						<td>&nbsp;<%= artistas.get(key).getAno() %></th>
						<td>&nbsp;<%= artistas.get(key).getVetado() %></th>
						<td><form action="" method="get"><input type="hidden" name="frame" value="2"><input type="hidden" name="id" value="<%= artistas.get(key).getCod_modafoca() %>"/><input type="submit" value="modifcar"/></form></td>
						<td><input type="image" src="recursos/aspa.jpg" alt="borrar" onclick="migo(<%= artistas.get(key).getCod_modafoca() %>)"/></td>
					</tr>
				<%
						}
					}
				%>
				</table>
</body>
</html>