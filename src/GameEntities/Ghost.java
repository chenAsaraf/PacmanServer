package GameEntities;

import Game.Map;
import Geom.Point3D;

/**
 * This class represents a "ghost" in a known geographic location (with the ability to move!).
 * The class has the ability to rebuild itself and update it's fields from a CSV row.
 * @author Inna and Chen
 */
public class Ghost {

	private final  String imageGhost = "images/Ghost.png";

	private int id;
	private Point3D location; 
	private double speed;

	private static final String COMMA_DELIMITER = ",";	
	private final int CSV_ID = 1;
	private final int CSV_LON = 2;
	private final int CSV_LAT = 3;
	private final int CSV_ALT = 4;
	private final int CSV_SPEED = 5;


	/**
	 * Constructor
	 * for mode where the user loads the game from a CSV File
	 * @param row
	 */
	public Ghost(String row){
		String [] tokens = row.split(COMMA_DELIMITER);	
		if (tokens.length > 0) {
			id = Integer.parseInt(tokens[CSV_ID]);
			String ghostPoint = tokens[CSV_LAT] + "," + tokens[CSV_LON] + "," + tokens[CSV_ALT];
			location = new Point3D(ghostPoint);
			location = Map.convertToPixel(location);
			speed = Double.parseDouble(tokens[CSV_SPEED]);
		}
	}


	/**
	 * Update the ghost location
	 * input: row that represents the entity data received from the server
	 * @param line
	 */
	public void setLocation(String line) {
		String [] tokens = line.split(COMMA_DELIMITER);	
		if (tokens.length > 0) {
			String ghostPoint = tokens[CSV_LAT] + "," + tokens[CSV_LON] + "," + tokens[CSV_ALT];
			Point3D polar = new Point3D(ghostPoint);
			location = Map.convertToPixel(polar);
		}
	}


	/**
	 * Returns the String represent this ghost
	 * @return thisGhost String
	 */
	public String toString() {
		String thisGhost =	"G id: " + id + " location: " + location.toString();
		return thisGhost;
	}



	//// GENERAL GETTERS AND SETTERS ////

	public int getID() {
		return id;
	}


	public void setID(int iD) {
		id = iD;
	}


	public Point3D getLocation() {
		return location;
	}


	public void setLocation(Point3D location) {
		this.location = location;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public String getImage() {
		return imageGhost;
	}



}
