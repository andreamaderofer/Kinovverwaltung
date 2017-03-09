package models;

import java.sql.Date;
import java.sql.Time;

import main.DBManager;

public class Filmsequenz {

	private int ID;
	private Saal saal;
	private Film film;
	private Date datum;
	private Time beginn;

	/**
	 * 
	 * @param id Die Filmsequenz ID
	 * @param saal Der Saal wo der Film läuft
	 * @param film Der Film der abgesielt wird
	 * @param datum Das Datum wo der Film stattfindet 
	 * @param beginn Die Uhrzeit waann der Film stattfindet
	 */
	public Filmsequenz(int id, Saal saal, Film film, Date datum, Time beginn) {

		ID = id;
		this.saal = saal;
		this.film = film;
		this.datum = datum;
		this.beginn = beginn;
	}
	
	/**
	 * 
	 * @param saal Der Saal wo der Film läuft
	 * @param film Der Film der abgesielt wird
	 * @param datum Das Datum wo der Film stattfindet 
	 * @param beginn Die Uhrzeit waann der Film stattfindet
	 */
	public Filmsequenz(Saal saal, Film film, Date datum, Time beginn) {

		ID = -1;
		this.saal = saal;
		this.film = film;
		this.datum = datum;
		this.beginn = beginn;
	}

	/**
	 * 
	 * @return  die ID des Filmsequenzes
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param iD setzt die ID der Filmsequenz
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * 
	 * @return  den Saal
	 */
	public Saal getSaal() {
		return saal;
	}
	
	/**
	 * 
	 * @param saal setzen des Saales
	 */
	public void setSaal(Saal saal) {
		this.saal = saal;
	}

	/**
	 * 
	 * @return gibt den Film zurück
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * 
	 * @param films setzen des Filmes
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * 
	 * @return  das Datum der Filmsequenz
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * 
	 * @param datum setzen des Datums
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * 
	 * @return gibt die Zeit zurück des Filmes
	 */
	public Time getBeginn() {
		return beginn;
	}

	/**
	 * 
	 * @param beginn setzt die Startzeit der Filmsequenz
	 */
	public void setBeginn(Time beginn) {
		this.beginn = beginn;
	}


	public String toString() {
		return null;
	}
	
	/**
	 * zum Speichern der Filmsequenz in die Datenbank
	 */
	public void save() {
		DBManager.Instance().Save(this);
	}
}
