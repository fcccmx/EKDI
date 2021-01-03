package team.ekdi1.musik.musikplayer;


public class Musikspieler {
	StatusAbfrage statusAbfrage = new StatusAbfrage();
	
	public Musikspieler() {
		this.statusAbfrage.start();
	}

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
		Boolean play = true;
		int aktTakt = 0;
		String state = "PLAY";
		LadeDatei fL = new LadeDatei();	
		
		this.liedArray = fL.csvRead2DArray("song2.txt");

		while(aktTakt < 20 && play == true) {
			Muzak kanal1Spieler = new Muzak();
			String audioFilePath1 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][0] + ".wav";
			String audioFilePath2 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][1] + ".wav";
			String audioFilePath3 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][2] + ".wav";
			String audioFilePath4 = System.getProperty("user.dir") +"\\tonauswahl\\" + this.liedArray[aktTakt][3] + ".wav";
			
			kanal1Spieler.play(audioFilePath1, audioFilePath2,audioFilePath3,audioFilePath4);	
			
			aktTakt++;
			
			if (this.statusAbfrage.status == 4) {
				play = false;
			}
			
			if(this.statusAbfrage.status == 3) {
				aktTakt = 0;
				this.statusAbfrage.status = 2;
			}
			
			while(this.statusAbfrage.status == 1) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("beendet");

	}



	public static void main(String[] args) {
		Musikspieler mS = new Musikspieler();
		mS.MusikspielerSchleife();

	}
}



