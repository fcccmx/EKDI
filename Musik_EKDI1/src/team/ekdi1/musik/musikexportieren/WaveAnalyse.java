package team.ekdi1.musik.musikexportieren;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileInputStream;

public class WaveAnalyse {
	 
		int ChunkID; // RIFF
		int ChunkSize; // size=filelen-8B
		int ChunkFormat; // WAVE
	 
		// subchunk-'fmt'
		int SubChunk1; // 'fmt'
		int SubChunk1Size;
		// PCMWAVEFORMAT
		short AudioFormat;
		short NumChannels;
		int SampleRate;
		int ByteRate;
		short BlockAlign;
		short BitsperSample;
	 
		// subchunk-date
		int SubChunk2ID;
		int SubChunk2Size;
	 
	 
		public void Analyse(String AudioPath){
			try {
				DataInputStream DemoWav = new DataInputStream(
						new BufferedInputStream(
								new FileInputStream(
										new File(AudioPath))));
				try {
					System.out.println(DemoWav.available());
					ChunkID = DemoWav.readInt();
					ChunkSize = DemoWav.readInt();
					ChunkFormat = DemoWav.readInt();
					SubChunk1 = DemoWav.readInt();
					SubChunk1Size = DemoWav.readInt();
					AudioFormat =  DemoWav.readShort();
					NumChannels =  DemoWav.readShort();
					SampleRate = DemoWav.readInt();
					ByteRate = DemoWav.readInt();
					BlockAlign =  DemoWav.readShort();
					BitsperSample =  DemoWav.readShort();
					SubChunk2ID = DemoWav.readInt();
					SubChunk2Size = DemoWav.readInt();
									
					System.out.println(DemoWav.available());
					DemoWav.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Analyse Error!");
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Can Not Find file!");
			}
		}
		public void ShowInfo1(){
			System.out.println("-----------------WAVE HEAD-------------------");
			System.out.println("ChunkID  "+Integer.toHexString(ChunkID));
			System.out.println("ChunkSize  "+Integer.toHexString(ChunkSize));
			System.out.println("ChunkFormat  "+Integer.toHexString(ChunkFormat));
			System.out.println("-----------------SubChunk1-------------------");
			System.out.println("SubChunk1  "+Integer.toHexString(SubChunk1));
			System.out.println("SubChunk1Size  "+Integer.toHexString(SubChunk1Size));
			System.out.println("AudioFormat  "+Integer.toHexString(AudioFormat));
			System.out.println("NumChannels  "+Integer.toHexString(NumChannels));
			System.out.println("SampleRate  "+Integer.toHexString(SampleRate));
			System.out.println("ByteRate  "+Integer.toHexString(ByteRate));
			System.out.println("BlockAlign  "+Integer.toHexString(BlockAlign));
			System.out.println("BitsperSample  "+Integer.toHexString(BitsperSample));
			System.out.println("-----------------SubChunk2-------------------");
			System.out.println("SubChunk2ID  "+Integer.toHexString(SubChunk2ID));
			System.out.println("SubChunk2Size  "+Integer.toHexString(SubChunk2Size));
		}
			
		
		public static void main(String[] args){
			WaveAnalyse test1=new WaveAnalyse();
			test1.Analyse("C:\\Users\\Ïº½È\\git\\EKDI\\tonauswahl\\1980s-Casio-Piano-C5.wav");
			test1.ShowInfo1();
			File test2=new File("C:\\\\Users\\\\Ïº½È\\\\git\\\\EKDI\\\\tonauswahl\\\\1980s-Casio-Piano-C5.wav");
			Encoder encoder = new Encoder();
			try {
				MultimediaInfo t=encoder.getInfo(test2);
				System.out.println("----------------------------------------------");
				System.out.println(t);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			System.out.println("----------------------------------------------");
			WaveAnalyse test3=new WaveAnalyse();
			test3.Analyse("C:\\Users\\Ïº½È\\Desktop\\new.wav");
			test3.ShowInfo1();
		
		}
	}


