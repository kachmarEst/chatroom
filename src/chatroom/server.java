package chatroom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	
	
	public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(4020);
        Socket client;
        int  count=0;
        while (true){

            //********* Receive Data
            System.out.println("Server Opened");
            client = server.accept();
            count++;
            System.out.println("=====> New CLient ");

            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            Thread t = new ChatConfig(client,dis, dos);
            t.start();
        }
    }
	
	

}