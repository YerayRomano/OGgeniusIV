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
<script>
function timeouttimeout(nba2k) {
    var pathfinda = '';
    var bom = true;
    switch (nba2k) {
        case 1:
            pathfinda = '/OGgenius/Bienvenida';
        break;
        case 2:
            pathfinda = '/OGgenius/NaveNodriza';
        break;
        default:
            bom = false;
            alert('no puedes pedir tiempo muerto');
        break;
    }
    if (bom == true) {
        setTimeout(function () { window.open(pathfinda,'_self') },6000);
    }
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="area51">
	<% 
	if(request.getParameter("op") == null) {
	%>
	<div class="area51">
		<form action="" method="post">
            <h2>Parece que quieres marcharte, ¿estas seguro?</h2>
            <input type="button" value="si" onclick="window.open('/OGgenius/SalirDeEsteAntro?op=1','_self')"/>&nbsp;<input type="button" value="no" onclick="window.open('/OGgenius/SalirDeEsteAntro?op=2','_self')"/>
		</form>
	</div>
	<%
	} else {
		try {
			int tipo = Integer.parseInt(request.getParameter("op") );
			switch(tipo) {
				case 2:
	%>
	<p>Gracias por quedarte en la gran familia de leopardo productions, tupac esta orgulloso de ti</p><br/>
	<img src="recursos/tupac_feliz.gif"/><br/>
	<script>
	timeouttimeout(1);
	</script>
	<%	
				break;
				case 1:
	%>
	<p>le has hecho llorar desalmado, tu nos aportabas</p>
	<img src="recursos/chinita.gif"/><br/>
	<script>
	timeouttimeout(2);
	</script>
	<%			
				break;
				default:
	%>
	<div class="area51">
		<h1>No me hackees my nigga</h1>
	</div>
	<%
				break;
			}
		} catch (NumberFormatException e) {
	%>
	<div class="area51">
		<h1>No me hackees my nigga</h1>
	</div>
	<%
		}
	}
	%>
</body>
</html>