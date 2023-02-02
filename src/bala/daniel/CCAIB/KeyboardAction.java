package bala.daniel.CCAIB;

import javax.swing.*;
import java.awt.event.ActionEvent;

//Class has all actions for keyboards.
@SuppressWarnings("serial")
public class KeyboardAction extends AbstractAction {
	
	private String command;
	
	public KeyboardAction(String command) {
		this.command = command;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (command.equals("F")) {	//If F key is pressed.
			Game.player.moveForward();	//Player moves forward
			System.out.println("F has been pressed.");
			System.out.println(Game.player.getX());
		} else if (command.equals("J")) {
			Game.player.moveForward();
			System.out.println("J has been pressed.");
			System.out.println(Game.player.getX());
		} else if (command.equals("SPACE")) {	//Jumping
			System.out.println("Jumping");
		}
	}

}
