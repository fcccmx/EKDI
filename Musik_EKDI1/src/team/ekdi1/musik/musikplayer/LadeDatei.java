package team.ekdi1.musik.musikplayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LadeDatei {

	public static String[][] csvRead(String csvFilePath) {
		String line="";
		String SpiltBy=",";
		String[][] KanalArray=new String[100][4];
		//List<String[]> KanalArray=new ArrayList<String[]>();
		// Jede Spalte soll einer von 4 kanal entsprechen, 
		//und jede Zeile den Anteil an einem Takt
		int takt=0;
		try {
			BufferedReader csvFile=new BufferedReader(new FileReader(csvFilePath));
			while((line=csvFile.readLine())!=null) {
				KanalArray[takt]=line.split(SpiltBy);
				takt++;
				}
		}catch (IOException e) {
			e.printStackTrace();
		}
				
	
//		for (int i= 0; i<takt; i++) {
//			for (int j = 0; j < KanalArray[takt].length; j++) {
//				System.out.print(KanalArray[i][j]+",");	
//			}
//			//System.out.print(KanalArray[takt].length);
//			System.out.println();
//		}
		return KanalArray;
	}
	
	public void csvWrite(String[][] KanalArray) {
		try {
			//BufferedReader csvFile=new BufferedReader(new FileReader(csvFilePath));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		csvRead("C:\\Users\\Ïº½È\\Desktop\\example.csv");
	}
}
