package Geom;

/**
 * represent an element includes all the geom elements- circle and point (?)
 * @author Inna and Chen
 *
 */
public class GeomElement implements Geom_element{

	
	private Point3D point;

	//create c-tor off string by spliting by ,
	public GeomElement(String row_point) {
		String[] tokens = row_point.split(",");
		double x = Double.parseDouble(tokens[0]);
		double y = Double.parseDouble(tokens[1]);
		double z = Double.parseDouble(tokens[2]);
		point = new Point3D(x, y, z);
	}
	
	/**
	 * distance in meters from this element point to other point p	 
	 */
	@Override
	public double distance3D(Point3D p) {
		return this.point.distance3D(p);
	}

	@Override
	public double distance2D(Point3D p) {
		return this.point.distance2D(p);
	}
	
	public Point3D getPoint3D() {
		return this.point;
	}
	
	public String toString(){
		return point.toString();
	}

}