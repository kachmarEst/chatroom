/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket1;

/**
 *
 * @author oyhiuiu
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;

public class client extends Thread{
	
	DataInputStream dis = null;
	chat client;
	public client(DataInputStream dis,chat client) {
		this.dis = dis;
		this.client = client;
	}
	
	public void run() {
		while(true) {
			try {
				while(dis.available()==0) { 
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
				String Message = dis.readUTF();
				client.addMessageToScreen(Message);
								
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	};
}



















