package team.ekdi1.musik.musikplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LadeDatei {

	public ArrayList<String> csvRead(String csvFilePath) {
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
				for (int i = 0; i < s.length; i++) {
					MusikArray.add(s[i]);
				}
				takt++;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(MusikArray);
//		System.out.println(takt);
//		int a=1;
//		for (String i:MusikArray) {	
//			System.out.print(i+",");
//			if (a%4==0) {
//				System.out.println();
//			}
//			a++;
//		}
		
		return MusikArray;
	}
	
	public void csvWrite(ArrayList<String> MusikArray,String csvFilePath) {
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
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		LadeDatei test1=new LadeDatei();
//		test1.csvRead("C:\\Users\\Ïº½È\\Desktop\\example.csv");
//		LadeDatei test2=new LadeDatei();
//		ArrayList<String> s=new ArrayList<String>();
//		s.add("E2");
//		s.add("B2");
//		s.add("C2");
//		s.add("E8");
//		s.add("H2");
//		test2.csvWrite(s, "C:\\Users\\Ïº½È\\Desktop\\e.csv");
//		test1.csvRead("C:\\Users\\Ïº½È\\Desktop\\e.csv");
//		}
	
}
