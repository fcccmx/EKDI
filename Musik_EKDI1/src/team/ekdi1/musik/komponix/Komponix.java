package team.ekdi1.musik.komponix;

import java.util.ArrayList;

import java.util.*;

public class Komponix {
	
	public ArrayList<String> Randomarray(int takt) {
		ArrayList<String> randomArray = new ArrayList<String>();
		Random r=new Random();
		for (int i = 0; i < takt; i++) {
			for (int j = 0; j < 4; j++) {
				randomArray.add(Integer.toString(r.nextInt(46)+1));
			}
		}
		return randomArray;
	}

	public static void main(String[] args){
		ArrayList<String> rA = new ArrayList<String>();
		Komponix t=new Komponix();
		rA=t.Randomarray(20);
		System.out.println(rA);
		
	}
}
