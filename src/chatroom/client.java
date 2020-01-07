package chatroom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
public static int i;

public static void main(String[] args) throws Exception {


    Scanner msg = new Scanner(System.in);
        Socket socket = new Socket(InetAddress.getLocalHost(), 4020);
        System.out.println(socket);

    DataInputStream dis = new DataInputStream(socket.getInputStream());
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

    while (true){
            System.out.println(dis.readUTF());
            System.out.println("Entre votre message: ");
            String message = msg.nextLine();
                    dos.writeUTF(message);
        if(message.equals("close"))
        {
            System.out.println("Closing connection : " + socket+"....");
            socket.close();
            System.out.println("closed");
            break;
        }
        

            String received = dis.readUTF();
            System.out.println(received);
        }
}


}
