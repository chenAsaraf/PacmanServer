package GameEntities;

import Coords.MyCoords;
import Game.Map;
import Geom.Point3D;

/**
 * This Class represent the main player of the game and extends the Packman class. 
 * The main player is identical in all his characteristics to the other packmans except his ability
 * to calculate azimuth from its current point to the next (which will be sent to the server).
 * When the game is loaded the user is asked to place the main player according to the
 * winning strategy considerations.
 * 
 * @author Inna and Chen
 */
public class Player extends Packman{
	
	private final String imagePlayer = "images/Player.png";
	
	private final MyCoords conversion = new MyCoords();

	/**
	 * Constructor
	 * @param firstLocation Point3D
	 * @param id int
	 */
	public Player(Point3D firstLocation, int id) {
		super(firstLocation, id);
	}

	/**
	 * Copy constructor
	 * @param demo Packman
	 */
	public Player(Packman demo) {
		super(demo);
	}

	
	/**
	 * Constructor - from String
	 * @param row String
	 */ 
	public Player (String row){
		super(row);
	}


	
	/**
	 * Calculate the azimuth to the input pixel coordinates Point3D.
	 * @param click Point3D
	 * @return az double
	 */
	public double Azimuth(Point3D click) {
		Point3D clickPolar = new Point3D(Map.convertToPolar(click));
		Point3D myLocationPolar = new Point3D(Map.convertToPolar(getLocation()));
		double [] azimuth_elevation_dist = conversion.azimuth_elevation_dist(myLocationPolar, clickPolar);
		double az = 450 - azimuth_elevation_dist[0];
		if (az < 0) az = az + 360;
		else if (az > 360) az = az % 360;
		return az;
	}
	
	
	@Override
	public String getImage() {
		return imagePlayer;
	}


}
