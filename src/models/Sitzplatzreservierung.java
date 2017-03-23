package models;

import main.DBManager;

public class Sitzplatzreservierung {

	private int ID;
	private Sitz sitz;
	private Filmsequenz filmsequenz;
	private char belegung;
	private String resName;

	/**
	 * 
	 * @param id Die Sitzplatreservierungsid
	 * @param sitz Der Sitz der Reserviert werden soll
	 * @param filmsequenz Die Filmsequenz f�r das Datum und  die Zeit
	 * @param bel Ob der Sitz reserviert ist oder nicht
	 * @param res Der Reservierungsname
	 */
	public Sitzplatzreservierung(int id, Sitz sitz, Filmsequenz filmsequenz, char bel, String res) {
		this.ID = id;

		this.sitz = sitz;
		this.filmsequenz = filmsequenz;
		this.belegung = bel;
		this.resName = res;
	}
	
	/**
	 * 
	 * @param sitz
	 * @param filmsequenz
	 * @param bel
	 * @param res
	 */
	public Sitzplatzreservierung(Sitz sitz, Filmsequenz filmsequenz, char bel, String res) {
		this.ID = -1;
		
		this.sitz = sitz;
		this.filmsequenz = filmsequenz;
		this.belegung = bel;
		this.resName = res;
	}

	/**
	 * 
	 *@return gibt die Sitzplatzreservierungsid zur�ck
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param sprID setzt die Sitzplatzreservierungsid
	 */
	public void setID(int sprID) {
		this.ID = sprID;
	}

	/**
	 * 
	 * @return gibt den reservierten Sitz zur�ck
	 */
	public Sitz getSitz() {
		return sitz;
	}

	/**
	 * 
	 * @param sitz setzt den Sitz f�rs reservieren
	 */
	public void setSitz(Sitz sitz) {
		this.sitz = sitz;
	}

	/**
	 * 
	 * @return gibt die Filmsequenz zur�ck wo der Sitz reserviert wurde
	 */
	public Filmsequenz getFilmsequenz() {
		return filmsequenz;
	}

	/**
	 * 
	 * @param filmsequenz setzt die Filmsequenz f�r den zu reservierenden Sitz
	 */
	public void setFilmsequenz(Filmsequenz filmsequenz) {
		this.filmsequenz = filmsequenz;
	}

	/**
	 * 
	 * @return gibt die Belegung zur�ck
	 */
	public char getBelegung() {
		return belegung;
	}

	/**
	 * 
	 * @param belegung setzt ob der Sitz belegt ist oder nicht
	 */
	public void setBelegung(char belegung) {
		this.belegung = belegung;
	}

	/**
	 * 
	 * @return gibt den Reservierungsnamen zur�ck
	 */
	public String getResName() {
		return resName;
	}

	/**
	 * 
	 * @param resName setzt den Reservierungsnamen
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}
	
	/**
	 * zum Speichern der Sitzplatzreservierung in die Datenbank
	 */
	public void save() {
		DBManager.Instance().Save(this);
	}
	
	/**
	 * zum Loeschen der Sitzplatzreservierung aus der Datenbank
	 */
	public void delete() {
		DBManager.Instance().Delete(this);
	}
}
