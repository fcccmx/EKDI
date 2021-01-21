package team.ekdi1.musik.musikplayer;

import java.util.ArrayList;

public class Musikspieler{
	public StatusAbfrage statusAbfrage = new StatusAbfrage();
	private String[][] liedArray;

	public Musikspieler() {
		this.statusAbfrage.start();
	}
	
	public Musikspieler(String status,ArrayList<String> musikArray) {
		this.statusAbfrage.status=status;
		liedArray=new String[musikArray.size()/4][4];
		for (int i = 0; i < musikArray.size(); i++) {
			liedArray[i/4][i%4]=musikArray.get(i);
		}
		System.out.println(this.statusAbfrage.status);
		this.statusAbfrage.start();
	}
	
	private int getLiedArrayLength() {
		for (int i = 0; i < 64; i++) {
			if (this.liedArray[0][i] == null) {
				return i + 1;
			}
		}
		return 64;

	}

	public void MusikspielerSchleife() {
		Boolean play = true;
		int aktTakt = 0;
		String state = "PLAY";
		LadeDatei fL = new LadeDatei();	
		
		
//		this.liedArray = fL.csvRead2DArray("song2.txt");

		
		while(aktTakt < liedArray.length && play == true) {
			while (this.statusAbfrage.status.equals("P")) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Muzak kanal1Spieler = new Muzak();
			String audioFilePath1 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][0] + ".wav";
			String audioFilePath2 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][1] + ".wav";
			String audioFilePath3 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][2] + ".wav";
			String audioFilePath4 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][3] + ".wav";
			
			kanal1Spieler.play(audioFilePath1, audioFilePath2,audioFilePath3,audioFilePath4);	
			aktTakt++;
			
			if (this.statusAbfrage.status.equals("P")) {
				play = true;
			}
			
			if (this.statusAbfrage.status.equals("S")) {
				play = false;
			}
			
			if (this.statusAbfrage.status.equals("V")) {
				aktTakt = 0;
				this.statusAbfrage.status = "F";
			}
			
			
		}
		System.out.println("beendet");

	}



	public static void main(String[] args) {
		ArrayList<String> ar=new ArrayList<String>();
		ar=LadeDatei.csvRead("C:\\Users\\Ïº½È\\Desktop\\song.csv");
		Musikspieler mS = new Musikspieler("F",ar);
		mS.MusikspielerSchleife();
		

	}
}



