package team.ekdi1.musik.musikplayer;

import java.util.Scanner;

import javax.swing.JTextField;

import team.ekdi1.musik.main.MainFrame;

public class StatusAbfrage extends Thread{
	public String status = "P";
	public StatusAbfrage() {
		
	} 
	
	public StatusAbfrage(String status){
		this.status=status;
	}

	@Override
	public void run() {
        Scanner sc = new Scanner(System.in); 
        try {
			synchronized (this) {
	        	while(true) {
				
					System.out.println("P. Pause"); 
		            System.out.println("F. Fortsetzen"); 
		            System.out.println("V. Von vorne"); 
		            System.out.println("S. Stop"); 
		            this.status = sc.next();
		            
	        	}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
     
		
		
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status=status;
	}

}
