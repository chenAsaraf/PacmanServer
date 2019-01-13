package Game;

import java.util.ArrayList;
import java.util.Iterator;

import GameEntities.Box;
import GameEntities.Fruit;
import GameEntities.Ghost;
import GameEntities.Packman;
import GameEntities.Player;

/**
 * This class represents the components of the game
 * @author Inna and Chen
 *
 */
public class Game {
	private Map map;
	private ArrayList<Packman> packmans = new ArrayList<>();
	private ArrayList<Fruit> fruits = new ArrayList<>();
	private ArrayList<Ghost> ghosts = new ArrayList<>();
	private ArrayList<Box> boxes = new ArrayList<>();
	private Player me;


	/**
	 * Constructor
	 * the class has the ability to build from a CSV file
	 * @param path
	 */
	public Game(Map map, ArrayList<String> boardData) {
		this.map = map;
		setInitState(boardData);
	}


	
	public void setInitState(ArrayList<String> boardData) {
		Iterator<String> i = boardData.iterator();
		while(i.hasNext()) {
			String line = i.next();
			if(line.substring(0,1).equals("M")){
				me = new Player(line); 
			}
			if(line.substring(0,1).equals("P")){
				Packman p  = new Packman(line);
				packmans.add(p);
			}
			else if(line.substring(0,1).equals("F")){
				Fruit f = new Fruit(line);
				fruits.add(f);
			}
			else if(line.substring(0,1).equals("B")){
				Box f = new Box(line);
				boxes.add(f);
			}
			else if(line.substring(0,1).equals("G")){
				Ghost f = new Ghost(line);
				ghosts.add(f);
			}
		}
	}
	


	public void setState(ArrayList<String> boardData) {
		ArrayList<Packman> newPackmans = new ArrayList<>();
		ArrayList<Fruit> newFruits = new ArrayList<>();
		ArrayList<Ghost> newGhosts = new ArrayList<>();
		Iterator<String> i = boardData.iterator();
		while(i.hasNext()) {
			String line = i.next();
			if(line.substring(0,1).equals("M")){
				me.setLocation(line); 
			}
			if(line.substring(0,1).equals("P")){
				Packman p  = new Packman(line);
				newPackmans.add(p);
			}
			else if(line.substring(0,1).equals("F")){
				Fruit f = new Fruit(line);
				newFruits.add(f);
			}
			else if(line.substring(0,1).equals("G")){
				Ghost f = new Ghost(line);
				newGhosts.add(f);
			}
		}
		packmans = newPackmans;
		fruits = newFruits;
		ghosts = newGhosts;
	}


	public ArrayList<Packman> getPackmans(){
		return packmans;
	}


	public ArrayList<Fruit> getFruits() {
		return fruits;
	}


	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public ArrayList<Box> getBoxes() {
		return boxes;
	}
	
	public Map getMap() {
		return map;
	}

	public Player getMe() {
		return me;
	}


	public void setMe(Player me) {
		this.me = me;
	}

	
	public String getPathImageGhost() {
		if(ghosts.size() > 0)
		return ghosts.get(0).getImage();
		else return "";
	}
	
	public String getPathImagePackman() {
		if(packmans.size() > 0)
		return packmans.get(0).getImage();
		else return "";
	}
	
	
	public String getPathImageFruit() {
		if(fruits.size() > 0)
		return fruits.get(0).getImage();
		else return "";
	}
	
	public String getPathImagePlayer() {
		return me.getImage();
	}



}
