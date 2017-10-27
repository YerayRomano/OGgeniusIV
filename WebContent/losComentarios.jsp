<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*,java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function migo(cod_comen) {
		$.ajax({
			url : '/OGgenius/GestionComentarios?del='+cod_comen,
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
	setInterval(function () {
		$.ajax({
			url : '/OGgenius/GestionComentarios?reload=',
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
	},360000);
	</script>
</head>
<body>
	<h2>Lista de comentarios</h2>
	<%
		Object coments = request.getAttribute("comentarios");
		HashMap<Integer, Comentario> comentarios = (HashMap<Integer, Comentario>) coments;
		Object comenteds = request.getAttribute("comentados");
		ArrayList<Comentado> comentados = (ArrayList<Comentado>) comenteds;
		Object users = request.getAttribute("usuarios");
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) users;
		Object oretomba = request.getAttribute("errores");
		ArrayList<String> errores = (ArrayList<String>) oretomba;
		if (errores != null) {
			if (errores.size() != 0) {
	%>
	<h2>Lista de errores</h2>
	<%
		for (int i = 0; i < errores.size(); i++) {
	%>
	<p><%=errores.get(i)%></p>
	<%
		}
			}
		}
		if (users == null || comentados == null || comentarios == null) {
	%>
	<h2>No hay comentarios</h2>
	<%
		} else {
			java.util.Set<Integer> keys = comentarios.keySet();
			int i = 0;
			for (Integer key : keys) {
				String rutaImagen = "perfiles/default.jpg";
				String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
				File tieneImagen = new File(directorio + usuarios.get(i).getCod_usr() + ".jpg");
				if (tieneImagen.exists()) {
					rutaImagen = "perfiles/" + usuarios.get(i).getCod_usr() + ".jpg";
				}
	%>
	<p>
		<img src="<%=rutaImagen%>" width="64" heigth="64" />
		<%=usuarios.get(i).getNombre() + "\t" + usuarios.get(i).getApellidos() + " ("
							+ usuarios.get(i).getMail() + ")"%><input
			type="image" src="recursos/aspa.jpg"
			onclick="migo(<%=comentarios.get(key).getCodcamen()%>)" /><br /><%=comentarios.get(key).getContenido()%>
	</p>
	<%
		i++;
			}
		}
	%>
</body>
</html>