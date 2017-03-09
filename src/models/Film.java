package models;

import main.DBManager;

/**
 * 
 *
 */
public class Film {

	private int ID;
	private String name;
	private String genre;
	private int altersfreigabe;
	private boolean ist3D;
	private int dauerMin;

	/**
	 * Der 
	 * @param id Die ID des Films
	 * @param name Der Name des Films
	 * @param genre Die Genre des Films
	 * @param altersfreigabe Die Altersfreigabe des Films
	 * @param ist3d Ob der Film eine 3D Funktion hat oder nicht
	 * @param dauerMin Wie lange dauert der Film
	 */
	public Film(int id, String name, String genre, int altersfreigabe, boolean ist3d, int dauerMin) {

		ID = id;
		this.name = name;
		this.genre = genre;
		this.altersfreigabe = altersfreigabe;
		ist3D = ist3d;
		this.dauerMin = dauerMin;
	}
	
	/**
	 * 
	 * @param name Der Name des Films
	 * @param genre Die Genre des Films
	 * @param altersfreigabe Die Altersfreigabe des Films
	 * @param ist3d Ob der Film eine 3D Funktion hat oder nicht
	 * @param dauerMin Wie lange dauert der Film
	 */
	public Film(String name, String genre, int altersfreigabe, boolean ist3d, int dauerMin) {

		ID = -1;
		this.name = name;
		this.genre = genre;
		this.altersfreigabe = altersfreigabe;
		ist3D = ist3d;
		this.dauerMin = dauerMin;
	}

	/**
	 * 
	 * @return die ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param iD setzt die ID
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * 
	 * @return den Namen des Films
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name setzt den Namen des Filmes
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return  die Genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * 
	 * @param genre zum Setzen der Genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * 
	 * @return  die Altersfreigabe
	 */
	public int getAltersfreigabe() {
		return altersfreigabe;
	}

	/**
	 * 
	 * @param altersfreigabe setzt die Altersfreigabe
	 */
	public void setAltersfreigabe(int altersfreigabe) {
		this.altersfreigabe = altersfreigabe;
	}

	/**
	 * 
	 * @return  ob 3D oder nicht
	 */
	public boolean isIst3D() {
		return ist3D;
	}

	/**
	 * 
	 * @param ist3d setzt ob 3D oder nicht
	 */
	public void setIst3D(boolean ist3d) {
		ist3D = ist3d;
	}

	/**
	 * 
	 * @return die Dauer des Filmes
	 */
	public int getDauerMin() {
		return dauerMin;
	}
	
	/**
	 * 
	 * @param dauerMin setzt die Dauer des Films
	 */
	public void setDauerMin(int dauerMin) {
		this.dauerMin = dauerMin;
	}

	public String toString() {
		return genre;
	}
	
	/**
	 * zum Speichern des Filmes in die Datenbank
	 */
	public void save() {
		DBManager.Instance().Save(this);
	}
}
