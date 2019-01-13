package GameEntities;


import GIS.GIS_element;
import GIS.Meta_data;
import Game.Map;
import Geom.Circle;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * A class that represents a "robot" with a location,
 * orientation and ability to move (at a defined man speed)
 * Each packman has a speed of movement (in meters per second)
 * and an eating radius (in meters) that defines the minimum proximity
 * of packman to the fruit to eat it.
 * @author Inna and Chen
 */
public class Packman implements GIS_element{

	private final String imagePackman = "images/Packman.png";

	public int time;	
	private int id;
	private Point3D location; 
	private double speed;
	private Circle radiusEat;

	private static final String COMMA_DELIMITER = ",";	
	private final int CSV_ID = 1;
	private final int CSV_LON = 2;
	private final int CSV_LAT = 3;
	private final int CSV_ALT = 4;
	private final int CSV_SPEED = 5;
	private final int CSV_RADIUS = 6;
	



	/**
	 * Constructor 
	 * for mode where the user builds the game
	 * @param firstLocation
	 */
	public Packman(Point3D firstLocation, int id){
		this.id = id;
		location = firstLocation;
		speed = 1;
		radiusEat = new Circle(location, 1);
	}

	/**
	 * Constructor copy
	 * @param demo
	 */
	public Packman(Packman demo){
		id = demo.getId();
		location = new Point3D(demo.getLocation());
		speed = demo.getSpeed();
		radiusEat = new Circle(demo.getRadiusEat());
	}


	/**
	 * Constructor
	 * for mode where the user loads the game from a CSV File
	 * @param row
	 */
	public Packman(String row){
		String [] tokens = row.split(COMMA_DELIMITER);	
		if (tokens.length > 0) {
			id = Integer.parseInt(tokens[CSV_ID]);
			String packmanPoint = tokens[CSV_LAT] + "," + tokens[CSV_LON] + "," + tokens[CSV_ALT];
			Point3D polar = new Point3D(packmanPoint);
			location = Map.convertToPixel(polar);
			speed = Double.parseDouble(tokens[CSV_SPEED]);
			double radius =  Double.parseDouble(tokens[CSV_RADIUS]);
			radiusEat = (new Circle(getLocation(), radius));
		}
	}


	/**
	 * A function that returns a row for the CSV file
	 * @return
	 */
	public String output(){
		Point3D p = Map.convertToPolar(this.getLocation());
		String ans = "";
		ans = "P," + this.getId() + "," + p.y() + "," + p.x() + "," + p.z() + "," + this.getSpeed()
		+ "," + this.getRadiusEat().get_radius();
		return ans;
	}


	public Point3D getPoint() {
		return location;
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

	public void setLocation(double x, double y, double z) {
		Point3D newLocation= new Point3D(x,y,z);
		this.location = newLocation;
	}
	
	
	/**
	 * Update the packman location
	 * input: row that represents the entity data received from the server
	 * @param line
	 */
	public void setLocation(String line) {
		String [] tokens = line.split(COMMA_DELIMITER);	
		if (tokens.length > 0) {
			String packmanPoint = tokens[CSV_LAT] + "," + tokens[CSV_LON] + "," + tokens[CSV_ALT];
			Point3D polar = new Point3D(packmanPoint);
			location = Map.convertToPixel(polar);
		}
	}
	
	/**
	 * Returns the String represent this packman
	 * @return thisPac String
	 */
	public String toString() { 
		 String thisPac = "P id: " + id + " location: " + location.toString();
		 return thisPac;
	}
	
	public String getImage() {
		return imagePackman;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Circle getRadiusEat() {
		return radiusEat;
	}

	public void setRadiusEat(Circle radiusEat) {
		this.radiusEat = radiusEat;
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