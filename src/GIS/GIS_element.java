package GIS;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Inna and Chen
 *
 */
public interface GIS_element {
	public Geom_element getGeom();
	public Meta_data getData();
	public void translate(Point3D vec);

}