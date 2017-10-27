<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*,auxiliar.Snippets,java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="css/<%=session.getAttribute("tema")%>.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function migo() {
		if(document.getElementById("texto").value == "") {
			alert("error, los comentarios no pueden estar vacios");
			return false;
		}
		$.ajax({
			url : '/OGgenius/Reproductor',
			type : 'post',
			dataType : 'html', //expect return data as html from server
			data : $('#comentario').serialize(),
			success : function(response, textStatus, jqXHR) {
				$('#comenta').html(response);
				return false;//select the id and put the response in the html
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error(s):' + textStatus, errorThrown);
			}
		});
		return false;
	}
	function opinion(opinion) {
		if(opinion != 0 && opinion != 1) {
			alert("error, peticion malformada");
			return false;
		}
		$.ajax({
			url : '/OGgenius/Opina?op='+opinion,
			type : 'get',
			dataType : 'html', //expect return data as html from server
			data : '',
			success : function(response, textStatus, jqXHR) {
				$('#opina').html(response);
				return false;//select the id and put the response in the html
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error(s):' + textStatus, errorThrown);
			}
		});
		return false;
	}
</script>
</head>
<body>
	<div class="cabecera">
		<img src="<%=request.getAttribute("img")%>" width="128" heigth="128" />
		Bienvenido/a:<%=session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos")%>
		Ir a la nave nodriza&nbsp;<input type="image"
			src="recursos/emergencia.jpg"
			onclick="window.open('/OGgenius/Logout','_self')" width="128"
			heigth="128" />
	</div>
	<div class="area51">
		<%
			Object laescogida = request.getAttribute("cancion");
			Cancion cancion = (Cancion) laescogida;
			Object albuma = request.getAttribute("albunes");
			Album album = (Album) albuma;
			Object aof = request.getAttribute("artistas");
			ArrayList<Artista> artistas = (ArrayList<Artista>) aof;
			Object opinions = request.getAttribute("opiniones");
			Integer [] opiniones = (Integer []) opinions; 
		%>
		<h2><%=cancion.getTitulo()%></h2>
		<img src="<%=request.getAttribute("caratula")%>" id="elPutoTeatro" /><br />
		<div id="opina">
			<input type="image" src="recursos/megusta.png" onclick="opinion(0)"/><%= (Integer) opiniones [0] %>&nbsp;&nbsp;<img src="recursos/nomegusta.png" heigth="60" onclick="opinion(1)"/><%= (Integer) opiniones [1] %><br/>
		</div>
		<audio src="mp3/<%=cancion.getCod_can()%>.mp3" controls
			style="margin-top: 2%"></audio>
		<br />
		<h3>Informacion de la cancion</h3>
		Titulo:
		<%=cancion.getTitulo()%><br /> a&ntilde;o:
		<%=cancion.getAno()%><br /> duracion:
		<%=cancion.getDuracion()%><br/> descricpcion:
		<%= cancion.getDescripcion() %><br/>
		<h3>Comentarios</h3>
		<div id="comenta">
		<%
			Object juicyj = request.getAttribute("comentarios");
			ArrayList<Comentario> comentarios = (ArrayList<Comentario>) juicyj;
			Object youngBuck = request.getAttribute("usuarios");
			ArrayList<Usuario> losUsuarios = (ArrayList<Usuario>) youngBuck;
			Usuario usr = new Usuario();
			String rutaImagen = "perfiles/default.jpg";
			String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
			File tieneImagen = new File(directorio + session.getAttribute("cod_usr") + ".jpg");
			if (tieneImagen.exists()) {
				rutaImagen = "perfiles/" + session.getAttribute("cod_usr") + ".jpg";
			}
		%>
		<p>
			<img src="<%=rutaImagen%>" width="64" heigth="64" />
			<%=session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos") + " ("
					+ session.getAttribute("mail") + ")"%><br />
		<form action="" method="" id="comentario">
			¿Que opinas?:<br />
			<textarea name="texto" id="texto"></textarea>
			<input type="button" value="postear" onclick="return migo()"/>
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
					for (int i = (comentarios.size() -1); i > 0; i--) {
						rutaImagen = "perfiles/default.jpg";
						directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
						tieneImagen = new File(directorio + losUsuarios.get(i).getCod_usr() + ".jpg");
						if (tieneImagen.exists()) {
							rutaImagen = "perfiles/" + losUsuarios.get(i).getCod_usr() + ".jpg";
						}
		%>
		<p>
			<img src="<%=rutaImagen%>" width="64" heigth="64" />
			<%=losUsuarios.get(i).getNombre() + "\t" + losUsuarios.get(i).getApellidos() + " ("
								+ losUsuarios.get(i).getMail() + ")"%><br /><%=comentarios.get(i).getContenido()%></p>
		<%
			}
				}
			}
		%>
		</div>
	</div>
</body>
</html>