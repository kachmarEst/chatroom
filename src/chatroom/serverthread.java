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
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import socket1.chat;
import javafx.collections.FXCollections;

public class serverthread extends Thread{
	 String UserName;
	 Socket socket;
	 server Server_Instance;
	private DataInputStream dis;
	private DataOutputStream dos;

	private boolean ShouldRun = true;
	 
	 public serverthread(Socket socket,server server) throws IOException {
		 this.socket = socket;
		 this.Server_Instance = server;
		 dis = new DataInputStream(socket.getInputStream());
		 dos = new DataOutputStream(socket.getOutputStream());
		 UserName = this.getName();
		
	 }
	 
	private void sendMessageToAll(String message) throws IOException {
		//a copy of the current Users
		Iterator<Entry<Socket, String>> mapIterator =  Server_Instance.Sockets.entrySet().iterator();
		while (mapIterator.hasNext()) {
			Entry<Socket, String> entry = mapIterator.next();
			new DataOutputStream(entry.getKey().getOutputStream()).writeUTF(UserName +" : "+ message);;
			
		}
	}
	
	public void run() {
		try {
			//Reads the first data that the User sent to the Server which is the "USERNAME"
			UserName = dis.readUTF();
			System.out.println(UserName+" is Connected ");
			Server_Instance.addSocket(socket,UserName);
			System.out.println(Server_Instance.Sockets.values()+"in the server");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while(ShouldRun) {
			System.out.println("SERVER : is closed ");

			try {
				 if(socket.isClosed()) 
				 {
					ShouldRun = false; 
				 	break;
				 }
				while(dis.available()==0) {
				
				}
				sendMessageToAll(dis.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.Server_Instance.Sockets.remove(this.socket);
		System.out.println(UserName+" is disconnected from the Server");
		System.out.println(this.Server_Instance.Sockets.size()+" USers left");


	}
	
}

