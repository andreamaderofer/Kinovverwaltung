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

			if(session.getAttribute("liste") == null){
				response.sendRedirect("index.jsp");
				return;
			}
		
			ArrayList<Film> filme=(ArrayList<Film>) session.getAttribute("liste");
		
			for(Film f: filme){	
				if(f == null)
					continue;
		%>
			<form action="UpdateFilmServlet" method="post">
			<tr align="left">
				<td ><%="Name:" %></td>
				<td><input type = "text" name = "name" value="<%=f.getName()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Genre:" %></td>
				<td><input type="text" name="genre"  value="<%=f.getGenre()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Alter:"%></td>
				<td><input type="text" name="alter" value="<%=f.getAltersfreigabe()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="Dauer:" %></td>
				<td><input type="text" name="dauer" value="<%=f.getDauerMin()%>"/></td>
			</tr>
			<tr align="left">
				<td><%="3D:" %></td>
				<td><input type="text" name="3D" value="<%=f.getIst3D()%>"/></td>
			</tr>
			<tr>
				<td>
					<button type="submit" name="filmID" value="<%=f.getID()%>">Update</button>
				</td>
			</form>
					<td>
					<form action="DeleteFilmServlet" method="post">
						<button type="submit" name="filmID" value="<%=f.getID()%>">Delete</button>
					</form>
				</td>
			</tr>
		<% 	
			}
		%>
	</table>
	<br /><br />
	<form action="NewFilmServlet" method="post">
		<h1>Film hinzufügen:</h1>
	<table>
	<tr align="left">
		<td>
		Name: 
		</td>
		<td>
		<input type="text" name="name"/> <br />
		</td>
	</tr>	
	<tr  align="left">	
		<td>
		Genre: 
		</td>
		<td>
			<input type="text" name="genre" /> <br />
		</td>
	</tr>
	<tr  align="left">
		<td>	
		Alter: 
		</td>
		<td>
			<input type="text" name="alter" /> <br />
		</td>
	</tr>
	<tr align="left">
		<td>	
		Dauer: 
		</td>
		<td>
		<input type="text" name="dauer" /> <br />
		</td>
	</tr>
	<tr align="left">
		<td>	
		3D: 
		</td>
		<td>
		<input type="text" name="3D" /> <br />
		</td>
	</tr>
	</table>
	</form>
	</section>
</body>
</html>

