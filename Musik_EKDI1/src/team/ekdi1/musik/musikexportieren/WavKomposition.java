package team.ekdi1.musik.musikexportieren;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

import team.ekdi1.musik.musikplayer.LadeDatei;
import ws.schild.jave.process.ffmpeg.FFMPEGProcess;


public class WavKomposition {


//	private int headLength1=0;
//	private int headLength2=0;
//	static String MusikFolder= System.getProperty("user.dir") +"\\tonauswahl";
//	String silenceAudio=System.getProperty("user.dir")+"\\tonauswahl\\silence.wav";
//	
//	//Convert hexadecimal byte arrays to int
//	public static int byteArrayToInt(byte[] b) {
//		return b[3]&0xFF|(b[2]&0xFF)<<8|(b[1]&0xFF)<<16|(b[0]&0xFF)<<24;
//	}
//	
//	//Convert int to hexadecimal byte array
//	public byte[] intToByteArray(int a) {
//		return new byte[] {
//				(byte)((a>>24)&0xFF),(byte)((a>>16)&0xFF),(byte)((a>>8)&0xFF),(byte)(a&0xFF)};
//	}
//	
//	
//	public static byte[] byteToByte(byte[] b) {
//		if(b.length==4) {
//			return new byte[] {b[3],b[2],b[1],b[0]};
//			}
//		return null;
//	}
//	
//	
//	
//	public void updateFileHead(String output,boolean ifUpdate) throws IOException {
//		RandomAccessFile raf=new RandomAccessFile(output, "rw");
//		long filelength=raf.length();
//		System.out.println(output+" filelength:"+filelength);
//		//Open a file channel
//		FileChannel channel=raf.getChannel();
//		//Mapping a part of the data in a file to memory in read and write mode
//		MappedByteBuffer buffer= channel.map(FileChannel.MapMode.READ_WRITE, 0, 44);
//		//System.out.println(Integer.toHexString(buffer.get(40)));
//		for (int i = 0; i < 44; i++) {
//			System.out.print(Integer.toHexString(buffer.get(i))+" ");
//		}
//		System.out.println();
//		byte[] b=new byte[4];
//		b[0]=buffer.get(4);
//		b[1]=buffer.get(5);
//		b[2]=buffer.get(6);
//		b[3]=buffer.get(7);
//		for (int i = 0; i < 4; i++) {
//			System.out.print(b[i]+" ");
//		}
//		
//		int length1=WavKomposition.byteArrayToInt(WavKomposition.byteToByte(b));
//		System.out.println("length1:"+length1);
//		
//		byte[] b1=new byte[4];
//		b1[0]=buffer.get(40);
//		b1[1]=buffer.get(41);
//		b1[2]=buffer.get(42);
//		b1[3]=buffer.get(43);
//		
//		int length2=WavKomposition.byteArrayToInt(WavKomposition.byteToByte(b1));
//		System.out.println("length2:"+length2);
//		
//		
//		
//		//Modify the header file
//		if (ifUpdate) {
//			byte[] head1=WavKomposition.byteToByte(this.intToByteArray(headLength1+44-8));
//			byte[] head2=WavKomposition.byteToByte(this.intToByteArray(headLength2));
//			
//			for (int i = 0; i < head1.length; i++) {
//				System.out.print(head1[i]+" ");
//			}
//			
//			buffer.put(4,head1[0]);
//			buffer.put(5,head1[1]);
//			buffer.put(6, head1[2]);
//	        buffer.put(7, head1[3]);
//	        
//	        buffer.put(40, head2[0]);
//	        buffer.put(41, head2[1]);
//	        buffer.put(42, head2[2]);
//	        buffer.put(43, head2[3]);
//	        
//	        
//	        buffer.force();//Force output, changes in the buffer to take effect in the file
//	        System.out.println("File header modified successfully");
//	        /*******************Query to see if the changes take effect***********/
//	        b=new byte[4];
//			b[0]=buffer.get(4);
//			b[1]=buffer.get(5);
//			b[2]=buffer.get(6);
//			b[3]=buffer.get(7);
//			length1=WavKomposition.byteArrayToInt(WavKomposition.byteToByte(b));
//			System.out.println("*length1:"+length1);
//			b1=new byte[4];
//			b1[0]=buffer.get(40);
//			b1[1]=buffer.get(41);
//			b1[2]=buffer.get(42);
//			b1[3]=buffer.get(43);
//			length2=WavKomposition.byteArrayToInt(WavKomposition.byteToByte(b1));
//			System.out.println("*length2:"+length2);
//			System.out.println();
//		}else {
//			this.headLength1=this.headLength1+length1-44+8;
//			System.out.println("headlength1:"+this.headLength1);
//			this.headLength2=this.headLength2+length2;
//			System.out.println("headlength2:"+this.headLength2);
//		}
//		buffer.clear();
//		channel.close();
//		raf.close();
//	}
//
//	public void mergeWav(ArrayList<String> komplettArray,String output,int channel) throws IOException {
//		InputStream is;
//		String[][] pathArray=new String[komplettArray.size()/4][4];
//		for (int i = 0; i < komplettArray.size(); i++) {
//			pathArray[i/4][i%4]=MusikFolder+"\\"+komplettArray.get(i)+".wav";
//		}
//		
//		File outFile= new File(output);
//		if(!outFile.getParentFile().exists()) {
//			outFile.getParentFile().mkdirs();
//		}
//		if (!outFile.exists()) {
//			outFile.createNewFile();
//		}
//		OutputStream os=new FileOutputStream(outFile);
//		for (int i = 0; i < pathArray.length; i++) {
//			File file1=new File(pathArray[i][channel-1]);
//			if(!file1.exists()) {
//				is=new FileInputStream(silenceAudio);
//			}else {	
//				is=new FileInputStream(file1);
//			}
//			if(i!=0) {
//				is.skip(44);//skip head of wav
//			}
//			byte[] tempBuffer=new byte[1024];
//			int nRed=0;
//			//Copy the entire contents to output.wav
//			while ((nRed=is.read(tempBuffer))!=-1) {
//				os.write(tempBuffer,0,nRed);
//				os.flush();		
//			}
//			is.close();
//		}
//		os.close();		
//		
//			for (int j = 0; j < pathArray.length; j++) {
//				updateFileHead(pathArray[j][channel-1], false);
//				updateFileHead(silenceAudio, false);
//
//			}
//			updateFileHead(output, true);
//			
//		
//	}



//	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String output1="C:\\Users\\Ïº½È\\Desktop\\newsong1.wav";
//		String output2="C:\\Users\\Ïº½È\\Desktop\\newsong2.wav";
//		String output3="C:\\Users\\Ïº½È\\Desktop\\newsong3.wav";
//		String output4="C:\\Users\\Ïº½È\\Desktop\\newsong4.wav";
//		LadeDatei a=new LadeDatei();
//		ArrayList<String> ar=new ArrayList<String>();
//		ar=a.csvRead("C:\\Users\\Ïº½È\\Desktop\\song.csv");
//		WavKomposition test=new WavKomposition();
//		test.mergeWav(ar, output1,1);
//		test.mergeWav(ar, output2,2);
//		test.mergeWav(ar, output3,3);
//		test.mergeWav(ar, output4,4);
//		System.out.println("ooooooooooops");		
//		
//	}

}
