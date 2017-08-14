// File Name Bob.java
import java.net.*;
import java.io.*;

public class Bob {

  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String serverName="localhost";
    // System.out.print("Enter Server Name to connect to (default - 'localhost') : ");
    // String serverNameTemp = br.readLine();
    // if(serverNameTemp.length()!=0) {
    //   serverName=serverNameTemp;
    // }
    System.out.print("Enter port you want to connect to : ");
    int port = Integer.parseInt(br.readLine());
    try {
      System.out.println("Connecting to " + serverName + " on port " + port);
      Socket client = new Socket(serverName, port);

      System.out.println("Just connected to " + client.getRemoteSocketAddress());
      OutputStream outToServer = client.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);

      out.writeUTF("Hello from " + client.getLocalSocketAddress());
      InputStream inFromServer = client.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);

      System.out.println("Server says " + in.readUTF());
      client.close();
    }catch(IOException e) {
      System.out.println("Errrr! Couldn't connect. Stats -->");
      e.printStackTrace();
    }
  }
}
