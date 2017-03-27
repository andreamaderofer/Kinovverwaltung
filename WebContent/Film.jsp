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
#pos1 {
	float: middle;
	margin-left: 500px;
}
</style>
</head>
<body>
	<section class="container">
	<form action="UpdateFilm" method="post">
	
	<font size=6xp><u>Folgende Filme gibt es:</u></font>
	<table>
		<colgroup>
 	 		<col width=80>
 		</colgroup>
		<% 
			ArrayList<Film> filme=(ArrayList<Film>)session.getAttribute("liste");
			int i = 0;
			for(i = 0;i < 1;i++){	
				if(filme.get(i) == null){
					break;
				}
		%>
			<tr align="left">
				<td ><%="Name:" %></td>
				<td><input type = "text" id="name" value="<%=filme.get(i).getName()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Genre:" %></td>
				<td><input type = "text" id="genre"  value="<%=filme.get(i).getGenre()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Alter:"%></td>
				<td><input type = "text" id="alter" value="<%=filme.get(i).getAltersfreigabe()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Dauer:" %></td>
				<td><input type="text" id="dauer" value="<%=filme.get(i).getDauerMin()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="3D:" %></td>
				<td><input type="text" id="3D"value="<%=filme.get(i).getIst3D()%>"/></td>
			</tr>
			<tr>
				<td align="right"><input id="id" type="submit" name="update" value="update" /></td>
				<td align="left"><input id="id" type="submit" name="delete"  value="delete" /></td>
			</tr>
		<% 	
			}
		%>
	</table>
	</form>
	</section>
</body>
</html>

