<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	Object opinions = request.getAttribute("opiniones");
	Integer [] opiniones = (Integer []) opinions;
	%>
	<input type="image" src="recursos/megusta.png" onclick="opinion(0)"/><%= (Integer) opiniones [0] %>&nbsp;&nbsp;<input type="image" src="recursos/nomegusta.png" heigth="60" onclick="opinion(1)"/><%= (Integer) opiniones [1] %><br/>
</body>
</html>