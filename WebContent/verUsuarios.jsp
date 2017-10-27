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
		url : '/OGgenius/GestionUsuarios?del='+cod_comen,
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
</head>
<body>
	<table id="users">
		<tr>
			<th>cod_usr</th>
			<th>nombre</th>
			<th>Apellidos</th>
			<th>mail</th>
			<th>Contrase&ntilde;a</th>
			<th>Activo</th>
			<th>tema</th>
			<th>modificar</th>
			<th>borrar</th>
		</tr>
		<%
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
			for (int i = 0; i < usuarios.size(); i++) {
		%>
		<tr>
			<td><%=usuarios.get(i).getCod_usr()%></td>
			<td><%=usuarios.get(i).getNombre()%></td>
			<td><%=usuarios.get(i).getApellidos()%></td>
			<td><%=usuarios.get(i).getMail()%></td>
			<td><%=usuarios.get(i).getActivo()%></td>
			<td><%=usuarios.get(i).getTema()%></td>
			<td><input type="image" src="recursos/hoja.jpg" alt="modificar" /></td>
			<td><input type="image" src="recursos/aspa.jpg" alt="borrar"
				onclick="migo(<%=usuarios.get(i).getCod_usr()%>)" /></td>
		</tr>
		<%
			}
		%>
	</table>
	<center>
		<input type="image" src="recursos/anadir.jpg" onclick="window.open('/OGgenius/GestionUsuarios?frame=1','_self')"/>
	</center>
</body>
</html>