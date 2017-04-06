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
	
	<font size=6xp><u>Folgende Filme gibt es:</u></font>
	<table>
		<colgroup>
 	 		<col width=80>
 		</colgroup>
		<% 
			ArrayList<Film> filme=(ArrayList<Film>)session.getAttribute("liste");
			int i = 0;
			for(i = 0;i < filme.size()-1;i++){	
				if(filme.get(i) == null){
					break;
				}
		%>
			<form action="UpdateFilmServlet" method="post">
			<tr align="left">
				<td ><%="Name:" %></td>
				<td><input type = "name<%=filme.get(i).getID()%>" name ="name"value="<%=filme.get(i).getName()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Genre:" %></td>
				<td><input type = "genre<%=filme.get(i).getID()%>" name="genre"  value="<%=filme.get(i).getGenre()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Alter:"%></td>
				<td><input type = "alter<%=filme.get(i).getID()%>" name="alter" value="<%=filme.get(i).getAltersfreigabe()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Dauer:" %></td>
				<td><input type="dauer<%=filme.get(i).getID()%>" name="dauer" value="<%=filme.get(i).getDauerMin()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="3D:" %></td>
				<td><input type="3D<%=filme.get(i).getID()%>" name="3D" value="<%=filme.get(i).getIst3D()%>"/></td>
			</tr>
			<tr>
				<td>
					<button type="submit" name="<%=i%>" value="<%=i%>">update</button>
				</td>
			</form>
					<td>
					<form action="DeleteFilmServlet" method="post">
						<button type="submit" name="<%=i%>" value="<%=i%>">delete</button>
					</form>
				</td>
			</tr>
		<% 	
			}
		%>
	</table>
	</section>
</body>
</html>

