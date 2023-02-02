package bala.daniel.CCAIB;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	private File file;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	DataLine.Info dataLineInfo;
	Clip track;
	
	public Sound(String soundFile) {
		file = new File(soundFile);
		init();
	}
	
	public void init() {
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			audioFormat = audioStream.getFormat();
			dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
			track = (Clip) AudioSystem.getLine(dataLineInfo);
			
		} catch (Exception e) {	//If an error occurs
			System.out.println("Error loading sound");
		}
	}
	
	public void start() {	//Starts specified audio clip
		try {
			track.open(audioStream);
			track.loop(track.LOOP_CONTINUOUSLY);
			track.start();
		} catch (Exception e) {
			System.out.println("Error playing sound");
		}
	}
	
	public void stop() {	//Stops specified audio clip
		track.stop();
	}
}
