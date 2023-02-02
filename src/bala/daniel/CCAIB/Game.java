package bala.daniel.CCAIB;

import java.awt.BorderLayout;

import javax.swing.*;

public class Game {
	public static JFrame gameWindow = new JFrame("Crazy Cole's Adventures in Brazil");	//Public scope so Map class can access.
	
	public static JPanel toolbar = new JPanel();
	
	public static InputMap inputMap = toolbar.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);	//Used for key listener
	public static ActionMap actionMap = toolbar.getActionMap();
		
	public static Player player = new Player("Game");	//Calls a new instance of player
	
	public static Map map = new Map(player);	//Calls new instance of map and gives it the reference variable for this class' instance of player.
	public static Camera camera = new Camera(player);
	
	public static JLabel livesLabel = new JLabel("Lives:");
	public static JLabel lives = new JLabel("3");
	
	public static JLabel phonesLabel = new JLabel("Phones:");
	public static JLabel phones = new JLabel(Integer.toString(player.getPhones()));	//Sets the JLabel to the number of phones from player
	
	public static Sound soundtrack = new Sound("./resources/music/soundtrack.wav");
	
	public void init() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {	//Runs GUI
			public void run() {
				guiApp();
			}
		});
		soundtrack.start();
	}
	
	public static void guiApp() {
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//When window is closed, close all running threads
		
		gameWindow.getContentPane().add(map, BorderLayout.CENTER);	//Adds new canvas in the center of the gamewindow.
		gameWindow.getContentPane().add(toolbar, BorderLayout.SOUTH);
		
		toolbar.add(livesLabel);
		toolbar.add(lives);
		toolbar.add(phonesLabel);
		toolbar.add(phones);
		
		inputMap.put(KeyStroke.getKeyStroke("released F"), "F");	//Only triggers when key is released not immediatley after pressed.
		inputMap.put(KeyStroke.getKeyStroke("released J"), "J");
		inputMap.put(KeyStroke.getKeyStroke("released SPACE"), "SPACE");
		
		actionMap.put("F", new KeyboardAction("F"));
		actionMap.put("J", new KeyboardAction("J"));
		actionMap.put("SPACE", new KeyboardAction("SPACE"));
		
		gameWindow.pack();
		gameWindow.setSize(840, 700);
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
	}

	public static void setLabel(JLabel label, String string) {	//Used by other classes to change value of specified JLabel.
		label.setText(string);
	}
}
