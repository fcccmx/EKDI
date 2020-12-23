package team.ekdi1.musik.musikexportieren;

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

import team.ekdi1.musik.musikplayer.LadeDatei;


public class WavKomposition {
	
	int headLength1=0;
	int headLength2=0;
	
	

	public int byteArrayToInt(byte[] b) {
		return b[3]&0xFF|(b[2]&0xFF)<<8|(b[1]&0xFF)<<16|(b[0]&0xFF)<<24;
	}
	
	public byte[] intToByteArray(int a) {
		return new byte[] {
				(byte)((a>>24)&0xFF),(byte)((a>>16)&0xFF),(byte)((a>>8)&0xFF),(byte)(a&0xFF)};
	}
	
	public byte[] byteToByte(byte[] b) {
		if(b.length==4)
			return new byte[] {b[3],b[2],b[1],b[0]};
		return null;
	}
	
	public void updateFileHead(String output,boolean ifUpdate) throws IOException {
		RandomAccessFile raf=new RandomAccessFile(output, "rw");
		//Open a file channel
		FileChannel channel=raf.getChannel();
		//Mapping a part of the data in a file to memory in read and write mode
		MappedByteBuffer buffer= channel.map(FileChannel.MapMode.READ_WRITE, 0, 44);
		//Modify the header file
		if (ifUpdate) {
			byte[] head1=byteToByte(intToByteArray(headLength1));
			byte[] head2=byteToByte(intToByteArray(headLength2));
			
			buffer.put(4,head1[0]);
			buffer.put(5,head1[1]);
			buffer.put(6, head1[2]);
	        buffer.put(7, head1[3]);
	        buffer.put(40, head2[0]);
	        buffer.put(41, head2[1]);
	        buffer.put(42, head2[2]);
	        buffer.put(43, head2[3]);
	        buffer.force();//Force output, changes in the buffer to take effect in the file
	        
	        
		}else {
			headLength1=headLength1+
					byteArrayToInt(byteToByte(
							new byte[] {buffer.get(4),buffer.get(5),buffer.get(6),buffer.get(7)}));
			headLength2=headLength1+
					byteArrayToInt(byteToByte(
							new byte[] {buffer.get(40),buffer.get(41),buffer.get(42),buffer.get(43)}));
		}
		buffer.clear();
		channel.close();
		raf.close();
	}

	public void mergeWav(ArrayList<String> komplettArray,String output) throws IOException {
		String[][] pathArray=new String[komplettArray.size()/4][4];
		for (int i = 0; i < komplettArray.size(); i++) {
			pathArray[i/4][i%4]="C:\\Users\\Ïº½È\\Desktop\\tonauswahl\\"+komplettArray.get(i)+".wav";
		}
		File outFile= new File(output);
		if(!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
		OutputStream os=new FileOutputStream(outFile);
		for (int i = 0; i < pathArray.length; i++) {
			File file1=new File(pathArray[i][0]);
			if(!file1.exists())
				continue;
			InputStream is=new FileInputStream(file1);
			if(i!=0) {
				is.skip(44);//skip head of wav
			}
			byte[] tempBuffer=new byte[1024];
			int nRed=0;
			//Copy the entire contents to output.wav
			while ((nRed=is.read(tempBuffer))!=-1) {
				os.write(tempBuffer,0,nRed);
				os.flush();		
			}
			
			is.close();
		}
		os.close();
		
		
		for (int i=0;i<pathArray.length;i++ ) {
			updateFileHead(pathArray[i][0], false);
			System.out.println("out:"+pathArray[i][0]);
			//updateFileHead(pathArray[i][1], false);
			//updateFileHead(pathArray[i][2], false);
			//updateFileHead(pathArray[i][3], false);
		}
		updateFileHead(output, true);
	}

//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		String output="C:\\Users\\Ïº½È\\Desktop\\new.wav";
//		LadeDatei a=new LadeDatei();
//		ArrayList<String> ar=new ArrayList<String>();
//		ar=a.csvRead("C:\\Users\\Ïº½È\\Desktop\\example.csv");
//		WavKomposition test=new WavKomposition();
//		test.mergeWav(ar, output);
//		System.out.println("ooooooooooops");
//		
//	}

}
