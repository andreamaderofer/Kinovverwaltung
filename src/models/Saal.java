package models;

import main.DBManager;

public class Saal {
	
	private int ID;
	private int anzSitze;
	private boolean ist3D;
	
	/**
	 * 
	 * @param id Die ID des Saales
	 * @param anzSitze Die Anzahl der Sitze im Saal
	 * @param ist3d Ob der Saal eine 3D Funktion hat oder nicht
	 */
	public Saal(int id, int anzSitze, boolean ist3d) {

		this.ID = id;
		this.anzSitze = anzSitze;
		ist3D = ist3d;
	}
	
	/**
	 * 
	 * @param anzSitze Die Anzahl der Sitze im Saal
	 * @param ist3d Ob der Saal eine 3D Funktion hat oder nicht
	 */
	public Saal(int anzSitze, boolean ist3d) {

		this.ID = -1;
		this.anzSitze = anzSitze;
		ist3D = ist3d;
	}
	
	/**
	 * 
	 * @return  die ID des Saales
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @return  die Anzahl der Sitze im Saal
	 */
	public int getAnzSitze() {
		return anzSitze;
	}

	/**
	 * 
	 * @param iD setzt die ID des Saales
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * 
	 * @param anzSitze setzt die Anzahl der Sitze im Saal
	 */
	public void setAnzSitze(int anzSitze) {
		this.anzSitze = anzSitze;
	}

	/**
	 * 
	 * @return ob der Saal 3D fähig ist oder nicht
	 */
	public boolean isIst3D() {
		return ist3D;
	}

	/**
	 * 
	 * @param ist3d Ist der Film 3D fähig oder nicht
	 */
	public void setIst3D(boolean ist3d) {
		ist3D = ist3d;
	}

	public String toString() {
		return null;
	}
	
	/**
	 * zum Speichern des Saales in die Datenbank
	 */
	public void save() {
		DBManager.Instance().Save(this);
	}
	
	/**
	 * zum Loeschen des Saales aus der Datenbank
	 */
	public void delete() {
		DBManager.Instance().Delete(this);
	}
}