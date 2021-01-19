package team.ekdi1.musik.musikeditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import team.ekdi1.musik.musikplayer.LadeDatei;

public class Editor {
	private ArrayList<String> MusikArray=new ArrayList<String>();
	
	public Editor(ArrayList<String> ar) {
		this.MusikArray=ar;
	}
	public Editor(String csvPath) {
		this.MusikArray=LadeDatei.csvRead(csvPath);
	}
	
//	public void Add(int takt,int Kanal,String Tone) {
//		this.MusikArray.add(takt*Kanal-1,Tone);		
//	}
	
	public void AddLine(int takt) {
		ArrayList<String> ar=new ArrayList<String>();
		Collections.addAll(ar, "","","","");
		System.out.println(ar);
		this.MusikArray.addAll(takt*4, ar);
	}
		
//	public void Replace(int takt,int Kanal,String Tone) {
//		this.MusikArray.set(takt*Kanal-1, Tone);
//			
//	}
		
	public void DeleteLine(int takt) {
		for (int i = 0; i < 4; i++) {
			this.MusikArray.remove(takt*4);
		}
	}
	
//		public static void main(String[] args) {
//			Editor test1=new Editor("C:\\Users\\Ïº½È\\Desktop\\song2.csv");
//			System.out.print(test1.MusikArray);
//			System.out.println();
//			test1.AddLine(2);
//			System.out.print(test1.MusikArray);
//			System.out.println();
//			test1.DeleteLine(2);
//			System.out.print(test1.MusikArray);
//		}
		
}
