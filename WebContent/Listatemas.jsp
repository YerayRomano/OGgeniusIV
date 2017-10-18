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
	margin-top: 5%;
	width: 50%;
}

.centro {
	text-align: center;
}
</style>
</head>
<body>
	<div class="area51">
		<h2>Escoge tu tema visual</h2>
		<%
			Object losTemas = request.getAttribute("temaList");
			ArrayList<Tema> temaList = (ArrayList<Tema>) losTemas;
			if (temaList == null) {
		%>
		<p>Error, no hay temas</p>
		<%
			} else {
				if (temaList.size() == 0) {
		%>
		<p>Error, no hay temas</p>
		<%
			} else {
		%>
		<table>
			<%
				for (int i = 0; i < temaList.size(); i++) {
			%>
			<form action="" method="post" id="<%=i%>">
				<input type="hidden" name="tema" value="<%=i%>" />
			</form>
			<td><img src="temas/<%=i%>.jpg" width="320" height="240"
				onclick="document.getElementById('<%=i%>').submit()" /><br /><%=temaList.get(i).getNombre()%></td>
			<%
				if (i % 4 == 0 && i != 0) {
			%>
			</tr>
			<%
				if ((i + 1) != temaList.size()) {
			%>
			<tr>
				<%
					}
				%>
				<%
					}
				%>
				<%
					}
				%>
			
		</table>
		<%
			}
			}
		%>
	</div>
</body>
</html>