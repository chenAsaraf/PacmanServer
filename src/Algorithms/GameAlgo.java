package Algorithms;

import java.util.ArrayList;
import Game.Game;
import GameEntities.Fruit;
import Geom.Point3D;
import GraphComponents.Graph;
import GraphComponents.Node;


/**
 * This class surrounds the Dijkstra class to match the algorithm to the GameFrame requires
 * input: the game
 * output: pathSolution : ArrayList that contains all the points including the next fruit
 * (next fruit is the last point in this array)
 * @author Inna and Chen
 *
 */
public class GameAlgo {
	
	private Graph G;
	private Game game;
	private ArrayList<Point3D> pathSolution = new ArrayList<>(); //output

	/**
	 * Constructor
	 * execute all the class calculation
	 * @param game
	 */
	public GameAlgo(Game game) {
		this.game = game;
		Node nextFruit =  findFruitTarget();
		setPathSolution(nextFruit);
	}


	/*
	 * Private method:
	 * For each of the fruit's game we will check who is closest to the main player.
	 * Each iteration will recreate a graph when the source node is the player position
	 * and the target node is one of the fruits
	 */
	private Node findFruitTarget(){
		Dijkstra dijkstra;
		Point3D source = game.getMe().getLocation(); //source point:
		double minimumDistance = Double.MAX_VALUE;
		Node tragetFruit = new Node(0 , null); 

		for(Fruit fruit : game.getFruits()) {
			Point3D target = fruit.getLocation();
			G = new Graph(source, target, game.getBoxes()); //crate new graph
			dijkstra = new Dijkstra(G);  //do Dijkstra calculate to this graph
			double shortestPath = dijkstra.shortestPathDistance();
			if(shortestPath < minimumDistance) { 
				minimumDistance = shortestPath;
				tragetFruit = G.getTarget();  //save the closer one foe later
			}
		}
		return tragetFruit;
	}


	/**
	 * Private method:
	 * after executing the Dijkstra algorithm - set the pathSolution points
	 * @param targetFruit Node
	 */
	private void setPathSolution(Node targetFruit) {
		int pathSize =  targetFruit.getShortestPath().size();
		for (int i = pathSize -1; i >  -1; i--) {
			Node inWay = targetFruit.getShortestPath().get(i);
			pathSolution.add(inWay.getPoint());
		}
		System.out.println("solution: " + pathSolution.toString());
	}
	

	
	/**
	 * The output of GameAlgo:
	 * return the pathSolution : path of Point3D to the target
	 * @return pathSolution ArrayList
	 */
	public ArrayList<Point3D> getPathSolution() { 
		return pathSolution;
	}


}
