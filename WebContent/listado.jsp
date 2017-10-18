<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Listado de usuarios</h2>
	<table>
		<tr>
			<th>cod_usr</th>
			<th>nombre</th>
			<th>Apellidos</th>
			<th>mail</th>
			<th>Contrase&ntilde;a</th>
			<th>Activo</th>
			<th>tema</th>
		</tr>
		<%
			Usuario usuario = new Usuario(); 
			ArrayList <Usuario> usuarios = usuario.mostrarUsuarios();
			for(int i=0;i<usuarios.size();i++) {
		%>
		<tr>
			<td><%= usuarios.get(i).getCod_usr() %></td>
			<td><%= usuarios.get(i).getNombre() %></td>
			<td><%= usuarios.get(i).getApellidos() %></td>
			<td><%= usuarios.get(i).getMail() %></td>
			<td><%= usuarios.get(i).getActivo() %></td>
			<td><%= usuarios.get(i).getTema() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>