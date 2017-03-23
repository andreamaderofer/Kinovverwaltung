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
 * Servlet implementation class Saal
 */
@WebServlet("/DeleteFilmServlet")
public class DeleteFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFilm() {
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

		ArrayList<Film> fListe = DBManager.Instance().getFilme();
		int fID = Integer.parseInt(request.getParameter("id"));
		
		for(Film f: fListe){
			if(f.getID() == fID){
				f.delete();
				break;
			}
		}
		
		HttpSession s = request.getSession();
		s.setAttribute("liste", DBManager.Instance().getFilme());
		response.sendRedirect("Film.jsp");
	}
}