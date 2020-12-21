package team.ekdi1.musik.musikexportieren;

import java.io.File;
import java.util.ArrayList;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import team.ekdi1.musik.musikplayer.LadeDatei;

public class MusikExportieren {
	
//	public void wavKomposition(String csvPath) {
//		ArrayList<String> MusikArray;
//		LadeDatei Musik=new LadeDatei();
//		MusikArray=Musik.csvRead(csvPath);
//		
//	}
	
	public static boolean WavToMp3(String inPath,String outFile) {
		boolean status =false;
		File file=new File(inPath);
		try {
			excute(file,outFile);
			status=true;
		}catch (Exception e) {
			// TODO: handle exception
			status=false;
			e.printStackTrace();
		}
		return status;
	}

	private static File excute(File source, String outFile) 
			throws IllegalArgumentException, InputFormatException, EncoderException {
		File target=new File(outFile);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(Integer.valueOf(128000));
		audio.setChannels(Integer.valueOf(2));
		audio.setSamplingRate(Integer.valueOf(44100));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(source, target, attrs);
		return target;
		
	}
	
//	public static void main(String[] args){
//		WavToMp3
//		("C:\\\\Users\\\\Ïº½È\\\\git\\\\EKDI\\\\tonauswahl\\\\1980s-Casio-Piano-C5.wav", 
//				"C:\\Users\\Ïº½È\\Desktop\\1980s-Casio-Piano-C5.mp3");
//		
//	}
}
