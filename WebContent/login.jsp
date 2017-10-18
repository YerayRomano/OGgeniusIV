<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="area51">
		<form action="" method="post">
			<h2>OGgenius IV</h2>
			Usuario:<input type="text" name="usr">&nbsp;Contraseña:<input
				type="password" name="pss"><br /> <input type="button"
				value="Registrarse" onclick="window.open('/OGgenius/Registro','_self')">&nbsp;<input
				type="submit" value="Accede">
		</form>
	</div>
</body>
</html>