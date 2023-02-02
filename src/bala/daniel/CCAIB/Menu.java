package bala.daniel.CCAIB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu {
	public static JFrame window = new JFrame("Crazy Cole's Adventures in Brazil - Main Menu");
	
	public static JPanel buttonPanel = new JPanel();
	
	public static JButton playButton = new JButton("Play");
	
	public static ButtonGroup optionGroup = new ButtonGroup();
	public static JPanel optionPanel = new JPanel();
	public static JRadioButton normalButton = new JRadioButton("Normal");
	public static JRadioButton ultraHDButton = new JRadioButton("UltraHD");
	
	public static Player fakePlayer = new Player("Menu");
	public static Camera camera = new Camera(fakePlayer);
	public static Map menuMap = new Map(fakePlayer);
	
	public static Sound introSoundtrack = new Sound("./resources/music/intro.wav");
	
	public void init() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				guiApp();
			}
		});
		introSoundtrack.start();	//Start soundtrack
	}
	
	public static void startGame() {
		fakePlayer.setX(1500);
		if (normalButton.isSelected()) {
			Main.mode = 1;
		} else if (ultraHDButton.isSelected()) {
			Main.mode = 2;
		}
		
		introSoundtrack.stop();	//Stop intro soundtrack when game starts
		
		window.setVisible(false);	//Closes window
		window.dispose();	//Closes all running threads
		
		Camera.reset();	//So two camera timers aren't running at the same time
		
		Main.runGame();	//Run game
	}
	
	public static void guiApp() {
		ButtonHandler onClick = new ButtonHandler();	//Checks for when button is pressed
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		window.getContentPane().add(menuMap, BorderLayout.CENTER);
		window.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		optionGroup.add(normalButton);
		optionGroup.add(ultraHDButton);
		
		optionPanel.add(normalButton);
		optionPanel.add(ultraHDButton);
		
		buttonPanel.add(playButton);
		buttonPanel.add(optionPanel);
		
		normalButton.setSelected(true);
		
		playButton.addActionListener(onClick);
		
		window.pack();
		window.setSize(840, 700);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	private static class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == playButton) {
				Menu.startGame();
			}
		}
	}
}
