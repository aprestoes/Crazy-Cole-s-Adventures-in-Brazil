package bala.daniel.CCAIB;


public class Player {
	private int lives = 3;
	private int phones = 0;
	
	private int x = 250;
	private int y = 370;	
	
	private String name; //Used to identify which instance of player. Helpful in debugging
	
	public Player(String playerName) {
		name = playerName;
	}
	
	public void setPhones(int phoneValue) {
		phones = phoneValue;
	}
	
	public int getPhones() {
		return phones;
	}
	
	public void setX(int X) {
		x = X;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int Y) {
		y = Y;
	}
	
	public int getY() {
		return y;
	}
	
	public void moveForward() {
		x = x + 9 + ((int) (phones / 2));	//Cole runs faster as he gets more phones.
		Game.map.changeCurrentFrame();
		Game.map.repaint();	//Repaint the map
	}
	
	/*public void jump() {
		isJumping = true;
		do {	//Upwards motion
			this.y = this.y - 3;
			Game.map.repaint();
		} while (this.y < 500);
		do {	//Downwards
			this.y = this.y + 3;
			Game.map.repaint();
		} while (this.y > 370);
		isJumping = false;
	}*/
	
	public void death() {
		System.out.println(name + " Cole has died");
		if (lives > 0) {	//If player still has lives.
			lives--;
			Game.setLabel(Game.lives, Integer.toString(lives));
			//System.out.println(lives + " lives left");
			x = 450;
		} else {	//If player lost
			System.exit(0);
		}
	}
	
}
