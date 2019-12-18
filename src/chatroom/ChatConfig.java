package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatConfig implements Runnable{
private Socket client;
public ChatConfig(Socket client) {
	this.client = client;
}
@Override
public void run() {
	// TODO Auto-generat ed method stub
	try{
	OutputStream os = this.client.getOutputStream();
	InputStream is = this.client.getInputStream();
	PrintWriter pw = new PrintWriter(os);
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	
	String msg = null;
	while((msg=br.readLine()) != null) {
		pw.write(msg+"\n");
		pw.flush();
		System.out.println("messaged Client with "+msg);
		
	}
	
	pw.close();
	br.close();
	this.client.close();
}catch(IOException e) {
	e.printStackTrace();
}
}

}
