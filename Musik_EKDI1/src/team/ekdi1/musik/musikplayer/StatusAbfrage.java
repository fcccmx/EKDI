package team.ekdi1.musik.musikplayer;

import java.util.Scanner;

public class StatusAbfrage extends Thread{
	public int status = 0;

	@Override
	public void run() {
        Scanner sc = new Scanner(System.in); 

		while(true) {
			
			System.out.println("1. Pause"); 
            System.out.println("2. Fortsetzen"); 
            System.out.println("3. Von vorne"); 
            System.out.println("4. Stop"); 
            this.status = sc.nextInt();
		}
		
	}
	
	public int getStatus() {
		return this.status;
	}

}
