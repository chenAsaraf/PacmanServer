package GraphComponents;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import GameEntities.Box;
import Geom.Point3D;


/**
 * The class examines whether the direct path between two Point3D
 * is cut by one of the obstacles.
 * The class uses Java swing objects 
 * Converts the boxes into swing rectangles, converts the line of sight into swing lines
 * @author Inna and Chen
 *
 */
public class Intersection {

	private ArrayList<Rectangle2D.Double> boxesByRectangles = new ArrayList<>();
	private int[] index;



	/**	
	 * Constructors
	 * Gets list of boxes
	 * Initializes all the rectangles that represent the boxes
	 * @param boxes ArrayList<Box> 
	 */
	public Intersection(ArrayList<Box> boxes) {
		index = new int[boxes.size()];
		int counter = 0;
		for(Box box : boxes) {
			int width = Math.abs((box.getMax().ix() - box.getMin().ix()));
			int height = Math.abs((box.getMin().iy() - box.getMax().iy()));
			int x = box.getMin().ix();
			int y = box.getMax().iy(); 
			Rectangle2D.Double boxByRectangle = new Rectangle2D.Double(x , y , width, height);
			boxesByRectangles.add(boxByRectangle);
			index[counter] = box.getID();
			counter++;
		}
	}


	/**
	 * Determines whether there is a direct way without boxes between this two points
	 * @param source Point3D
	 * @param target Point3D
	 * @return haveIntersect boolean
	 */
	public boolean haveIntersect(Point3D source, Point3D target) {
		//set the sight-line between the source to the target
		Line2D.Double sightLine = new Line2D.Double(source.x() - 2, source.y() -2, target.x(), target.y()); 
		boolean haveIntersect = false;
		for(Rectangle2D box : boxesByRectangles) { //if one of the box blocks the line - return false
			haveIntersect = (haveIntersect || box.intersectsLine(sightLine));
		}
		return haveIntersect;
	}


	/**
	 * Determines whether this point representing a vertex of thisBox is contained
	 * within one of the other boxes
	 * @param thisBox Box
	 * @param vertex Point3D
	 * @return haveIntersect boolean
	 */
	public boolean haveIntersect(Box thisBox,Point3D vertex) {
		//Convert the vertex from Point3D object to Point- Swing Java object
		Point vertexSwing = new Point(vertex.ix(), vertex.iy());
		boolean haveIntersect = false;
		int checkIfThisIsTheBox = 0;
		for(Rectangle2D box : boxesByRectangles) {
			if(thisBox.getID()!=index[checkIfThisIsTheBox]) {
				haveIntersect = (haveIntersect || box.contains(vertexSwing));
				checkIfThisIsTheBox++;
			}
		}
		return haveIntersect;
	}



}
