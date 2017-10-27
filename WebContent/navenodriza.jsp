<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	width: 70%;
}

.centro {
	text-align: center;
}
</style>
</head>
<body>
	<div class="area51">
		<div class="centro">
		<h2>Bienvenido a tu humilde morada</h2>
		<img src="<%= request.getAttribute("img") %>" width="256" heigth="256"/><br/>
		<%= session.getAttribute("nombre") %>&nbsp;<%= session.getAttribute("apellidos") %><br/>
		<form action="" method="post" style="margin-bottom: 1.5%" enctype="multipart/form-data">
			<input type="file" name="foto"/><br/>
			<input type="submit" value="subelo!!"/>
		</form>
		<input type="button" value="modifica tus datos" onclick="window.open('/OGgenius/ModificarCuenta')"/>&nbsp;<input type="button" value="cambiar tu tema visual" onclick="window.open('/OGgenius/Modtema','_self')"/>&nbsp;<input type="button" value="borrar cuenta" onclick="window.open('/OGgenius/SalirDeEsteAntro')"/>&nbsp;<input type="button" value="volver al home" onclick="window.open('/OGgenius/Principal')"/>&nbsp;<input type="button" value="logout" onclick="window.open('/OGgenius/Logout','_self')"/>
		</div>
	</div>
</body>
</html>