package bala.daniel.CCAIB;

public class Main {	//Main class
	public static int mode = 3;
	/*
	 There are three modes:
	 1. Normal
	 2. Ultra-HD
	 3. Menu(Endless scrolling no character)
	 
	 */
	
	
	public static void runGame() {	//Gets called by Menu class to open up a new instance of game.
		Game game = new Game();
		game.init();
	}
	public static void main(String args[]) {
		Menu menu = new Menu();	//Menu at start
		menu.init();
	}
}
