package Coords;

import Game.Map;
import Geom.Point3D;

public class Pixel extends Point3D{
	
	public Pixel (String s){
		super(s);
	}

	public Pixel(double x, double y) { //2D always
		super(x,y,0);
	}
	
	public Pixel(double x, double y, double z){
		super(x,y,z);
	}



	//  computes the 3D vector (in pixels) between two pixel point
	public Point3D vector3Dpixel(Point3D pix1, Point3D pix2) {
		double dx = pix2.x()-pix1.x();
		double dy = pix1.y()-pix2.y(); //the direction of the y axis is opposite in the pixels
		double dz = pix2.z()-pix1.z();
		Point3D vector = new Point3D(dx, dy, dz);
		return vector;
	}

	


	@Override 
	public double angleXY_2PI(Point3D pix) {
		Point3D gps0 = Map.convertToPolar(this);
		Point3D gps1 = Map.convertToPolar((Pixel)pix);
		return gps0.angleXY_2PI(gps1);
	}

	@Override 
	public double angleXY(Point3D pix) {
		Point3D gps0 = Map.convertToPolar(this);
		Point3D gps1 = Map.convertToPolar((Pixel)pix);
		return gps0.angleXY(gps1);
	}

	@Override
	public double angleZ(Point3D pix) {
		Point3D gps0 =  Map.convertToPolar(this);
		Point3D gps1 = Map.convertToPolar((Pixel)pix);
		return gps0.angleZ(gps1);
	}

	@Override
	public double north_angle(Point3D pix) {
		Point3D gps0 = Map.convertToPolar(this);
		Point3D gps1 = Map.convertToPolar((Pixel)pix);
		return gps0.north_angle(gps1);
	}

	@Override
	public double up_angle(Point3D pix) {
		Point3D gps0 = Map.convertToPolar(this);
		Point3D gps1 = Map.convertToPolar((Pixel)pix);
		return gps0.up_angle(gps1);
	}

	@Override
	public double up_angle(Point3D pix, double height) {
		Point3D gps0 = Map.convertToPolar(this);
		Point3D gps1 = Map.convertToPolar((Pixel)pix);
		return gps0.up_angle(gps1, height);
	}


}
