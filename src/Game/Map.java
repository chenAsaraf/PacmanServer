package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Coords.MyCoords;
import Coords.Pixel;
import Geom.Point3D;

/**
 * This class represent a map, includes map picture's file
 * and method to convert picture's pixels to a global coordinate system
 * @author Inna and Chen
 *
 */
public class Map {
	private static BufferedImage myImage;
	private String filePath; 
	private static Point3D BottomLeft;	// Bottom left edge coordinate
	private static Point3D UpperRight;  // Upper Right edge coordinate
	private static final Pixel XYpixelsBegin = new Pixel(0.0 , 642.0);  // corresponding to bottomLeft coordinate



	
	/**
	 * Constructor gets a map picture's file 
	 * get from the user the map's coordinates
	 * @param path
	 */
	public Map(String path, String mapData) {
		filePath = path;
		//B,0,32.101898,35.202369,0.0,32.105728,35.212416,0.0,1.0//
		String[] pointsData = mapData.split(",");
		String min = pointsData[3] + "," + pointsData[2] + "," + pointsData[4];
		String max = pointsData[6] + "," + pointsData[5] + "," + pointsData[7];
		BottomLeft = new Point3D(min);
		UpperRight = new Point3D(max);
		try {
			myImage = ImageIO.read(new File(filePath));//"C:\\Users\\owner\\Desktop\\Ariel1.png"
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	
	
	/**
	 * Converts coordinates from global representation to pixel in image
	 * @param GPS Point3D
	 * @return pixelFromGPS Point3D
	 */
	public static Point3D convertToPixel(Point3D GPS) {
		MyCoords conversion = new MyCoords();
		
		Point3D vectorDiff_Meter = conversion.vector3D(BottomLeft, GPS);
		
		Point3D pixelFromGPS  =  add(XYpixelsBegin, vectorDiff_Meter);
		return pixelFromGPS;
	}
	
//	public static Point3D convertToPixel(Point3D GPS) {
//		MyCoords conversion = new MyCoords();
//		
//		double width =  MyCoords.distance_x(UpperRight,BottomLeft);	
//		double height = MyCoords.distance_y(UpperRight,BottomLeft);	
//		
//		Point3D vector = new Point3D((width/2), (height/2), 0);
//		Point3D center = conversion.add(BottomLeft, vector);
//		
//		double dw = GPS.x() -(width/2); // Some meters to the right or left from the center should go to find GPS
//		double proLAT = dw/width; // The proportion in reality on the LAT axis
//		double dx = MyCoords.distance_x(center, GPS);
//		double x = dx * proLAT; 
//		
//		double dh = GPS.y() -(height/2); // Some meters to the right or left from the center should go to find GPS
//		double proLON = dh/height; // The proportion in reality on the LON axis
//		double dy = MyCoords.distance_y(center, GPS);
//		double y = dy * proLON; 
//		
//		Point3D pixelFromGPS  =  new Point3D(x,y,0);
//		return pixelFromGPS;
//	}
	
	
	/**
	 * Converts pixels representation in image to global coordinates
	 * @param pixel Point3D
	 * @return gpsFromPixel Point3D
	 */
	public static Point3D convertToPolar(Point3D pixel) {
		Point3D vectorDiff_Meter = vector3Dmeter(XYpixelsBegin, pixel);
		MyCoords conversion = new MyCoords();
		Point3D gpsFromPixel = conversion.add(BottomLeft, vectorDiff_Meter);
		return gpsFromPixel;
	}

	
	
	

	/**
	 * Adds a metric vector to a pixel point
	 * @param pix1
	 * @param local_vector_in_meter Point3D
	 * @return answer Point3D
	 */
	public static Pixel add(Pixel pix1, Point3D local_vector_in_meter) {
		double newX = pix1.x() + convToPixel_x(local_vector_in_meter.x());
		double newY = pix1.y() - convToPixel_y(local_vector_in_meter.y()); // The direction of the y axis is reversed in the GUI
		Pixel answer = new Pixel( newX,  newY);
		return answer;
	}



	


	/**
	 * computes the 3D vector (in meters) between two pixel point
	 * @param px1
	 * @param px2
	 * @return distancePXtoMeter
	 */
	public static Point3D vector3Dmeter(Point3D px1, Point3D px2) { 
		Point3D d = vector3Dpixel_forMeter(px1,px2);
		Point3D distancePXtoMeter = new Point3D(convToMeter_x(d.x()), convToMeter_y(d.y()), d.z());
		return distancePXtoMeter;
	}


	
	/**
	 * Calculates the distance itself in meters between two pixels points
	 * @param px1 Point3D
	 * @param px2 Point3D
	 * @return distanceInMeter
	 */
	public static double vectorMeter(Point3D px1, Point3D px2) {
		double dx = MyCoords.distance_x(px1, px2);
		double dy = MyCoords.distance_y(px1, px2);
		double dz = MyCoords.distance_z(px1, px2);
		Point3D distanceInPixel = new Point3D(dx,dy, dz);
		double distanceInMeter_x = convToMeter_x(distanceInPixel.x());
		double distanceInMeter_y = convToMeter_y(distanceInPixel.y());
		double distanceInMeter = Math.sqrt(Math.pow(distanceInMeter_x, 2) + Math.pow(distanceInMeter_y, 2));
		return distanceInMeter;
	}





	//  computes the 3D vector (in pixels) between two pixel point 
	private static Point3D vector3Dpixel_forMeter(Point3D pix1, Point3D pix2) {
		double dx = pix2.x()-pix1.x();
		double dy = pix1.y()-pix2.y(); //the direction of the y axis is opposite in the Earth map
		double dz = pix2.z()-pix1.z();
		Point3D vector = new Point3D(dx, dy, dz);
		return vector;
	}


	/**
	 * 
	 * @param pix1
	 * @param pix2
	 * @return
	 */
	public static Point3D vector3Dpixel_forMovment(Point3D pix1, Point3D pix2) {
		double dx = pix2.x()-pix1.x();
		double dy = pix2.y()-pix1.y(); 
		double dz = pix2.z()-pix1.z();
		Point3D vector = new Point3D(dx, dy, dz);
		return vector;
	}


	/**
	 * Checks whether the point representing a position in a pixel
	 * is within the boundaries of the image
	 * @param point Point3D represent a pixel
	 * @return 
	 */
	public static boolean is_Valid_Point(Point3D point) {
		boolean flagX = ((point.x() > 0) && (point.x() < myImage.getWidth()));
		boolean flagY = ((point.y() > 0) && (point.y() < myImage.getHeight()));
		return flagX && flagY;
	}

	
	
	//the ratio between meters to pixels in the x axis - how many meters in 1 pixel
	private static double ratioX() { 
		double x_coords_diff = MyCoords.distance_x(BottomLeft, UpperRight);
		double ratioX = x_coords_diff/myImage.getWidth(); ////0.7048811679269573  in Ariel1 image
		return ratioX;
	}

	
	
	//the ratio between meters to pixels in the y axis - how many meters in 1 pixel
	private static double ratioY() {
		double y_coords_diff = MyCoords.distance_y(BottomLeft, UpperRight);
		double ratioX = y_coords_diff/myImage.getHeight();
		return ratioX;
	}


	// converts number of pixels to meters in real world for the x axis 
	public static double convToMeter_x(double pixels) {
		return pixels*ratioX();
	}

	//converts number of pixels to meters in real world for the y axis
	public static double convToMeter_y(double pixels) {
		return pixels*ratioY();
	} 

	
	
	// convert number of meters to pixels in map for the x axis
	private static double convToPixel_x(double meters) {
		return meters*(1/ratioX());//Reverse ratio
	}

	// convert number of meters to pixels in map for the y axis
	private static double convToPixel_y(double meters) {
		return meters*(1/ratioY());//Reverse ratio
	}



	public Point3D getBottomLeft() {
		return BottomLeft;
	}


	public Pixel getXYpixelsBegin() {
		return XYpixelsBegin;
	}

	public Point3D getUpperRight() {
		return UpperRight;
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	public String  getFilePath() {
		return filePath;
	}










}
