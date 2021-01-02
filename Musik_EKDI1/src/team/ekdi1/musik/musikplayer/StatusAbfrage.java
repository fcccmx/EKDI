package team.ekdi1.musik.musikplayer;

import java.util.Scanner;

public class StatusAbfrage extends Thread{
	public int status = 0;

	@Override
	public void run() {
        Scanner sc = new Scanner(System.in); 

		while(true) {
			
			System.out.println("1. pause"); 
            System.out.println("2. resume"); 
            System.out.println("3. restart"); 
            System.out.println("4. stop"); 
            System.out.println("5. Jump to specific time"); 
            this.status = sc.nextInt();
		}
		
	}
	
	public int getStatus() {
		return this.status;
	}

}
