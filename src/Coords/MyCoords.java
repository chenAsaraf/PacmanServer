package Coords;

import Geom.Point3D;

/**
 * This class represents a basic coordinate system converter, including:
 * 1. The 3D vector between two lat, lon, alt points 
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates
 * @author Inna and Chen
 */


public class MyCoords implements coords_converter {

	private static final int earthRadius = 6371*1000;
	private static final double PI = Math.PI;



	/**
	 * Computes a new point which is the gps point
	 * transformed by a 3D vector (in meters)
	 * @param gps Point3D
	 * @param local_vector_in_meter Point3D
	 * @return answer Point3D polarity point after adding
	 */	
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double lat = gps.x();
		double x = local_vector_in_meter.x();
		double insideArcsin = (x/earthRadius);
		double diffRadianX = Math.asin(insideArcsin);
		double diffLat = (diffRadianX*180)/PI;
		double newLat = lat + diffLat;
		double y = local_vector_in_meter.y();
		double lon = gps.y();
		double insideArcsin2 = (y / ( earthRadius * lonNorm(lat) ));
		double diffRadianY = Math.asin(insideArcsin2);
		double diffLon = (diffRadianY * 180)/PI;
		double newLon = lon + diffLon;
		double z = local_vector_in_meter.z();
		double alt = gps.z();
		double newAlt = alt + z;		
		Point3D answer = new Point3D(newLat, newLon, newAlt);
		return answer;
	}


	/** computes the 3D distance (in meters) between the two GPS points
	 *@param gps0 Point3D
	 *@param gps0 Point3D
	 *@return distance3d double
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double xMeter = distance_x(gps0, gps1);
		double yMeter = distance_y(gps0, gps1);
		double xPow = Math.pow(xMeter, 2);
		double yPow = Math.pow(yMeter, 2);
		double distance3d = Math.sqrt(xPow+yPow);
		return distance3d;
	}

	/**
	 * Calculates the metric distance on the X axis between 2 GPS point
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	public static double distance_x(Point3D gps0, Point3D gps1) {
		double diffLat = diffX(gps0, gps1);
		double diffRadianLat = (diffLat*PI)/180;
		double xMeter = diffRadianLat * earthRadius;
		return xMeter;
	}

	/**
	 * Calculates the metric distance on the Y axis between 2 GPS point
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	public static double distance_y(Point3D gps0, Point3D gps1) {
		double lonNormal = lonNorm(gps0.x());
		double diffLon = diffY(gps0, gps1);
		double diffRadianLon = (diffLon*PI)/180;
		double yMeter = Math.sin(diffRadianLon) * lonNormal * earthRadius;
		return yMeter;
	}


	/**
	 * Calculates the metric distance on the Z axis between 2 GPS point
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	public static double distance_z(Point3D gps0, Point3D gps1) {
		return gps1.z() - gps0.z();
	}


	/** computes the 3D vector (in meters) between two GPS point
	 * @param gps0
	 * @param gps1
	 * @return vectorDistance Point3D x=north y=east z=up
	 * @throws RuntimeException
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		if(!(isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1))) {
			throw new RuntimeException("not a valid points");
		}
		double x = distance_x(gps0, gps1);
		double y = distance_y(gps0, gps1);
		double z = diffZ(gps0, gps1);
		Point3D vector3D = new Point3D (x, y, z); 
		return vector3D;
	}


	/** computes the polar representation of the 3D vector be from point gps0 to gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double azimuth = azimuth(gps0, gps1);
		double distance = distance3d(gps0 , gps1);
		double elevation =degrees(diffZ(gps0, gps1))/distance - distance/(2*earthRadius);

		double [] polar = new double[3];
		polar[0] = azimuth;
		polar[1] = elevation;
		polar[2] = distance;
		return polar;
	}



	/**
	 * Finding Azimuth by coordinates between this point gps0 to target point gps1
	 * see more in: https://en.wikipedia.org/wiki/Azimuth 
	 * @param gps0 Point3D
	 * @param gps1 Point3D
	 * @return azimuthDegree double
	 */
	public double azimuth(Point3D gps0,Point3D gps1) {
		double diffY =diffY(gps0, gps1);
		double y = Math.sin(radians(diffY));
		double normal = lonNorm(gps0.x());
		double a = y*normal;
		double b = Math.sin(radians(gps1.x()))*Math.cos(radians(gps0.x()))-Math.cos(radians(gps1.x()))*Math.sin(radians(gps0.x()))*Math.cos(radians(diffY));
		double azimuthDegree = degrees(Math.atan2(a,b));
		if (azimuthDegree<0) return azimuthDegree+360;
		else return azimuthDegree;

	}



	/**
	 * return true iff this point is a valid lat, lon , alt coordinate:
	 * [-90,+90],[-180,+180],[-450, +inf]
	 * @param p Point3D
	 * @return answer boolean
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean lat = p.x() <= 90 &&  p.x() >= -90;
		boolean lon = p.y() <= 180 &&  p.x() >= -180;
		boolean alt = p.z() >= -450;
		boolean answer = lat && lon && alt;
		return answer;
	}

	private static double diffX(Point3D gps0 ,Point3D gps1)
	{
		return gps1.x()  - gps0.x() ;
	}


	private static double diffY(Point3D gps0 ,Point3D gps1)
	{
		return gps1.y()  - gps0.y() ;
	}


	private double diffZ(Point3D gps0 ,Point3D gps1)
	{
		return gps1.z() - gps0.z() ;
	}

	/* transform from radians to angles */
	private static double degrees(double a)
	{
		return Math.toDegrees(a);
	}

	/* transform from angles to radians*/
	private static double radians(double a)
	{
		return Math.toRadians(a);
	}

	/*compute the normal of point with this lat value*/
	private static double lonNorm(double lat){
		return Math.cos((lat*PI)/180);
	}




}
