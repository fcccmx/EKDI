package team.ekdi1.musik.musikplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;


public class Musikspieler {

	private String[][] liedArray;


	private int getLiedArrayLength() {
		for (int i = 0; i < 64; i++) {
			if (this.liedArray[0][i] == null) {
				return i + 1;
			}
		}
		return 64;

	}

	public void MusikspielerSchleife() {
		int aktTakt = 0;
		String state = "PLAY";
		LadeDatei fL = new LadeDatei();
		System.out.println(System.getProperty("user.dir"));
		this.liedArray = fL.csvRead2DArray("C:\\Users\\jsste\\Desktop\\song2.txt");

		while(aktTakt < 3 ) {
			Muzak kanal1Spieler = new Muzak();
			String audioFilePath1 = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + this.liedArray[aktTakt][0] + ".wav";
			String audioFilePath2 = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + this.liedArray[aktTakt][1] + ".wav";
			String audioFilePath3 = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + this.liedArray[aktTakt][2] + ".wav";
			String audioFilePath4 = "C:\\Users\\jsste\\Downloads\\anhang(2)\\anhang\\tonauswahl\\" + this.liedArray[aktTakt][3] + ".wav";

			kanal1Spieler.play(audioFilePath1, audioFilePath2,audioFilePath3,audioFilePath4);	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
				System.out.println("kann nicht warten");
			}
			aktTakt++;
		}

	}



	public static void main(String[] args) {
		Musikspieler mS = new Musikspieler();
		mS.MusikspielerSchleife();

	}
}



