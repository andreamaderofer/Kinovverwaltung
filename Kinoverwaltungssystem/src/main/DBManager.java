package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Film;
import models.Filmsequenz;
import models.Saal;
import models.Sitz;
import models.Sitzplatzreservierung;

/**
 * dfsdfsdfsdf
 *<br/>
 *  {@link #Save(Film) }<br/>
 *
 * @author ndsts_000
 * */
public class DBManager {

	Connection conn;

	// Singleton Pattern
	private static DBManager instance = null;
	
	private final static Logger LOGGER = Logger.getLogger(DBManager.class.getName());

	/**
	 * einmaliges initialisieren des DBManagers
	 */
	private DBManager() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Kinosaalverwaltung", "root", "");
			
			Handler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.INFO);  
			LOGGER.addHandler(consoleHandler);
			LOGGER.setLevel(Level.FINE);
			
		} catch (Exception e) {
			LOGGER.severe("Datenbankverbindung fehlgeschlagen!");
			LOGGER.severe(e.getMessage());
		}
	}

	/**
	 * DB-Zugriffsinstanz holen
	 * @return Zugriffsinstanz
	 */
	public static DBManager Instance() {
		if (instance == null)
			instance = new DBManager();
		return instance;
	}

	/**
	 * Connection schließen
	 * @throws SQLException Bei SQL-Fehlern ausgeloest
	 */
	public void close() throws SQLException {
		conn.close();
	}

	/**
	 * DB-Speichern eines Filmes.
	 * Sollte es sich um ein der Datenbank unbekanntes Objekt handeln, so wird dies
	 * anhand der ID < 0 erkannt. Dieser Schritt muss lokal berücksichtigt werden!
	 * Dementsprechend wird ein INSERT für neue Objekte und ein UPDATE für bestehende ausgeführt.
	 * Im Falle des INSERTS wird die von der Datenbank generierte ID geholt und dem lokalen Objekt zugewiesen.
	 * @param fi Der zu speichernde Film.
	 * @see Film
	 */
	public void Save(Film fi) {

		PreparedStatement stat = null;
		
		if (fi.getID() < 0) {

			String sql = "INSERT INTO Film VALUES (?, ?, ?, ?, ?, ?, ?)";
			ResultSet rs = null;
			Statement stmt = null;
			
			try {
				
				stat = conn.prepareStatement(sql);
				stat.setString(1, null);
				stat.setString(2, fi.getName());
				stat.setString(3, fi.getGenre());
				stat.setInt(4, fi.getAltersfreigabe());
				stat.setBoolean(5, fi.isIst3D());
				stat.setInt(6, fi.getDauerMin());
				stat.execute();

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					fi.setID(rs.getInt(1));
				}

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Insertproblem - Film");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
				try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}

		} else {

			String sql = "UPDATE Film SET Name = ?, Genre = ?, Altersfreigabe = ?, 3D = ?, Dauer = ? WHERE FilmID = ?";

			try {
				stat = conn.prepareStatement(sql);
				stat.setString(1, fi.getName());
				stat.setString(2, fi.getGenre());
				stat.setInt(3, fi.getAltersfreigabe());
				stat.setBoolean(4, fi.isIst3D());
				stat.setInt(5, fi.getDauerMin());
				stat.setInt(6, fi.getID());
				stat.execute();

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Insertproblem - Film");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}
		}

	}

	/**
	 * DB-Speichern einer Filmsequenz.
	 * Sollte es sich um ein der Datenbank unbekanntes Objekt handeln, so wird dies
	 * anhand der ID < 0 erkannt. Dieser Schritt muss lokal berücksichtigt werden!
	 * Dementsprechend wird ein INSERT für neue Objekte und ein UPDATE für bestehende ausgeführt.
	 * Im Falle des INSERTS wird die von der Datenbank generierte ID geholt und dem lokalen Objekt zugewiesen.
	 * @param fs Die zu speichernde Filmsequenz
	 */
	public void Save(Filmsequenz fs) {
		
		PreparedStatement stat = null;
		
		if (fs.getID() < 0) {

			String sql = "INSERT INTO Filmsequenz VALUES (?, ?, ?, ?, ?)";
			ResultSet rs = null;
			Statement stmt = null;
			
			try {

				stat = conn.prepareStatement(sql);
				stat.setString(1, null);
				stat.setInt(2, fs.getSaal().getID());
				stat.setInt(3, fs.getFilm().getID());
				stat.setDate(4, fs.getDatum());
				stat.setTime(5, fs.getBeginn());
				stat.execute();

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					fs.setID(rs.getInt(1));
				}

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Insertproblem - Filmsequenz");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
				try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}

		} else {

			String sql = "UPDATE Filmsequenz SET SaalNr = ?, Film = ?, Datum = ?, Beginn = ? WHERE FilmsequenzID = ?";

			try {

				stat = conn.prepareStatement(sql);
				stat.setInt(1, fs.getSaal().getID());
				stat.setInt(2, fs.getFilm().getID());
				stat.setDate(3, fs.getDatum());
				stat.setTime(4, fs.getBeginn());
				stat.setInt(5, fs.getID());
				stat.execute();

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Updateproblem - Sitz");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}
		}
	}

	/**
	 * DB-Speichern eines Saals
	 * Sollte es sich um ein der Datenbank unbekanntes Objekt handeln, so wird dies
	 * anhand der ID < 0 erkannt. Dieser Schritt muss lokal berücksichtigt werden!
	 * Dementsprechend wird ein INSERT für neue Objekte und ein UPDATE für bestehende ausgeführt.
	 * Im Falle des INSERTS wird die von der Datenbank generierte ID geholt und dem lokalen Objekt zugewiesen.
	 * @param sa Der zu speichernde Saal.
	 */
	public void Save(Saal sa) {

		PreparedStatement stat = null;
		
		if (sa.getID() < 0) {

			String sql = "INSERT INTO Saal VALUES (?, ?, ?)";
			ResultSet rs = null;
			Statement stmt = null;
			
			try {
				stat = conn.prepareStatement(sql);
				stat.setString(1, null);
				stat.setInt(2, sa.getAnzSitze());
				stat.setBoolean(3, sa.isIst3D());
				stat.execute();

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					sa.setID(rs.getInt(1));
				}

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Insertproblem - Saal");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
				try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}

		} else {

			String sql = "UPDATE Saal SET Sitzzahl = ?, 3D = ? WHERE SaalID = ?";

			try {
				
				stat = conn.prepareStatement(sql);
				stat.setInt(1, sa.getAnzSitze());
				stat.setBoolean(2, sa.isIst3D());
				stat.setInt(3, sa.getID());
				stat.execute();

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Updateproblem - Saal");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}
		}

	}

	/**
	 * DB-Speichern eines Sitzes
	 * Sollte es sich um ein der Datenbank unbekanntes Objekt handeln, so wird dies
	 * anhand der ID < 0 erkannt. Dieser Schritt muss lokal berücksichtigt werden!
	 * Dementsprechend wird ein INSERT für neue Objekte und ein UPDATE für bestehende ausgeführt.
	 * Im Falle des INSERTS wird die von der Datenbank generierte ID geholt und dem lokalen Objekt zugewiesen.
	 * @param si Der zu speichernde Sitz
	 */
	public void Save(Sitz si) {

		PreparedStatement stat = null;
		
		if (si.getID() < 0) {

			String sql = "INSERT INTO Sitz VALUES (?, ?, ?, ?)";
			ResultSet rs = null;
			Statement stmt = null;
			
			try {
				stat = conn.prepareStatement(sql);
				stat.setString(1, null);
				stat.setInt(2, si.getReihe());
				stat.setInt(3, si.getReihenNr());
				stat.setInt(4, si.getSaal().getID());
				stat.execute();

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					si.setID(rs.getInt(1));
				}

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Insertproblem - Sitz");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
				try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}

		} else {

			String sql = "UPDATE Sitz SET Reihe = ?, ReihenNr = ?, SaalID = ? WHERE SitzID = ?";

			try {

				stat = conn.prepareStatement(sql);
				stat.setInt(1, si.getReihe());
				stat.setInt(2, si.getReihenNr());
				stat.setInt(3, si.getSaal().getID());
				stat.setInt(4, si.getID());
				stat.execute();

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Updateproblem - Sitz");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}
		}

	}

	/**
	 * DB-Speichern einer Sitzplatzreservierung
	 * Sollte es sich um ein der Datenbank unbekanntes Objekt handeln, so wird dies
	 * anhand der ID < 0 erkannt. Dieser Schritt muss lokal berücksichtigt werden!
	 * Dementsprechend wird ein INSERT für neue Objekte und ein UPDATE für bestehende ausgeführt.
	 * Im Falle des INSERTS wird die von der Datenbank generierte ID geholt und dem lokalen Objekt zugewiesen.
	 * @param spr Die zu speichernde Reservierung
	 */
	public void Save(Sitzplatzreservierung spr) {

		PreparedStatement stat = null;
		
		if (spr.getID() < 0) {

			String sql = "INSERT INTO Sitzplatzreservierung VALUES (?, ?, ?, ?, ?, ?)";
			ResultSet rs = null;
			Statement stmt = null;
			
			try {

				stat = conn.prepareStatement(sql);
				stat.setString(1, null);
				stat.setInt(2, spr.getSitz().getID());
				stat.setInt(3, spr.getFilmsequenz().getID());
				stat.setString(4, String.valueOf(spr.getBelegung()));
				stat.setString(5, spr.getResName());
				stat.execute();

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					spr.setID(rs.getInt(1));
				}

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Insertproblem - Sitzplatzreservierung");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
				try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}

		} else {

			String sql = "UPDATE Reservierung SET SitzID = ?, FilmlsequenzID = ?, Belegung = ?, Reservierungsname = ? WHERE SprID = ?";

			try {

				stat = conn.prepareStatement(sql);
				stat.setInt(1, spr.getSitz().getID());
				stat.setInt(2, spr.getFilmsequenz().getID());
				stat.setString(3, String.valueOf(spr.getBelegung()));
				stat.setString(4, spr.getResName());
				stat.setInt(5, spr.getID());
				stat.execute();

			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				LOGGER.severe("Updateproblem - Sitz");
			} finally {
			    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			}
		}
	}

	/**
	 * Abfragen aller Sitze eines bestimmten Saales
	 * @param saal Der zugehoerige Saal
	 * @return Die Liste der Sitze
	 */
	public ArrayList<Sitz> getSitze(Saal saal) {
		
		ArrayList<Sitz> sitze = new ArrayList<Sitz>();
		String sql = "SELECT * FROM Sitz JOIN Reservierung USING(SitzID) JOIN Saal USING(SaalID) WHERE SaalID = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, saal.getID());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Sitz s = new Sitz(rs.getInt("SitzID"), rs.getInt("Reihe"), rs.getInt("ReihenNr"), saal);
				sitze.add(s);
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Sitz");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
		
		return sitze;
	}

	/**
	 * Abfragen aller Saele
	 * @return Die Liste der Saele
	 */
	public ArrayList<Saal> getSaele() {
		
		ArrayList<Saal> saele = new ArrayList<Saal>();
		String sql = "SELECT * FROM Saal";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				saele.add(new Saal(rs.getInt("SaalID"), rs.getInt("Sitzzahl"), rs.getBoolean("")));
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Saal");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
		
		return saele;
	}

	/**
	 * Abfragen aller Filmsequenzen eines bestimmten Saales und Filmes
	 * @param saal Der zugehoerige Saal
	 * @param film Der zugehoerige Film
	 * @return Die Liste der Filmsequenzen
	 */
	public ArrayList<Filmsequenz> getFilmsequenzen(Saal saal, Film film) {
		
		String sql = "SELECT * FROM Filmsequenz JOIN Saal ON USING(SaalID) JOIN FILM USING(FilmID) WHERE SaalID = ? AND FilmID = ?";
		ArrayList<Filmsequenz> fsequenz = new ArrayList<Filmsequenz>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, saal.getID());
			stmt.setInt(2, film.getID());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Filmsequenz filmsequenz = new Filmsequenz(rs.getInt("FilmsequenzID"), saal, film, rs.getDate("Datum"), rs.getTime("Beginn"));
				
				fsequenz.add(filmsequenz);
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Filmsequenz");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
		
		return fsequenz;
	}
	
	
	/**
	 * Abfragen aller Filmsequenzen eines bestimmten Filmes
	 * @param film Der zugehoerige Film
	 * @return Die Liste der Filmsequenzen
	 */
	public ArrayList<Filmsequenz> getFilmsequenzen(Film film) {
		
		String sql = "SELECT * FROM Filmsequenz JOIN Saal ON USING(SaalID) JOIN FILM USING(FilmID) WHERE FilmID = ?";
		ArrayList<Filmsequenz> fsequenz = new ArrayList<Filmsequenz>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getID());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Saal saal = new Saal(rs.getInt("SaalID"), rs.getInt("Sitzzahl"), rs.getBoolean("3D"));
				Filmsequenz filmsequenz = new Filmsequenz(rs.getInt("FilmsequenzID"), saal, film, rs.getDate("Datum"), rs.getTime("Beginn"));
				
				fsequenz.add(filmsequenz);
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Filmsequenz");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
		
		return fsequenz;
	}
	
	
	/**
	 * Abfragen aller Filmsequenzen eines bestimmten Saales
	 * @param saal Der zugehoerige Saal
	 * @return Die Liste der Filmsequenzen
	 */
	public ArrayList<Filmsequenz> getFilmsequenzen(Saal saal) {
		
		String sql = "SELECT * FROM Filmsequenz JOIN Saal ON USING(SaalID) JOIN FILM USING(FilmID) WHERE SaalID = ?";
		ArrayList<Filmsequenz> fsequenz = new ArrayList<Filmsequenz>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, saal.getID());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Film film = new Film(rs.getInt("FilmID"), rs.getString("Name"), rs.getString("Genre"), rs.getInt("Altersfreigabe"), rs.getBoolean("3D"), rs.getInt("Dauer"));
				Filmsequenz filmsequenz = new Filmsequenz(rs.getInt("FilmsequenzID"), saal, film, rs.getDate("Datum"), rs.getTime("Beginn"));
				
				fsequenz.add(filmsequenz);
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Filmsequenz");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
		
		return fsequenz;
	}
	
	/**
	 * Abfragen aller Filme
	 * @return Die Liste der Filme
	 */
	public ArrayList<Film> getFilme() {
		
		ArrayList<Film> filme = new ArrayList<Film>();
		String sql = "SELECT * FROM Film";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Film film = new Film(rs.getInt("FilmID"), rs.getString("Name"), rs.getString("Genre"), rs.getInt("Altersfreigabe"), rs.getBoolean("3D"), rs.getInt("Dauer"));
				
				filme.add(film);
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Film");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}

		return filme;
	}

	/**
	 * Abfragen aller Sitzplatzreservierungen einer bestimmten Sequenz und eines bestimmten Sitzes
	 * @param sequenz Die zugehoerige Sequenz
	 * @param sitz Der zugehoerige Sitz
	 * @return Die Liste der Reservierungen
	 */
	public ArrayList<Sitzplatzreservierung> getSitzplatzReservierung(Filmsequenz sequenz, Sitz sitz) {

		ArrayList<Sitzplatzreservierung> reservierungen = new ArrayList<Sitzplatzreservierung>();
		String sql = "SELECT * FROM Reservierungen WHERE FilmsequenzID = ? AND SitzID = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, sequenz.getID());
			stmt.setInt(2, sitz.getID());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Sitzplatzreservierung res = new Sitzplatzreservierung(rs.getInt("SprID"), sitz, sequenz, rs.getString("Belegung").charAt(0), rs.getString("Reservierungsname"));
				
				reservierungen.add(res);
			}
			
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Selectproblem - Sitzplatzreservierung");
		} finally {
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
			try { if (rs != null) rs.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
		
		return reservierungen;
	}
	
	/**
	 * Entfernen eines bestimmten Saales
	 * @param saal Der zugehoerige Saal
	 */
	public void Delete(Saal saal){
		
		PreparedStatement stat = null;
		String sql = "DELETE FROM Saal WHERE SaalID = ?";

		try {

			stat = conn.prepareStatement(sql);
			stat.setInt(1, saal.getID());
			stat.execute();

		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Deleteproblem - Saal");
		} finally {
		    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
	}
	
	/**
	 * Entfernen eines bestimmten Sitzes
	 * @param sitz Der zugehoerige Sitz
	 */
	public void Delete(Sitz sitz){
		
		PreparedStatement stat = null;
		String sql = "DELETE FROM Sitz WHERE SitzID = ?";

		try {

			stat = conn.prepareStatement(sql);
			stat.setInt(1, sitz.getID());
			stat.execute();

		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Deleteproblem - Sitz");
		} finally {
		    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
	}
	
	/**
	 * Entfernen einer bestimmten Sitzplatzreservierung
	 * @param sitzpl Die zugehoerige Reservierung
	 */
	public void Delete(Sitzplatzreservierung sitzpl){
		
		PreparedStatement stat = null;
		String sql = "DELETE FROM Reservierung WHERE SprID = ?";

		try {

			stat = conn.prepareStatement(sql);
			stat.setInt(1, sitzpl.getID());
			stat.execute();

		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Deleteproblem - Sitzplatzreservierung");
		} finally {
		    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
	}
	
	/**
	 * Entfernen eines bestimmten Filmsequenz
	 * @param filmseq Die zugehoerige Sequenz
	 */
	public void Delete(Filmsequenz filmseq){
		
		PreparedStatement stat = null;
		String sql = "DELETE FROM Filmsequenz WHERE FilmsequenzID = ?";

		try {

			stat = conn.prepareStatement(sql);
			stat.setInt(1, filmseq.getID());
			stat.execute();

		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Deleteproblem - Filmsequenz");
		} finally {
		    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
	}
	
	/**
	 * Entfernen eines bestimmten Filmes
	 * @param film Der zugehoerige Film
	 */
	public void Delete(Film film){
		
		PreparedStatement stat = null;
		String sql = "DELETE FROM Film WHERE FilmID = ?";

		try {

			stat = conn.prepareStatement(sql);
			stat.setInt(1, film.getID());
			stat.execute();

		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe("Deleteproblem - Film");
		} finally {
		    try { if (stat != null) stat.close(); } catch (Exception e) {LOGGER.severe(e.getMessage());};
		}
	}
}