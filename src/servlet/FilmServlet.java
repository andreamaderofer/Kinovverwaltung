package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.DBManager;

/**
 * Servlet implementation class Saal
 */
@WebServlet("/Saal")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filmwahl = (String) request.getParameter("Filmwahl");
		String genre = (String) request.getParameter("Genre");
		int altersbegrenzung = Integer.parseInt(request.getParameter("Altersbegrenzung"));
		String laenge = (String) request.getParameter("Laenge");
		String dreiD = (String) request.getParameter("3D");
		DBManager.Instance().getFilme();
	}
}