package GameEntities;


import GIS.GIS_element;
import GIS.Meta_data;
import Game.Map;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * This class represents a "target" in a known geographic location
 * (without movement)
 * @author Inna and Chen
 *
 */
public class Fruit implements GIS_element {

	private final String imageFruit = "images/Fruit.png";

	private int id;
	private Point3D location;
	private double weight;

	private final int CSV_ID = 1;
	private final int CSV_LON = 2;
	private final int CSV_LAT = 3;
	private final int CSV_ALT = 4;
	private final int CSV_WIEGHT = 5;
	private final String COMMA_DELIMITER = ",";



	/**
	 * Constructor 
	 * for mode where the user builds the game
	 * @param firstLocation
	 */
	public Fruit(Point3D location, int id) {
		this.id = id;
		this.location = location;
		this.weight = 1;
	}

	/**
	 * Constructor copy
	 * @param other
	 */
	public Fruit(Fruit other) {
		id = other.getId();
		location = new Point3D(other.getLocation());
		weight = other.getWeight();
	}


	/**
	 * Constructor by String
	 * for mode where the user loads the game from a CSV File
	 * @param row
	 */
	public Fruit(String row){
		String [] tokens = row.split(COMMA_DELIMITER);
		if (tokens.length > 0) {
			id = (Integer.parseInt(tokens[CSV_ID]));
			String fruitPoint = tokens[CSV_LAT] + "," + tokens[CSV_LON] + "," + tokens[CSV_ALT];
			location = new Point3D(fruitPoint);
			location = Map.convertToPixel(location);
			weight = Double.parseDouble(tokens[CSV_WIEGHT]);
		}
	}




	/**
	 * A function that returns a row for the CSV file
	 * @return
	 */
	public String output(){
		Point3D p = Map.convertToPolar(this.getLocation());
		String ans = "";
		ans = "F," + this.getId() + "," + p.y() + "," + p.x() + "," + p.z() + "," + this.getWeight();
		return ans;
	}



	/**
	 * Update the fruit location
	 * input: row that represents the entity data received from the server
	 * @param line
	 */
	public void setLocation(String line) {
		String [] tokens = line.split(COMMA_DELIMITER);	
		if (tokens.length > 0) {
			String fruitPoint = tokens[CSV_LAT] + "," + tokens[CSV_LON] + "," + tokens[CSV_ALT];
			Point3D polar = new Point3D(fruitPoint);
			location = Map.convertToPixel(polar);
		}
	}


	/**
	 * Returns the String represent this fruit
	 * @return thisFruit String
	 */
	public String toString() { 
		String thisFruit = "F id: " + id + " location: " + location.ix()+ " " + location.iy();
		return thisFruit;
	}



	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getImage() {
		return imageFruit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point3D getLocation() {
		return location;
	}

	public void setLocation(Point3D location) {
		this.location = location;
	}


	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}



}
