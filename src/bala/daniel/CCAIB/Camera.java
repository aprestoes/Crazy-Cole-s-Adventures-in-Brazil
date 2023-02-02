package bala.daniel.CCAIB;

import java.util.TimerTask;
import java.util.Timer;


public class Camera {
	private static int offset = 0;	//Formula for x positioning is x - camera offset, which gives appearance camera is moving right.
	
	private static Player player;
	
	private static Timer timer;
	
	public Camera(Player gamePlayer) {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {	//Creates new timer that automatically moves the camera.
			@Override
			public void run() {
				offset = offset + 1 + ((int) player.getPhones() / 5);
				if (Main.mode == 3) {
					Menu.menuMap.repaint();
				} else {
					Game.map.repaint();
				}
				//System.out.println(offset);
			}
		}, 0, 20);
		player = gamePlayer;
	}
	
	public static void setOffset(int offsetValue) {
		offset = offsetValue;
	}

	public static int getOffset() {	//How much to the left an image should be.
		if ((player.getX() - offset < -100) && (Main.mode != 3)) {	//Menu does not kill player
			player.death();		
		}
		return offset;
	}
	
	public static void reset() {	//Cancels stuff
		timer.cancel();
		timer.purge();
		offset = 0;	
	}
}
