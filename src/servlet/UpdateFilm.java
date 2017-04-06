package servlet;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.DBManager;
import models.Film;

/**
 * Servlet implementation class Film
 */
@WebServlet("/UpdateFilmServlet")
public class UpdateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFilm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		String genre = (String) request.getParameter("genre");
		
		int alter = 0;
		int laenge = 0;
		boolean dreiD = false;
		
		System.out.println("UPDATE FILM");
		try{
			alter = Integer.parseInt(request.getParameter("alter"));
			laenge = Integer.parseInt(request.getParameter("dauer"));
			dreiD = Boolean.parseBoolean(request.getParameter("3D"));
		}catch(NumberFormatException e){}
		
		ArrayList<Film> fListe = DBManager.Instance().getFilme();
		
		//id des Buttons
		String par = request.getParameter("0");
		int fID = Integer.parseInt(par);
		
		for(Film f: fListe){
			if(f.getID() == fID){
				f.setName(name);
				f.setGenre(genre);
				f.setAltersfreigabe(alter);
				f.setDauerMin(laenge);
				f.setIst3D(dreiD);
				break;
			}
		}
		
		HttpSession s = request.getSession();
		s.setAttribute("liste", fListe);
		response.sendRedirect("Film.jsp");
	}
}