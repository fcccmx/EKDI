package team.ekdi1.musik.musikeditor;
import team.ekdi1.musik.musikplayer.LadeDatei;
public class Editor {
//		public static void main(String[] args) {
//			
//	
//		}
		public static void Add(int takt,int Kanal,String Tone,String csvPath) {
			LadeDatei editor= new LadeDatei();
			String KanalArray[][]=editor.csvRead(csvPath);
			
			//editor.csvWrite(KanalArray);
			
		}
		
		public void Replace(int takt,int Kanal,String Tone,String csvPath) {
			LadeDatei editor= new LadeDatei();
			String KanalArray[][]=editor.csvRead(csvPath);
			KanalArray[takt][Kanal]=Tone;
			//editor.csvWrite(KanalArray);
			
		}
		
		public void Delete(int takt,int Kanal,String csvPath) {
			LadeDatei editor= new LadeDatei();
			String KanalArray[][]=editor.csvRead(csvPath);
			KanalArray[takt][Kanal]=" ";
		}
}
