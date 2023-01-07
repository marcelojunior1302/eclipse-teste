package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
	
	Clip clip;
	URL urlSom[] = new URL[30];
	
	public Som() {
		
		urlSom[0] = getClass().getResource("/som/BlueBoyAdventure.wav");
		urlSom[1] = getClass().getResource("/som/coin.wav");
		urlSom[2] = getClass().getResource("/som/powerup.wav");
		urlSom[3] = getClass().getResource("/som/unlock.wav");
		urlSom[4] = getClass().getResource("/som/fanfare.wav");
	}
	
	public void definirArquivo(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(urlSom[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch (Exception e) {
		}
	}
	
	public void reproduzir() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void parar() {
		
		clip.stop();
	}
}
