package team.ekdi1.musik.komponix;

import java.util.ArrayList;

import java.util.*;



import team.ekdi1.musik.musikplayer.*;
public class Komponix {
	
	public static ArrayList<String> Randomarray(int takt) {
		ArrayList<String> randomArray = new ArrayList<String>();
		Random r=new Random();
		for (int i = 0; i < takt; i++) {
			for (int j = 0; j < 4; j++) {
				int randomInt=r.nextInt(48);
				if (randomInt==48) {
					randomArray.add("silence_0.5s");
				}else {
					randomArray.add(Integer.toHexString(randomInt));
				}
			}
		}
		return randomArray;
	}

	public static void main(String[] args){
		ArrayList<String> rA = new ArrayList<String>();
		Komponix t=new Komponix();
		rA=t.Randomarray(20);
		System.out.println(rA);
		
//		LadeDatei.csvWrite(rA, "C:\\Users\\jsste\\eclipse-workspace\\ekdi2\\EKDI\\Musik_EKDI1\\src\\team\\ekdi1\\musik\\musikplayer\\song2.txt");
		
	}
}
