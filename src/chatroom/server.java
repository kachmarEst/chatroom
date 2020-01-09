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
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javafx.beans.property.SimpleListProperty;

public class server extends Observable{
	public Map<Socket,String> Sockets  = new HashMap<>();
	final static int PORT  = 3030;
	public static void main(String[] args) {
		new server(PORT);
	}
	
	public server(int PORT) {
		ServerSocket serverSocket = null;
	try {
			 serverSocket = new ServerSocket(PORT);
			System.out.println("Server is Listning in "+PORT+"...");
			while(true) {
				Socket socket = serverSocket.accept();
				setChanged();
				notifyObservers("NewUSer");
				serverthread ST = new serverthread(socket,this);
				ST.start();
			}
			
		} catch (IOException e) {
			System.out.println("Server Faild To connect...");
			e.printStackTrace();
		}finally {
			try {
				if(serverSocket !=null) serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized void addSocket (Socket socket,String UserNam) {
		Sockets.put(socket,UserNam);
	
	}
	
}

