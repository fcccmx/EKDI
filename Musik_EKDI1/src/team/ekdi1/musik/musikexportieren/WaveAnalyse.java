package team.ekdi1.musik.musikexportieren;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileInputStream;

public class WaveAnalyse {
	 
//		int ChunkID; // RIFF
        String AudioFormat;
		int NumChannels;
		int SampleRate;
		int ByteRate;
//		short BlockAlign;
//		short BitsperSample;
//	 
//		// subchunk-date
//		int SubChunk2ID;
//		int SubChunk2Size;
	 
		String decoder;
		
		public WaveAnalyse(String AudioPath){
			File audio=new File(AudioPath);
			Encoder encoder = new Encoder();
			try {
				MultimediaInfo t=encoder.getInfo(audio);
				AudioFormat=t.getFormat();
				NumChannels=t.getAudio().getChannels();
				ByteRate=t.getAudio().getBitRate();
				SampleRate=t.getAudio().getSamplingRate();
				decoder=t.getAudio().getDecoder();
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
//			try {
//				DataInputStream DemoWav = new DataInputStream(
//						new BufferedInputStream(
//								new FileInputStream(
//										new File(AudioPath))));
//				try {
//					System.out.println(DemoWav.available());
//					ChunkID = DemoWav.readInt();
//					ChunkSize = DemoWav.readInt();
//					ChunkFormat = DemoWav.readInt();
//					SubChunk1 = DemoWav.readInt();
//					SubChunk1Size = DemoWav.readInt();
//					AudioFormat =  DemoWav.readShort();
//					NumChannels =  DemoWav.readShort();
//					SampleRate = DemoWav.readInt();
//					ByteRate = DemoWav.readInt();
//					BlockAlign =  DemoWav.readShort();
//					BitsperSample =  DemoWav.readShort();
//					SubChunk2ID = DemoWav.readInt();
//					SubChunk2Size = DemoWav.readInt();
//									
//					System.out.println(DemoWav.available());
//					DemoWav.close();
//					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					System.out.println("Analyse Error!");
//				}
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Can Not Find file!");
//			}
		}
		
		private static File excutechannel(File source, String outFile,int channel) 
				throws IllegalArgumentException, InputFormatException, EncoderException {
			File target=new File(outFile);
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("pcm_s16le");
			audio.setBitRate(Integer.valueOf(1411000));
			audio.setChannels(Integer.valueOf(channel));
			audio.setSamplingRate(Integer.valueOf(44100));
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("wav");
			attrs.setAudioAttributes(audio);
			Encoder encoder = new Encoder();
			encoder.encode(source, target, attrs);
			return target;
			
		}
		
		public static boolean changechannel(String Path,int channel) {
			boolean status =false;
			File file=new File(Path);
			try {
				excutechannel(file,Path,channel);
				status=true;
			}catch (Exception e) {
				// TODO: handle exception
				status=false;
				e.printStackTrace();
			}
			return status;
		}

//		public void ShowInfo1(){
//			System.out.println("-----------------WAVE HEAD-------------------");
//			System.out.println("ChunkID  "+Integer.toHexString(ChunkID));
//			System.out.println("ChunkSize  "+Integer.toHexString(ChunkSize)+"  "+ChunkSize);
//			System.out.println("ChunkFormat  "+Integer.toHexString(ChunkFormat));
//			System.out.println("-----------------SubChunk1-------------------");
//			System.out.println("SubChunk1  "+Integer.toHexString(SubChunk1));
//			System.out.println("SubChunk1Size  "+Integer.toHexString(SubChunk1Size));
//			System.out.println("AudioFormat  "+Integer.toHexString(AudioFormat));
//			System.out.println("NumChannels  "+Integer.toHexString(NumChannels));
//			System.out.println("SampleRate  "+Integer.toHexString(SampleRate));
//			System.out.println("ByteRate  "+Integer.toHexString(ByteRate));
//			System.out.println("BlockAlign  "+Integer.toHexString(BlockAlign));
//			System.out.println("BitsperSample  "+Integer.toHexString(BitsperSample));
//			System.out.println("-----------------SubChunk2-------------------");
//			System.out.println("SubChunk2ID  "+Integer.toHexString(SubChunk2ID));
//			System.out.println("SubChunk2Size  "+Integer.toHexString(SubChunk2Size));
//		}
			
		
		public static void main(String[] args){
			WaveAnalyse.changechannel("C:\\Users\\Ïº½È\\Desktop\\TC5.wav", 2);
			

		}
	}


