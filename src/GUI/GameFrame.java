package GUI;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Algorithms.GameAlgo;
import Coords.Pixel;
import Game.Game;
import Geom.Point3D;
import Robot.Play;
import Game.Map;
import Game.Sql;
import GameEntities.Box;
import GameEntities.Fruit;
import GameEntities.Ghost;
import GameEntities.Packman;
import GameEntities.Player;


public class GameFrame extends JPanel implements MouseListener, ActionListener, ComponentListener{

	private static final long serialVersionUID = 1L;

	public int Image_initial_Width; 
	public int Image_initial_Height;
	/*pictures buffered images:*/
	private BufferedImage mapImage;
	private BufferedImage packmanImage;
	private BufferedImage fruitImage;
	private BufferedImage ghostImage;
	private BufferedImage playerImage;
	/*repaint's coordinates:*/
	private int x = -1;
	private int y = -1;
	private double ratioY;
	private double ratioX;
	/*auto run variables:*/
	private GameAlgo algorithm;
	private ArrayList<Point3D> path = new ArrayList<>();
	private Point3D next = new Point3D(0,0,0);
	private int sizePathCounter = -1;;
	private int DELAY = 10;
	private Timer timer ;
	/*server:*/
	private Play play;

	private Game game;
	private int counter;
	private String status = "";


	/**
	 * Constructor
	 * Create the frame.
	 */
	public GameFrame(Game game, Play play) {
		this.play = play;
		this.game = game;
		mapImage = game.getMap().getMyImage();
		Image_initial_Width = mapImage.getWidth();
		Image_initial_Height = mapImage.getHeight();
		ratioY = 1;
		ratioX = 1;
		createGui();
		initImages();
		this.setSize(Image_initial_Width, Image_initial_Height);
	}

	/*Private method - set all the BufferedImage once for the repaint method invoke later*/
	private void initImages() {
		//set packman image
		try {
			packmanImage= ImageIO.read(new File (game.getPathImagePackman()));
		}catch (IOException e) {
			e.printStackTrace();
		}	
		//set fruit image
		try {
			fruitImage= ImageIO.read(new File (game.getPathImageFruit()));
		}catch (IOException e) {
			e.printStackTrace();
		}	
		//set ghost image
		try {
			ghostImage= ImageIO.read(new File (game.getPathImageGhost()));
		}catch (IOException e) {
			e.printStackTrace();
		}	
		//set player image
		try {
			playerImage= ImageIO.read(new File (game.getPathImagePlayer()));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*create the GUI*/
	private void createGui() {
		timer = new Timer(DELAY, this);
		addMouseListener(this); 
		addComponentListener(this);
	}

	
	
	


	//              Actions performed from the menu              //


	/**
	 * Changes the status for the paint function
	 * Called by MyFrame 
	 */	
	public void addPlayer() {
		status = "DRAW_ME";
	}



	/**
	 * play manually
	 * Changes the status for the mouse listener 
	 * Called by MyFrame 
	 */	
	public void manual() {
		if(game.getFruits().size() == 0) status = "EXCEPTION";
		else{
			status = "DIRECTION";
		}
	}


	/**
	 * Play auto
	 * Changes the status for the repaint method
	 * make the timer start
	 * then each DELAY seconds the step() method called, and then repaint() again 
	 */
	public void runAuto() {
		if(play.isRuning()) {
			status = "AUTO";
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//if the solution path havn't initialize yet or we got to all the points in the last path
		if((path.size() == 0) || (sizePathCounter == path.size())){
			algorithm = new GameAlgo(this.game);
			path = algorithm.getPathSolution();
			sizePathCounter = 0;
		}

		Point3D myLocation = game.getMe().getLocation();

		if(path.size() > 0) {
			if(sizePathCounter <  path.size() ){ //if there still fruits the player didn't eat
				next = path.get(sizePathCounter);
				boolean sameX = (Math.abs(myLocation.ix() - next.ix()) <= 2);
				boolean sameY = (Math.abs(myLocation.iy() - next.iy()) <= 2);
				if (sameX && sameY) { //if this is the point of the fruit
					sizePathCounter++;
					if( sizePathCounter <  path.size() ){
						next = path.get(sizePathCounter);
					}
				}
			}
			step();
		}
	}

	/*
	 * Rotate the player in AUTO status
	 */
	private void step() {
		if(play.isRuning()) {
			double azimuth = game.getMe().Azimuth(next);
			play.rotate((azimuth));  
			game.setState(play.getBoard());
			repaint();
		}
		else {
			status = "GAME_OVER";
			System.out.println("game is over");
			timer.stop();
			repaint();
		}
	}


	/**
	 * Stop the timer
	 */
	public void Stop() {
		status = "STOP";
		timer.stop();
	}



	/**
	 * This function active after the user click on one of the Draw Item
	 * and then you click on the board
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(status.equals("DRAW_ME")) {	
			/// need to add exception if putted on boxes
			if( game.getMe().getLocation().x() < 0) { //If not initialized 
				x = arg0.getX();
				y = arg0.getY();
				counter++;
				Player me = new Player(new Pixel(x,y),counter); 
				game.setMe(me);
				Point3D polarLocation =new Point3D(x,y,0);
				polarLocation = Map.convertToPolar(polarLocation);
				play.setInitLocation(polarLocation.y(),polarLocation.x());
				repaint();
				startGame();
			}
		}

		else if(status.equals("DIRECTION")) {
			if(play.isRuning()) {
				x = arg0.getX();
				y = arg0.getY();
				Point3D click =new Point3D(x,y,0);
				double azimuth = game.getMe().Azimuth(click);
				play.rotate((azimuth));  
				game.setState(play.getBoard());
				repaint();
			}
			else if(!play.isRuning()) {
				status = "GAME_OVER";
				repaint();

			}
		}

	}

	/*Manual interface- change the orientation by clicking on the screen*/
	private void startGame() {
		status = "DIRECTION";
		play.start(); // start the server
	}



	/**
	 * Panel painting function
	 * Refers to the given status according to which the board is painted
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//always draw the map
		g.drawImage(mapImage, 0,0,getWidth(),getHeight(),this);
		//exception
		if(status.equals("EXCEPTION")) {
			String ex = "EXCEPTION";
			g.setColor(Color.red);
			g.drawString(ex ,40 ,40);
		}

		//run
		if(status.equals("AUTO")) {
			timer.start();
		}
		//stop the run
		if(status.equals("STOP")) {
			timer.stop();
		}

		//stop the game
		if(status.equals("GAME_OVER")) {
			String gemeOver = "THE GAME IS OVER!";
			g.drawString(gemeOver, 40, 40);
			Sql sql = new Sql();
			sql.getData();

		}

		//paint the component
		for (Packman p : game.getPackmans()) {
			if(Map.is_Valid_Point(p.getLocation())) {
				x = (int)p.getLocation().x();
				y = (int)p.getLocation().y();
				g.drawImage(packmanImage,(int)(x*ratioX) ,(int)(y*ratioY) ,30 , 30,this);
			}
		}

		for (Fruit f : game.getFruits()) {
			if( Map.is_Valid_Point(f.getLocation())) {
				x = (int)f.getLocation().x();
				y = (int)f.getLocation().y();
				g.drawImage(fruitImage, (int)(x*ratioX) ,(int)(y*ratioY) ,25 , 25 ,this);
			}
		}

		for (Ghost gh : game.getGhosts()) {
			if( Map.is_Valid_Point(gh.getLocation())) {
				x = (int)gh.getLocation().x();
				y = (int)gh.getLocation().y();
				g.drawImage(ghostImage, (int)(x*ratioX) ,(int)(y*ratioY) ,30 , 30 ,this);
			}
		}

		for (Box b : game.getBoxes()) {
			if( Map.is_Valid_Point(b.getMin()) && Map.is_Valid_Point(b.getMax()) ) {
				x = (int)b.getMin().x();
				y = (int)b.getMax().y();
				int width = Math.abs((int)(b.getMax().x() - b.getMin().x()));
				int height = Math.abs((int)(b.getMin().y() - b.getMax().y()));
				g.fillRect((int)(x*ratioX), (int)(y*ratioY), (int)(width*ratioX), (int)(height*ratioY));
			}
		}

		Packman p = game.getMe();
		x = (int)p.getLocation().x();
		y = (int)p.getLocation().y();
		g.drawImage(playerImage, (int)(x*ratioX) ,(int)(y*ratioY) ,30 , 30 ,this);


	}





	/**
	 * Adjusts the Packmas and fruits in the screen according to its size
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		ratioX = (double)this.getWidth()/(double)Image_initial_Width;
		ratioY = (double)this.getHeight()/(double)Image_initial_Height;
		repaint();
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}


}
