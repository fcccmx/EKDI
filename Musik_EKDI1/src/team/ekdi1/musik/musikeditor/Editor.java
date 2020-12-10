package team.ekdi1.musik.musikeditor;
import java.util.ArrayList;

import team.ekdi1.musik.musikplayer.LadeDatei;
public class Editor {
	private ArrayList<String> MusikArray=new ArrayList<String>();
	
	public Editor(String csvPath) {
		LadeDatei editor= new LadeDatei();
		this.MusikArray=editor.csvRead(csvPath);
	}
	
	public void Add(int takt,int Kanal,String Tone) {
		this.MusikArray.add(takt*Kanal-1,Tone);
		//editor.csvWrite(KanalArray);			
	}
		
	public void Replace(int takt,int Kanal,String Tone) {
		this.MusikArray.set(takt*Kanal-1, Tone);
			
		//editor.csvWrite(KanalArray);
			
	}
		
	public void Delete(int takt,int Kanal) {
		this.MusikArray.set(takt*Kanal-1, "");
			
	}
	
//		public static void main(String[] args) {
//			
//		}
		
}
