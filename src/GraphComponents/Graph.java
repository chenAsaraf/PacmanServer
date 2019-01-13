package GraphComponents;

import java.util.ArrayList;
import GameEntities.Box;
import Geom.Point3D;

/**
 * This class represents a basic graph.
 * Graph elements: Source node, target node, and a list of nodes containing the all
 * vertices including source and target vertex.
 * 
 * @author Inna and Chen
 *
 */
public class Graph {

	private ArrayList<Node> vertices = new ArrayList<>();
	private Node source;
	private Node target;
	private Intersection tester; // help to sets the graph edges
	private int index = 0; // nodes index when adding them to the graph


	/**
	 * Constructor
	 * input: Point3D of main player, Point3D of fruit target, ArrayList of Boxes in the game
	 * @param source Point3D
	 * @param target Point3D
	 * @param boxes ArrayList
	 */
	public Graph(Point3D source , Point3D target, ArrayList<Box> boxes) {
		tester = new Intersection(boxes);
		setVertices(source, target, boxes);
		addEdges();
	}



	/*
	 * Private method
	 * Set all the graph vertices: first the source, then all boxes vertices
	 * that available, then target 
	 */
	private void setVertices(Point3D source, Point3D target, ArrayList<Box> boxes) {
		Node sourceNode = new Node(index++, source);
		this.source = sourceNode;
		vertices.add(sourceNode);

		// add all the boxes's vertex that not inside the other boxes
		for(Box box : boxes) {
			double rightX = box.getMax().x() + 2;//to not touch in the box
			double leftX = box.getMin().x() - 2;
			double bottomY = box.getMin().y() + 2;
			double upperY = box.getMax().y() - 2;
			Point3D UpperRight = new Point3D(rightX, upperY);
			Point3D UpperLeft = new Point3D(leftX, upperY);
			Point3D BottomRight = new Point3D(rightX, bottomY);
			Point3D BottomLeft = new Point3D(leftX, bottomY);

			//test if have intersection then add the vertices
			testAndAdd(box, UpperRight);
			testAndAdd(box, UpperLeft);
			testAndAdd(box, BottomRight);
			testAndAdd(box, BottomLeft);
		}

		Node targetNode = new Node(index++, target);
		this.target = targetNode;
		vertices.add(targetNode);
	}



	/*
	 * Private method
	 * Checks if the boxe's vertex available
	 */
	private void testAndAdd(Box box , Point3D point) {
		if (! tester.haveIntersect(box, point)) {
			Point3D pointWithMeter = new Point3D(point.x(), point.y() , 0); 
			Node vertex = new Node(index, pointWithMeter);
			vertices.add(vertex);
			index++;
		}
	}


	/*
	 * Private method
	 * sets all the edges's graph - all the adjacent ArrayList of the vertices 
	 */
	private void addEdges() {
		for(Node current : vertices) {
			for(Node other : vertices) { 
				if( ! current.equals(other)) {
					Point3D currentPoint = current.getPoint();
					Point3D otherPoint = other.getPoint();
					if( ! tester.haveIntersect(currentPoint, otherPoint)) {
						//						if( ! hasEdge(current, other)) {
						current.addDestination(other);
						//						}
					}
				}
			}
		}
	}



	/**
	 * Checks if the line between the 2 points already set as an edge in the graph
	 * ask the destination node if he already have the current as adjacent
	 * @param current Node
	 * @param destination Node
	 * @return hasEdge boolean
	 */
	public boolean hasEdge(Node current, Node destination) {
		boolean hasEdge = false;
		for (int i = 0; i < destination.getAdjacent().size(); i++) {
			Node destAdjacent = destination.getAdjacent().get(i);
			hasEdge = hasEdge || destAdjacent.equals(current);
		}
		return hasEdge;
	}




	/**
	 * Add the node to the vertices of the graph
	 * @param nodeA Node
	 */
	public void addNode(Node nodeA) {
		vertices.add(nodeA);
	}



	/**
	 * Get the node with this index
	 * @param index int
	 * @return
	 */
	public Node getNodeByIndex(int index) {
		if(index > vertices.size()) return null;
		return vertices.get(index);			
	}


	/**
	 * Returns the String represent this graph
	 * @return graph String
	 */
	public String toString() {
		String graph = "";
		for(Node node : vertices) {
			graph = graph + " , " + node.toString() + " \n";
		}
		return graph;
	}





	//// GENERAL GETTERS AND SETTERS ////

	public ArrayList<Node> getVertices() {
		return vertices;
	}

	public Node getSource() {
		return source;
	}


	public Node getTarget() {
		return target;
	}







}
