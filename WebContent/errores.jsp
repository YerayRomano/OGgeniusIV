<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="panelError">
		<%
			Object errors = request.getAttribute("errores");
			if(errors == null) {
		%>
		No hay errores, para que me mandas para aqui
		<%
			} else {
				ArrayList <String> errores = (ArrayList <String>)errors;
				if(errores.size() == 0) {
		%>
		No hay errores, para que me mandas para aqui
		<%
				} else {
					for(int i=0;i<errores.size();i++) {
		%>
		<%= errores.get(i) %><br/>
		<%
					}
				}
			}
		%>
	</div>
</body>
</html>