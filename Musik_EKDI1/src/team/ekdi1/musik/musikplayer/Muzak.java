package team.ekdi1.musik.musikplayer;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Muzak /*implements LineListener*/ {

    /**
     * This is an example program that demonstrates how to play back an audio file
     * using the Clip in Java Sound API.
     * @author www.codejava.net
     *
     */
    
	
    /**
     * this flag indicates whether the playback completes or not.
     */
    boolean playCompleted;
    
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public void play(String audioFilePath) {
	File audioFile = new File(audioFilePath);
	
	try {
	    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile);	    
	    AudioFormat format = audioStream.getFormat();
	    AudioFormat format2 = audioStream2.getFormat();
	    
	    DataLine.Info info = new DataLine.Info(Clip.class, format);
	    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
	    
	    Clip audioClip = (Clip) AudioSystem.getLine(info);
	    Clip audioClip2 = (Clip) AudioSystem.getLine(info2);
	    
	    //audioClip.addLineListener(this);
	    
	    audioClip.open(audioStream);
	    audioClip2.open(audioStream2);
	    
	    audioClip.start();

	    
	    while (!playCompleted) {
		// wait for the playback completes
		try {
		    Thread.sleep(50);
		    //audioClip2.start();
		    Thread.sleep(1000);
		    playCompleted = true;
		    //audioClip2.start();
		} catch (InterruptedException ex) {
		    ex.printStackTrace();
		}
		}
	    
	    audioClip.close();
	    audioClip2.close();

	    
	} catch (UnsupportedAudioFileException ex) {
	    System.out.println("The specified audio file is not supported.");
	    ex.printStackTrace();
	} catch (LineUnavailableException ex) {
	    System.out.println("Audio line for playing back is unavailable.");
	    ex.printStackTrace();
	} catch (IOException ex) {
	    System.out.println("Error playing the audio file.");
	    ex.printStackTrace();
	}
	
    }
    
    /**
     * Listens to the START and STOP events of the audio line.
     
    //@Override
    public void update(LineEvent event) {
	LineEvent.Type type = event.getType();
	
	if (type == LineEvent.Type.START) {
	    System.out.println("Playback started.");
	    
	} else if (type == LineEvent.Type.STOP) {
	    playCompleted = true;
	    System.out.println("Playback completed.");
	}
	
	} 
    */
    public static void main(String[] args) {
	String audioFilePath = "C:\\Users\\jsste\\Downloads\\anhang(1)\\anhang\\ton.wav";
	String audioFilePath2 = "C:\\Users\\jsste\\Downloads\\anhang(1)\\anhang\\tonauswahl\\Bamboo.wav";
	//Muzak player = new Muzak();
	Muzak player2 = new Muzak();
	for(int i = 0; i < 10000; i++) {
		Muzak player = new Muzak();
		player.play(audioFilePath);
		
		
	}
	
	
	player2.play(audioFilePath2);
    }   
}
