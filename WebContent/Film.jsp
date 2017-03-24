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
	<div class="FilmServlet">
	
	<font size=6xp><u>Folgende Filme gibt es:</u></font>
	<table>
		<colgroup>
 	 		<col width=80>
 		</colgroup>
		<% 
			ArrayList<Film> filme=(ArrayList<Film>)session.getAttribute("liste");
			int i = 0;
			for(i = 0;i < filme.size();i++){	
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
				<td><%="Alter:" %></td>
				<td><input type = "text" id="alter" value="<%=filme.get(i).getAltersfreigabe()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Dauer:" %></td>
				<td><input type="text" id="dauer" value="<%=filme.get(i).getDauerMin()%>"/></td>
			</tr>
			<tr>
				<td align="right"><input type="submit" name="submit" id="id" value="update" /></td>
				<td align="left"><input type="submit" name="submit" id="id" value="delete" /></td>
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

