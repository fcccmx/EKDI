package team.ekdi1.musik.musikplayer;

import java.util.Scanner;

public class Musikspieler {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] kanal1Array = new String[64];
		String[] kanal2Array = new String[64];
		String[] kanal3Array = new String[64];
		String[] kanal4Array = new String[64];
		
		int aktTakt = 0;
		
		String state = "PLAY";
		
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 64; i++) {
			kanal1Array[i] = "1";
		}
		
		for (int i = 0; i < 64; i++) {
			kanal2Array[i] = "2";
		}
		
		for (int i = 0; i < 64; i++) {
			kanal3Array[i] = "3";
		}
		for (int i = 0; i < 64; i++) {
			kanal4Array[i] = "4";
		}
		kanal1Array[0] = "1";
		kanal1Array[1] = "2";
		
		while(true) {
			if ( state.equals("PAUSE")) {
				if(scanner.next() == "P") {
					state = "PLAY";
				}
				    try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("kann nicht warten");
					}
				    
				
			}
			if (state.equals("PLAY")) {
//				if(scanner.next() == "P") {
//					state = "PAUSE";
//				}
			while(aktTakt < 64 ) {
				Muzak kanal1Spieler = new Muzak();
				String audioFilePath = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + kanal1Array[aktTakt] + ".wav";
				kanal1Spieler.play(audioFilePath);	
				Muzak kanal2Spieler = new Muzak();
				audioFilePath = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + kanal2Array[aktTakt] + ".wav";
				kanal2Spieler.play(audioFilePath);	
				Muzak kanal3Spieler = new Muzak();
				audioFilePath = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + kanal3Array[aktTakt] + ".wav";
				kanal3Spieler.play(audioFilePath);	
				Muzak kanal4Spieler = new Muzak();
				audioFilePath = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + kanal4Array[aktTakt] + ".wav";
				kanal4Spieler.play(audioFilePath);	
				 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("kann nicht warten");
					}
				 aktTakt++;
			}
			}
		}
	}

}

