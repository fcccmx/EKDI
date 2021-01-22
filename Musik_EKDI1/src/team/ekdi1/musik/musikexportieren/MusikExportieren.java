package team.ekdi1.musik.musikexportieren;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


import team.ekdi1.musik.musikplayer.LadeDatei;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.process.ffmpeg.FFMPEGProcess;

public class MusikExportieren {
	private String wavfilepath;
	String MusikFolder= System.getProperty("user.dir")+"\\tonauswahl\\";
	String TaktFolder=MusikFolder+"Takt\\";
	String silenceWavPath1=MusikFolder+"silence_1s.wav";
	String silenceWavPath2=MusikFolder+"silence_0.5s.wav";
	private static final String FFmpeg_path=
			System.getProperty("user.dir") +"\\ffmpeg-N-100653-geb0f532c98-win64-gpl-shared-vulkan\\bin\\ffmpeg.exe";
	
	public MusikExportieren(ArrayList<String> ar,String outFile) throws IOException, UnsupportedAudioFileException{
		this.wavfilepath=outFile.substring(0, outFile.lastIndexOf("."))+".wav";
	}
	
	/**
	 * Mix the sounds of each Takt
	 * Create a new folder Takt and save the mixed music to the folder
	 * 
	 */
	public String mixWav(ArrayList<String> pathArray,int Takt)  {
					if (pathArray.size()==0) {
						return silenceWavPath2;
					}else if (pathArray.size()==1) {
						return pathArray.get(0);
					}else{
						String wavPath=TaktFolder+(Takt+1)+".wav";
						System.out.println(wavPath);
						try {
							String command1=FFmpeg_path;
							File file=new File(TaktFolder);
							if (!file.exists()) {
								file.mkdirs();
							}
							for (int i = 0; i < pathArray.size(); i++) {		
								command1+=" -i "+pathArray.get(i);	
							}
							command1+=" -filter_complex amix=inputs="+pathArray.size()
									+":duration=longest:dropout_transition=3 -y "
									+wavPath;
							System.out.println(command1);	
							Process process = Runtime.getRuntime().exec(command1);
							
							InputStream errorStream = process.getErrorStream();
							InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
							BufferedReader br = new BufferedReader(inputStreamReader);
							
							String str = "";
							while ((str = br.readLine()) != null) {
								System.out.println(str);	
							}			
						  	process.waitFor();	
							process.destroy();
						} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						}
						return wavPath;
					}
					
			}	
		
	/**
	 * add Takt wav path into a txt file
	 * connect Takt wav
	 * Delete the Takt folder and the waves in it
	 */		
	public void concatWav(ArrayList<String> komplettArray) {
		String command;
		String mixedWavPath=MusikFolder+"mixedWavPath.txt";
		System.out.println(mixedWavPath);
		try {
			File mixedWavPathList=new File(mixedWavPath);
			if (!mixedWavPathList.exists()) {
			    mixedWavPathList.createNewFile();
			}
			BufferedWriter PathFile=new BufferedWriter(new FileWriter(mixedWavPathList));
			for (int i = 0; i < komplettArray.size()/4; i++) {
				ArrayList<String> pathArray=new ArrayList<String>();
				for (int j = 0; j < 4; j++) {
					if (komplettArray.get(i*4+j)!="") {
						pathArray.add(MusikFolder+komplettArray.get(i*4+j)+".wav");	
					}
				}
				PathFile.write("file "+mixWav(pathArray,i).replace("\\", "\\\\"));
				PathFile.newLine();
				PathFile.write("file "+silenceWavPath1.replace("\\", "\\\\"));
				PathFile.newLine();
			}
			PathFile.flush();
			PathFile.close();
		}catch (Exception e) {
				e.printStackTrace();
		}
		try {
			command=FFmpeg_path+" -f concat -safe 0 -i "+mixedWavPath+" -c copy "+wavfilepath;
				System.out.println(command);	
				Process process = Runtime.getRuntime().exec(command);
				InputStream errorStream = process.getErrorStream();
				InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				
				String str = "";
				while ((str = br.readLine()) != null) {
					System.out.println(str);	
				}			
			  	process.waitFor();	
				process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		deleteDirectory(TaktFolder);	
	}	
	
	public boolean WavToMp3(String outFile) {
		boolean status =false;
		
		try {
			excute(outFile);
			status=true;
		}catch (Exception e) {
			// TODO: handle exception
			status=false;
			e.printStackTrace();
		}
		return status;
	}
	

	private  File excute( String outFile) 
			throws IllegalArgumentException, InputFormatException, EncoderException {
		File source=new File(wavfilepath);
		File target=new File(outFile);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(Integer.valueOf(128000));
		audio.setChannels(Integer.valueOf(2));
		audio.setSamplingRate(Integer.valueOf(44100));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setOutputFormat("mp3");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(new MultimediaObject(source), target, attrs);
		return target;
		
	}
	
	
	public boolean deleteDirectory(String sPath) {
	    if (!sPath.endsWith(File.separator)) {
	        sPath = sPath + File.separator;
	    }
	    File dirFile = new File(sPath);
	    if (!dirFile.exists() || !dirFile.isDirectory()) {
	        return false;
	    }
	    boolean flag = true;
	    File[] files = dirFile.listFiles();
	    for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile()) {
	            flag = deleteFile(files[i].getAbsolutePath());
	            if (!flag) break;
	        } 
	        else {
	            flag = deleteDirectory(files[i].getAbsolutePath());
	            if (!flag) break;
	        }
	    }
	    if (!flag) return false;
	    if (dirFile.delete()) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public boolean deleteFile(String sPath) {
	    boolean flag = false;
	    File file = new File(sPath);
	    if (file.isFile() && file.exists()) {
	        file.delete();
	        flag = true;
	    }
	    return flag;
	}
	
	public static void main(String[] args){
		try {
			String output="C:\\Users\\Ïº½È\\Desktop\\nawsl.mp3";
		ArrayList<String> ar=new ArrayList<String>();
		ar=LadeDatei.csvRead("C:\\Users\\Ïº½È\\Desktop\\song.csv");
		MusikExportieren test=new MusikExportieren(ar,output);
		test.concatWav(ar);
		test.WavToMp3(output);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
