<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filmverwaltung</title>
<style>
h1 {
	margin-left: auto;
	margin-right: auto;
}

#pos1 {
	float: middle;
	margin-left: 500px;
}
</style>
</head>
<body>
	<section class="container">
	<div class="Film">
		<h1>Filmverwaltung</h1>
		Film aussuchen: <input type="text" name="Filmwahl" /></br></br>
		Genre: <input type="text" name="Genre" /></br></br> 
		Altersbegrenzung: <input type="text" name="Altersbegrenzung" /></br></br> 
		Laenge: <input type="text" name="Laenge" /></br> </br> 
		3D: <input type="text" name="3D" /></br> </br>
		<p>
			<input type="submit" name="submit" value="aendern" />
		</p>
	</div>
	</section>
</body>
</html>

