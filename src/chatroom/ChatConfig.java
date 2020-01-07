package chatroom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatConfig extends Thread {

	final Socket client;

final DataInputStream dis;
final DataOutputStream dos;
public ChatConfig(Socket client,DataInputStream dis,DataOutputStream dos) {
	this.dis = dis;
	this.dos = dos;
	this.client = client;
}
@Override
public void run() {
    String messaged;
    while (true)
    {
        try {
            dos.writeUTF("send random message :");

            messaged = dis.readUTF();

            if(messaged.equals("close"))
            {
                System.out.println("Client " + this.client);
                System.out.println("Closing the connection...");
                this.client.close();
                System.out.println("closed");
                break;
            }

            System.out.println("message recieved : "+messaged);
        
                   dos.writeUTF("Got your message and i wanted to thank you ;) , here is your meessage: "+messaged);
                   
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    try
    {
        this.dis.close();
        this.dos.close();

    }catch(IOException e){
        e.printStackTrace();
    }
}

}
