package team.ekdi1.musik.musikplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LadeDatei {

	private static BufferedReader csvFile;


	public static ArrayList<String> csvRead(String csvFilePath) {
		String line="";
		String SpiltBy=",";
		//String[][] KanalArray=new String[100][4];
		// Jede Spalte soll einer von 4 kanal entsprechen, 
		//und jede Zeile den Anteil an einem Takt
		ArrayList<String> MusikArray=new ArrayList<String>();
		String[] s;//Store the contents of each line in a csvfile.
		int takt=0;
		try {
			File inFile=new File(csvFilePath);
			BufferedReader csvFile=new BufferedReader(new FileReader(inFile));
			while((line=csvFile.readLine())!=null) {
				//KanalArray[takt]=line.split(SpiltBy);
				s=line.split(SpiltBy);
				for (int i = 0; i < 4 ; i++) {
					if (i-s.length<0) {//when s array length is less than 4, add "" to Arraylist
						MusikArray.add(s[i]);
					}
					else {
						MusikArray.add("");
					}
				}
				takt++;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}		
		return MusikArray;
	}
	
	public static void csvWrite(ArrayList<String> MusikArray,String csvFilePath) {
		
		try {
			File outFile=new File(csvFilePath);
			if (!outFile.exists()) {
				    outFile.createNewFile();
			}
			BufferedWriter csvFile=new BufferedWriter(new FileWriter(outFile));
			int a=1;
			for(String i:MusikArray) {
				csvFile.write(i+",");
				if (a%4==0) {
					csvFile.newLine();	
				}
				a++;
			}			
			csvFile.flush();
			csvFile.close();
		}catch (IOException e) {
			e.printStackTrace();}
//		}finally {
//			if (csvFile!=null) {
//				try {
//					csvFile.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//				}
//				
//			}
//		}
	}
	
	
	public static String[][] csvRead2DArray(String fileName) {

		String[][] komplettArray = new String[64][4];

		URL path = Musikspieler.class.getResource(fileName);
		File f = new File(path.getFile());
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			int i = 0;
			while(line != null) {
				line.replaceAll("\\s+","");
				for (int j = 0; j < 4; j++) {
					komplettArray[i][j] = line.split(",")[j];
				}
				i++;
				line = br.readLine();

			}
			br.close();

			return komplettArray;


		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}

	}
	
}
