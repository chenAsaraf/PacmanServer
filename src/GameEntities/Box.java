package GameEntities;

import Game.Map;
import Geom.Point3D;


/**
 * This class represents a "obstacle" in a known geographic location (without movement).
 * This obstacle is actually in the form of a rectangle, and it's edges are only parallel to the axis
 * Defined by the following variables:
 * (1) Point3D min - the lower left vertex of the rectangle
 * (2) Point3D max - the top right vertex of the rectangle
 * (3) int id - to identifies each box
 * 
 * The class has the ability to rebuild itself from a CSV row
 * @author Inna and Chen
 */
public class Box {

	private Point3D min;
	private Point3D max;
	private int id;


	/**
	 * Constructor
	 * Input: CSV row, for mode where the user loads the game from a CSV File
	 * @param row String
	 */
	public Box(String row){
		int CSV_ID = 1; //2
		int CSV_LON_MIN = 2;
		int CSV_LAT_MIN= 3;
		int CSV_ALT_MIN = 4; //do not need
		int CSV_LON_MAX= 5; 
		int CSV_LAT_MAX = 6;
		int CSV_ALT_MAX = 7;
		String COMMA_DELIMITER = ",";


		String [] tokens = row.split(COMMA_DELIMITER);	
		if (tokens.length > 0) {
			id = Integer.parseInt(tokens[CSV_ID]);
			String minPoint = tokens[CSV_LAT_MIN] + "," + tokens[CSV_LON_MIN] + "," + tokens[CSV_ALT_MIN];
			String maxPoint = tokens[CSV_LAT_MAX] + "," + tokens[CSV_LON_MAX] + "," + tokens[CSV_ALT_MAX];
			min = new Point3D(minPoint);
			min = Map.convertToPixel(min);
			max = new Point3D(maxPoint);
			max = Map.convertToPixel(max);
		}
	}


	/**
	 * Returns the String represent this box
	 * @return thisBox String
	 */
	public String toString() {
		String thisBox = "B id: " + id + " minPoint: " + min.toString() + " maxPoint: " + max.toString();
		return thisBox;
	}
	
	
	//// GENERAL GETTERS AND SETTERS ////

	public Point3D getMin() {
		return min;
	}


	public void setMin(Point3D min) {
		this.min = min;
	}


	public Point3D getMax() {
		return max;
	}


	public void setMax(Point3D max) {
		this.max = max;
	}


	public int getID() {
		return id;
	}


	public void setID(int iD) {
		id = iD;
	}





}
