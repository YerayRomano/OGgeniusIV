<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-image: url('genio.jpg');
	font-family: arial;
}

.area51 {
	background-color: black;
	color: white;
	opacity: 0.9;
	margin-top: 20%;
	width: 50%;
}

.centro {
	text-align: center;
}
</style>
</head>
<body>
	<%
		Object tusGatos = request.getAttribute("tusDatos");
		Usuario tusDatos = (Usuario) tusGatos;
	%>
	<div class="area51">
		<form action="" method="post">
			<h2>Modifica tus datos</h2>
			Nombre:<input type="text" name="nome" value="<%= tusDatos.getNombre() %>">&nbsp;apellidos:<input type="text" name="apellidos" value="<%= tusDatos.getApellidos() %>"><br/>
			Correo:<input type="text" name="mail" value="<%= tusDatos.getMail() %>"/>&nbsp;Contrase&ntilde;a:<input type="password" name="pss"/><br/>
			Repitela:<input type="password" name="pss0"/>&nbsp<input type="submit" value="Registrate"/>&nbsp;<input type="button" value="Me arrepiento" onclick="window.open('/OGgenius/NaveNodriza','_self')"/><br/>
		</form>
	</div>
</body>