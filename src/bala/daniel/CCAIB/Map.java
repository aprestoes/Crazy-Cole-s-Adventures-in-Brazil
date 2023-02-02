package bala.daniel.CCAIB;

import java.awt.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel {
	private Image map1;
	private Image map2;
	
	//Main maps
	private Image mapGrass;	//Images for the map
	private Image mapWinter;
	private Image mapBridge;
	
	//UltraHD Maps
	//private Image mapClub;
	private Image mapChicken;
	//private Image mapSanta;
	private Image mapHorse;
	private Image mapDuck;
	
	private Font titleFont = new Font("Comic Sans MS", Font.BOLD, 25);
	private Font descriptionFont = new Font("Comic Sans MS", Font.ITALIC, 16);
	
	
	private Image phoneImage;
	private int phoneX;
	
	private Image frames[] = new Image[6];
	private int currentFrame = 0;
	
	private Player player;
	
	private Random random = new Random();
	private int randomInt;
	
	public Map(Player gamePlayer) {	//Whenever new instance of Map is called
		init();
		player = gamePlayer;
	}
	
	public void init() {
		try {	//Load images
			if ((Main.mode == 1) || (Main.mode == 3)) {
				for (int i = 0; i < 6; i++) {	//Loads all normal running frames
					frames[i] = getToolkit().getImage(getClass().getResource("/resources/sprites/cole/running/frame-" + (i + 1) + ".png"));	
				}
			} else {
				for (int i = 0; i < 6; i++) {	//Loads all UltraHD running frames
					frames[i] = getToolkit().getImage(getClass().getResource("/resources/sprites/cole/running/ultraHD/frame-" + (i + 1) + ".png"));
				}
			}			
			
			phoneX = 450;	//Starting position of phone
			
			phoneImage = getToolkit().getImage(getClass().getResource("/resources/sprites/objects/phone.png"));
					
			mapGrass = getToolkit().getImage(getClass().getResource("/resources/maps/mapGrass.png"));
			mapWinter = getToolkit().getImage(getClass().getResource("/resources/maps/mapWinter.png"));
			mapBridge = getToolkit().getImage(getClass().getResource("/resources/maps/mapBridge.png"));
			
			//mapClub = getToolkit().getImage(getClass().getResource("/resources/maps/mapClub.png"));
			//mapSanta = getToolkit().getImage(getClass().getResource("/resources/maps/mapSanta.png"));
			mapChicken = getToolkit().getImage(getClass().getResource("/resources/maps/mapChicken.png"));
			
			mapHorse = getToolkit().getImage(getClass().getResource("/resources/maps/mapHorse.png"));
			mapDuck = getToolkit().getImage(getClass().getResource("/resources/maps/mapDuck.png"));
			
			if ((Main.mode == 1) || (Main.mode == 3)) {
				map1 = mapGrass;
				map2 = mapBridge;
			} else if (Main.mode == 2) {
				map1 = mapChicken;
				map2 = mapHorse;
			}
		} catch(Exception e) {	//If an error loading occurs
			System.out.println("Image loading error");
			e.printStackTrace();
		}
	}

	public void changeMap() {	//Gives illusion of continuous scrolling map
		if ((0 - Camera.getOffset() + 840) < 0) {	//If the leftmost map is no longer visible
			map1 = map2;	//Sets the leftmost map to be the same as the one previously to the right
			
			randomInt = random.nextInt(4);	//Gets random number to determine next map
			
			if ((Main.mode == 1) || (Main.mode == 3)) {
				if (randomInt == 0) {
					map2 = mapGrass;
				} else if (randomInt == 1) {
					map2 = mapWinter;
				} else if (randomInt == 2) {
					map2 = mapBridge;
				}
			} else {	//UltraHD mode
				if (randomInt == 0) {
					map2 = mapChicken;
				} else if (randomInt == 2) {
					map2 = mapHorse;
				} else if (randomInt == 3) {
					map2 = mapDuck;
				}
			}			
			
			player.setX(player.getX() - Camera.getOffset());	//Sets the normal X to (x - offset) to give appearance of infinite scrolling.
			Camera.setOffset(0);	//Resets camera.
		} /*else if () {
			
		}*/
	}
		
	public void changeCurrentFrame() {	//Changes the running frame, activated by pressing run button.
		if (currentFrame == 5) {	//If there are no more frames.
			currentFrame = 0;
		} else {
			currentFrame++;
		}
	}

	public void checkCollision() { //Checks to make sure Cole is touching the phone, makes sure Cole coords within phone coords.
		/*if ((player.getX() >= phoneX) && ((player.getX() + 80) <= (phoneX + 32)) && (player.getY() >= phoneY) && ((player.getY() + 124) <= (phoneY + 63))) {	//
			System.out.println("Touching");
		}*/
		
		if ((((player.getX()) - Camera.getOffset()) >= phoneX) && (((player.getX()) - Camera.getOffset()) <= phoneX + 32)) {	//If the player x location is between the two sides of the phone.
			//System.out.println("Touching");
			changePhone();
		}
	}
	
	public void changePhone() {	//Called whenever Cole gets phone
		player.setPhones(player.getPhones() + 1);	//Adds another to phone amount
		Game.setLabel(Game.phones, Integer.toString(player.getPhones()));
		
		randomInt = random.nextInt(730) + 70;	//Random number for x value of phone with minimum value of 70, and maximum of (730 + 70)
		phoneX = randomInt;	//Phone will appear at random X location.
		
		Game.map.repaint();	//Repaints to show changes.
	}
	
	public void drawNormal(Graphics g) {
		try {
			Graphics2D g2D;
			g2D = (Graphics2D) g;
		
			g2D.drawImage(map1, 0 - Camera.getOffset(), 0, this);	//Camera offset gives appearance that the game is moving right by making things move left.
			g2D.drawImage(map2, 840 - Camera.getOffset(), 0, this);
			g2D.drawImage(phoneImage, phoneX, 350, this);	//Phone stays at one location to give appearance of a floating phone
			g2D.drawImage(frames[currentFrame], player.getX() - Camera.getOffset(), player.getY(), this);	//Draws Cole
			
			changeMap();
			checkCollision();
		} catch(Exception e) {	//If an error happens
			System.out.println("drawNormal: Image error");
		}
	}
	
	public void drawMenu(Graphics g) {	//Same as normal except it doesn't draw the player, and draws title and instruction
		try {
			Graphics2D g2D;
			g2D = (Graphics2D) g;
		
			g2D.drawImage(map1, 0 - Camera.getOffset(), 0, this);
			g2D.drawImage(map2, 840 - Camera.getOffset(), 0, this);
			g2D.drawImage(phoneImage, 380, 350, this);
			
			g2D.setFont(titleFont);
			g2D.drawString("Crazy Cole's Adventure's In Brazil", 125, 150);
			
			g2D.setFont(descriptionFont);
			g2D.drawString("Help Cole make new friends while on exchange by getting phone numbers and pressing F and J rapidly", 50, 250);
			
			changeMap();
		} catch(Exception e) {
			System.out.println("drawMenu: Image error");
		}
	}
	
	public void paintComponent(Graphics g) {	//Paints in component
		super.paintComponent(g);
		
		if ((Main.mode == 1) || (Main.mode == 2)) {	//If in an actual game(not menu)
			drawNormal(g);
		} else if (Main.mode == 3) {	//Menu drawing mode
			drawMenu(g);
		}
		
		/*try {	//In case of an error with image.
			if (Main.mode == 1) {	//If mode is set to normal
				drawNormal(g);
			} else if (Main.mode == 2) {
				
			} else {
				drawMenu(g);
			}
			
			

		} catch(Exception e) {	//If an error painting occurs.
			System.out.println("Image error.");
		} */
	}
}
