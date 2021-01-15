package team.ekdi1.musik.musikplayer;

import java.util.Scanner;

public class StatusAbfrage extends Thread{
	public String status = "P";

	
	StatusAbfrage(String status) {
		this.status=status;
	}
	
	StatusAbfrage() {
		
	}
	

	@Override
	public void run() {
        Scanner sc = new Scanner(System.in); 

		while(true) {
			
			System.out.println("P. Pause"); 
            System.out.println("F. Fortsetzen"); 
            System.out.println("V. Von vorne"); 
            System.out.println("S. Stop"); 
            this.status = sc.next();
		}
		
	}
	
	public String getStatus() {
		return this.status;
	}

}
