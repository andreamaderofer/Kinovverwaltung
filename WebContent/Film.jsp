<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.Film"%>
<%@ page import= "java.util.ArrayList"%>
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
	<div class="FilmServlet">
			<table border=1>
	 <colgroup>
 	 <col width=200>
 	 </colgroup>
		<caption>
			Woerter aus der Kategorie
		</caption>
		<tbody>
		<% 
			ArrayList<Film> filme=(ArrayList<Film>)session.getAttribute("liste");
			int i = 0;
			for(i = 0;i < filme.size();i++){	
				if(filme.get(i) == null){
					break;
				}
		%>
			<tr align="center">
				<td><%="Name:" %></td>
				<td><input type="text" id="name"><%=filme.get(i).getName()%></input></td>
			</tr>
			<tr align="center">
				<td><%="Genre:" %></td>
				<td><input type="text" id="genre"><%=filme.get(i).getGenre()%></input></td>
			</tr>
			<tr align="center">
				<td><%="Alter:" %></td>
				<td><input type="text" id="alter"><%=filme.get(i).getAltersfreigabe()%></input></td>
			</tr>
			<tr align="center">
				<td><%="Dauer:" %></td>
				<td><input type="text" id="dauer"><%=filme.get(i).getDauerMin()%></input></td>
			</tr>
			<tr>
				<input type="submit" name="submit" id="id" value="update" />
				<input type="submit" name="submit" id="id" value="delete" />
			</tr>
		<% 	
			}
		%>
		</tbody>
	</table>
	</div>
	</section>
</body>
</html>

