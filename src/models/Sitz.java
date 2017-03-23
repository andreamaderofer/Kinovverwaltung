package models;

import main.DBManager;

public class Sitz {

	private int ID;
	private int reihe;
	private int reihenNr;
	private Saal saal;
	
	/**
	 * 
	 * @param id Die Sitzid
	 * @param reihe Die Reihe wo sich der Sitz befindet
	 * @param reihenID Die ID des Sitzes in der jeweiligen Reihe
	 * @param saal Der Saal wo der Sitz sich befindet
	 */
	public Sitz(int id, int reihe, int reihenID, Saal saal) {

		ID = id;
		this.reihe = reihe;
		this.reihenNr = reihenID;
		this.saal = saal;
	}
	
	/**
	 * 
	 * @param reihe Die Reihe wo sich der Sitz befindet
	 * @param reihenID Die ID des Sitzes in der jeweiligen Reihe
	 * @param saal Der Saal wo der Sitz sich befindet
	 */
	public Sitz(int reihe, int reihenID, Saal saal) {

		ID = -1;
		this.reihe = reihe;
		this.reihenNr = reihenID;
		this.saal = saal;
	}

	/**
	 * 
	 * @return  die Sitzid
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param iD setzt die Sitzid
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * 
	 * @return gibt die Reihe wo der Sitz steht zurück
	 */
	public int getReihe() {
		return reihe;
	}

	/**
	 * 
	 * @param reihe setzt die Reihennummer
	 */
	public void setReihe(int reihe) {
		this.reihe = reihe;
	}

	/**
	 * 
	 * @return den Saal
	 */
	public Saal getSaal() {
		return saal;
	}

	/**
	 * 
	 * @param saal gibt den Saal zurück
	 */
	public void setSaal(Saal saal) {
		this.saal = saal;
	}

	/**
	 * 
	 * @param resName der Name für die Reservierung
	 * @return ob der Sitz schon reserviert ist oder nicht
	 */
	public boolean doReservierung(String resName) {
		return false;
	}
	
	/**
	 * 
	 * @param name name des Kunden
	 * @return ob kaufen oder nicht
	 */
	public boolean doKaufen(String name) {
		return false;
	}

	/**
	 * 
	 * @return löscht die Reservierung
	 */
	public boolean clearReservierung() {
		return false;
	}

	/**
	 * 
	 * @return gibt die Reihennummer zurück
	 */
	public int getReihenNr() {
		return reihenNr;
	}

	/**
	 * 
	 * @param reihenNr setzt die Reihennummer
	 */
	public void setReihenNr(int reihenNr) {
		this.reihenNr = reihenNr;
	}

	public String toString() {
		return null;
	}
	
	/**
	 * zum Speichern des Sitzes in die Datenbank
	 */
	public void save() {
		DBManager.Instance().Save(this);
	}
	
	/**
	 * zum Loeschen des Sitzes aus der Datenbank
	 */
	public void delete() {
		DBManager.Instance().Delete(this);
	}
}
