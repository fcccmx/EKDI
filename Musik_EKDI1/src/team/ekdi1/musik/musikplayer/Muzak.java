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
	public int vier = 4;
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
    public void play(String audioFilePath1, String audioFilePath2,String audioFilePath3, String audioFilePath4) {
	File audioFile1 = new File(audioFilePath1);
	File audioFile2 = new File(audioFilePath2);
	File audioFile3 = new File(audioFilePath3);
	File audioFile4 = new File(audioFilePath4);
	
	try {
	    AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(audioFile1);
	    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);	 
	    AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(audioFile3);
	    AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(audioFile4); 
	    
	    AudioFormat format1 = audioStream1.getFormat();
	    AudioFormat format2 = audioStream2.getFormat();
	    AudioFormat format3 = audioStream3.getFormat();
	    AudioFormat format4 = audioStream4.getFormat();
	    
	    DataLine.Info info1 = new DataLine.Info(Clip.class, format1);
	    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
	    DataLine.Info info3 = new DataLine.Info(Clip.class, format3);
	    DataLine.Info info4 = new DataLine.Info(Clip.class, format4);
	    
	    Clip audioClip1 = (Clip) AudioSystem.getLine(info1);
	    Clip audioClip2 = (Clip) AudioSystem.getLine(info2);
	    Clip audioClip3 = (Clip) AudioSystem.getLine(info3);
	    Clip audioClip4 = (Clip) AudioSystem.getLine(info4);
	    
	    //audioClip.addLineListener(this);
	    
	    audioClip1.open(audioStream1);
	    audioClip2.open(audioStream2);
	    audioClip3.open(audioStream3);
	    audioClip4.open(audioStream4);
	    
	    audioClip1.start();
	    audioClip2.start();
	    audioClip3.start();
	    audioClip4.start();
	   	    
	    while (!playCompleted) {
		// wait for the playback completes
		try {
		    Thread.sleep(1500);
		    playCompleted = true;
		    //audioClip2.start();
		} catch (InterruptedException ex) {
		    ex.printStackTrace();
		}
		}
	    
	    audioClip1.close();
	    audioClip2.close();
	    audioClip3.close();
	    audioClip4.close();


	    
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
//	String audioFilePath = "C:\\Users\\jsste\\Downloads\\anhang(1)\\anhang\\ton.wav";
//	String audioFilePath2 = "C:\\Users\\jsste\\Downloads\\anhang(1)\\anhang\\tonauswahl\\Bamboo.wav";
//	//Muzak player = new Muzak();
//	Muzak player2 = new Muzak();
//	for(int i = 0; i < 10000; i++) {
//		Muzak player = new Muzak();
//		player.play(audioFilePath);
//		
//		
//	}
//	
//	
//	player2.play(audioFilePath2);
    }   
}
