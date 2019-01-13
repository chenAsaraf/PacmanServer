package Algorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;
import GraphComponents.Graph;
import GraphComponents.Node;
import GraphComponents.NodeComperator;



/**
 * Main Algorithm class
 * Input: a graph whose nodes are sorted in ascending order according to indexes,
 * where the source node is numbered at 0 ant the targeted node is numbered at graph size -1
 * Output: the input graph when its nodes are initialized with a minimum distance from the source
 * and the shortest way to it
 * Uses Dijkstra's algorithm
 * @author Chen and Inna
 *
 */
public class Dijkstra {

	private Graph graph; //input
	private Node source; 
	private Node target;
	private NodeComperator compareNodes = new NodeComperator();
	//Algorithm variables
	private ArrayList<Node> setteled = new ArrayList<>();
	private PriorityQueue<Node> Q;
	private ArrayList<Node> QueueVeritces;


	/**
	 * Constructor
	 * initialize the Class variables and execute the main calculation
	 * @param G Graph
	 */
	public Dijkstra(Graph G) {
		this.graph = G;
		this.source = G.getSource();
		this.target = G.getTarget();
		this.QueueVeritces = G.getVertices();
		//execute the main calculation of the algorithm
		calculateShortestPathFromSource();		
		setNodesPathes();
	}

	/*Private method: the main loop of Dijkstra algorithm */
	private void calculateShortestPathFromSource() {
		int V = graph.getVertices().size();
		Q = new PriorityQueue<>(V, compareNodes);
		setQ();
		source.setDistance(0);
		QueueVeritces.sort(compareNodes);
		
		while(Q.size() != 0) {
			Node current = Q.poll();
			QueueVeritces.remove(0);
			setteled.add(current);
			for(Node adjacent : current.getAdjacent()) {
				relaxetionStep(current, adjacent);
			}
			QueueVeritces.sort(compareNodes);
		}
	}


	
	/* Private method: set the priority queue for the algorithm*/
	private void setQ() {
		Q.clear();
		for(Node node : QueueVeritces) {
			Q.add(node);
		}
		
	}


	
	/* Private method: for the algorithm main loop
	for each of the adjacent of the current node do relaxation - reduce the distance value if possible*/
	private void relaxetionStep(Node current, Node adjacent) {
		double edgeWeight = current.getPoint().distance2D(adjacent.getPoint());
		double relaxationDistance = current.getDistance() + edgeWeight;
		if(adjacent.getDistance() > relaxationDistance) { 
			adjacent.setDistance(relaxationDistance);//relaxation
			adjacent.setPrev(current);
		}
		setQ();
	}


	/*
	 * Private method: after the main calculation of the graph
	 * Initial the shortest route from the source to each node according to the previous of each
	 */
	private void setNodesPathes() {
		setteled.sort(compareNodes);
		for(Node node : setteled) {
			node.getShortestPath().add(node);
			if( node.getPrev() != null) {
				//				if(node.getPrev().equals(source)) node.getShortestPath().add(source); //near the source
				if ( ! (node.getPrev().equals(source)) ) {
					//					node.addPreviousOf(node); //add all previous path's nodes
					node.getShortestPath().add(node.getPrev());
					for(Node prevPrev : node.getPrev().getShortestPath()) {
						if(!prevPrev.equals(node.getPrev()))
							node.getShortestPath().add(prevPrev);
					}
				}
			}
		}	
	}

	
	
	//// GETTERS AND SETTERS ////
	
	public String shortestPath() {
		return target.pathString();
	}

	/**
	 * Returns the shortest distance to the target from the source
	 * according to this class calculation
	 * @return distance double
	 */
	public double shortestPathDistance() {
		 double distance = target.getDistance();
		 return distance;
	}

	/**
	 * Returns the shortest path to the target from the source
	 * according to this class calculation
	 * @return shortest ArrayList
	 */
	public ArrayList<Node> getShortestPath() {
		  ArrayList<Node> shortest = target.getShortestPath();
		  return shortest;
	}

	/**
	 * Returns String represents this graph AFTER Dijkstra
	 */
	public String toString() {
		String graph = "";
		for(Node node : setteled) {
			graph = graph +  node.toString() + " \n";
		}
		return graph;
	}


}
