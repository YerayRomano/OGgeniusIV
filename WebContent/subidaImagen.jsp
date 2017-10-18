<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function escapar() {
		document.getElementById("salida").submit();
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<h2>Sube tu querida foto</h2>
	<form action="" method="post" id="salida">
		<input type="hidden" name="cuerdaHuida" value="true"/>
	</form>
	<form action="" method="post"  enctype="multipart/form-data">
		<%
			String rutaImagen = (String) request.getAttribute("img");
			if(rutaImagen == null) {
				rutaImagen = "perfiles/default.jpg";
			}
		%>
		<img src="<%= rutaImagen %>" alt="tu careto" width="256" heigth="256"/><br/>
		<input type="file" name="foto" acept="image/*"/><br/>
		<input type="submit" value="subir la foto"/><input type="button" value="ahora mismo no" onclick="escapar()"/>
	</form>
</body>
</html>