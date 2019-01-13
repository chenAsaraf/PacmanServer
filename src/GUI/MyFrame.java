package GUI;


import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Game.Game;
import Game.Map;
import Robot.Play;

public class MyFrame extends JFrame	{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu drawGame;
	private JMenu playGame;
	
	private JMenuItem openItem;
	private JMenuItem manualItem;
	private JMenuItem autoItem;
	private JMenuItem stopItem;
	private JMenuItem  playerItem;
	
	private String mapPath= "images/Ariel1.png";
	private Play play;
	private String mapData;
	private GameFrame board;
	private static int IDplayer = 0;



	/**
	 * Constructor
	 */
	public MyFrame() {
		initComponents();
		createMenu();
		initGui();
		//initBoard();
	}


	//Initializes frame elements
	private void initComponents() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,  ActionEvent.CTRL_MASK));
		fileMenu.add(openItem);
		menuBar.add(fileMenu);
		drawGame = new JMenu("Draw game");
		menuBar.add(drawGame);
		playerItem = new JMenuItem("Me");
		drawGame.add(playerItem);
		playGame = new JMenu("Play");
		stopItem = new JMenuItem("Stop");
		autoItem = new JMenuItem("Auto");
		manualItem = new JMenuItem("Manual");
		playGame.add(autoItem);
		playGame.add(manualItem);
		playGame.add(stopItem);
		menuBar.add(playGame);
	}




	//Initializes the frame buttons and their operations
	private void createMenu() {
		//set the open button
		openItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				readFileDialog();
			}
		});
		//set the Player button
		playerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent p) {
				board.addPlayer();
			}
		});
		//set the simulation button
		manualItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.manual();
			}
		});
		//set the run button
		autoItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.runAuto();
			}
		});
		//set the stop button
		stopItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.Stop();
			}
		});

	}



	/**
	 * Make the Frame features
	 */
	public void initGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1443, 682); //set init size
	}


	//when clicking on Open - reads from a CSV file and initialize the game
	private void readFileDialog() {
		FileDialog fileDialog = new FileDialog(this, "Open text file", FileDialog.LOAD);
		fileDialog.setFile("*.csv");
		fileDialog.setDirectory("C:");
		fileDialog.setFilenameFilter(new FilenameFilter() {
			@Override 
			public boolean accept (File dir, String name) {
				return name.endsWith(".csv");
			}	
		});
		fileDialog.setVisible(true);
		String folder = fileDialog.getDirectory();
		String fileName = fileDialog.getFile();
		if(fileName!=null) {
			System.out.println("File opened: " + folder + fileName);
		}
		play = new Play(folder + fileName);

		setYourIds();
		setMapAndBoard();
		initBoard();
		repaint();
	}


	/**
	 * When the game is loaded, the server requires the player's 
	 * identity numbers
	 */
	public void setYourIds(){
		///check the ability to make it one or tow or three
		String IDstring = JOptionPane.showInputDialog(null, "Set ID's players: ", "Lets start to play!", JOptionPane.QUESTION_MESSAGE);
		if (IDstring == null) {
			System.out.println("The user canceled");
			setYourIds();
		}

		IDplayer = Integer.parseInt(IDstring);
		play.setIDs(IDplayer);


	}

	/**
	 * Get the GPS coordinates of the "arena" from the server
	 * initial the game's map
	 * then send it to the setBoard function
	 */
	public void setMapAndBoard() {
		mapData = play.getBoundingBox();
		Map map = new Map(mapPath,mapData);
		setBoard(map);
	}


	/**
	 * Get the game-board data from the server
	 */
	public void setBoard(Map map) {
		ArrayList<String> boardData = play.getBoard();
		Game game = new Game(map, boardData);
		board = new GameFrame(game, play);
		System.out.println("board has been initial");
	}


	/**
	 * Adds the JPanel board 
	 */
	public void initBoard() {
		add(board);
	}



	/**
	 *Return the players id (for the SQL)
	 */
	public static int getPlayerId(){
		return IDplayer;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame ex = new MyFrame();
		ex.setVisible(true);
	}

}
