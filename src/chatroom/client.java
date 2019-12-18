package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client implements Runnable {
public static int i;
	public static void main(String[] args) {
		// TODO Auto-generated method s;tub
		i=1;
		new Thread(new client()).start();
		new Thread(new client()).start();
		new Thread(new client()).start();
		new Thread(new client()).start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket server = new Socket("localhost",4020);
		
		OutputStream os = server.getOutputStream();
		InputStream is = server.getInputStream();
		PrintWriter pw = new PrintWriter(os);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
pw.write("this is client"+i+" \n");
pw.flush();
i++;
String msg = null;
while((msg=br.readLine()) != null) {
	System.out.println("the message backed is :"+msg);
}
pw.close();
br.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
