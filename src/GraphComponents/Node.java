package GraphComponents;

import java.util.ArrayList;

import Geom.Point3D;

/**
 * Class that represent node in graph, implements Comparable
 * the main component of the graph
 * Variables
 * (1) index (to identifies each node)
 * (2) array list of adjacent nodes :nodes having an arch connects them with this node in the graph
 * (3) point3D- point itself on the map in game view (Pixel)
 * (4) distance from source
 * (5) the previous node to this one in the shortest way from the source
 * (4) array list of nodes preceding this node in the shortest way from the source
 * 
 * In addition, each node have a comparator that will help in the
 * shortest path building (compare by distances)
 * 
 * @author Inna and Chen
 */
public class Node implements Comparable<Node>{
	//Variables
	private int index; 
	private ArrayList<Node> adjacent = new ArrayList<>();  
	private Point3D point; 
	private double distance = Double.MAX_VALUE; //as long as there is no edges - distance equals infinity
	private Node prev; 
	private ArrayList<Node> shortestPath = new ArrayList<>(); 


	/**
	 * Constructor
	 * The index will be 0 if this is the source node - (useful for the class of the algorithm),
	 * and will be the last of the node's index on the graph if this is the target.
	 * Each node has it's own location on the map
	 * @param index int
	 * @param location Point3D
	 */
	public Node(int index, Point3D location) {
		this.index = index;
		point = location;
		prev = null;
	}


	/**
	 * Deep copy constructor 
	 * @param other Node
	 */
	public Node(Node other) {
		this.index = other.index;
		this.point = other.point;
		this.distance = other.distance;
		this.prev = other.prev;
		this.shortestPath = other.shortestPath;
		this.adjacent = other.adjacent;
	}



	/**
	 * Adds this node's neighbor in this graph to this adjacent list
	 * @param destination Node
	 */
	public void addDestination(Node destination) {
		adjacent.add(destination);
	}




	/**
	 * Returns String that represent this node
	 */
	public String toString() {
		if(shortestPath == null) return "Node number: [" + index +"] Distance: [" + distance + "]";
		String edges = "";
		for(Node n : adjacent) {
			edges =  edges + " , " + n.getIndex();
		}
		String node = "Node number: [" + index +"] Point: " + point.toString() +  " Distance: [" + distance + "] Adjacent: " + edges  ;
		if(prev != null) node = node + " Prev : [" + prev.getIndex() + "] ShortestPath: " + pathString();
		return node;
	}



	/**
	 * Returns String that represent this node's shortest path
	 * @return path String
	 */
	public String pathString() {
		String path = "";
		for(Node pathNode : shortestPath) {
			path = path + "["+ pathNode.getIndex() + "]";
		}
		return path;
	}



	/**Compare between 2 nodes by there distance from source node
	 * @return positive number if  the first one is bigger
	 * @return negative number if the second one is bigger
	 * @return 0 if equals
	 */
	@Override
	public int compareTo(Node otherNode) {
		double distanceOther = otherNode.getDistance();
		return ((int)(this.distance-distanceOther));
	}



	/// GENERAL GETTERS AND SETTERS ///


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public ArrayList<Node> getAdjacent() {
		return adjacent;
	}

	public ArrayList<Node> getShortestPath() {
		return shortestPath;
	}

	public Point3D getPoint() {
		return point;
	}

	public void setPoint(Point3D point) {
		this.point = point;
	}



}