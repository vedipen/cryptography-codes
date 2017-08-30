// File Name Bob.java
import java.net.*;
import java.io.*;

public class Bob {

  public static int g=6,p=13;
  private static int y=10;

  public static int generateKey(int r1) {
      return (int)(Math.pow(r1,y))%p;
  }

  public static String decrypt(String in,int k) {
    String out="";
    for(int i=0;i<in.length();i++) {
      int z=(in.toLowerCase().charAt(i)-'a'-k+26);
      if(z>=25) {
        z%=26;
      }
      out+=((char)(z+'a')+"");
    }
    return out;
  }

  public static int findR2() {
    int r1=(int)(Math.pow(g,y))%p;
    System.out.println("R1 generated is "+r1);
    return r1;
  }

  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Public Key p is : "+p);
    System.out.println("Public Key g is : "+g);
    System.out.println("Private Key of Bob is : "+y);
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
      InputStream inFromServer = client.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);
      OutputStream outToServer = client.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);
      int r2=findR2();
      int r1 = Integer.parseInt(in.readUTF());
      System.out.println("R3 recieved from Eve is "+r1);
      System.out.println("Sending R2 to Eve");
      out.writeUTF(r2+"");
      // out.writeUTF("Hello from " + client.getLocalSocketAddress());
      int k = generateKey(r1);
      System.out.println("Generated key is "+k);
      String cipher = in.readUTF();
      System.out.println("Cipher recieved is " + cipher);
      String plainText = decrypt(cipher,k);
      System.out.println("Message sent from Eve is "+plainText);
      client.close();
    }catch(IOException e) {
      System.out.println("Errrr! Couldn't connect. Stats -->");
      e.printStackTrace();
    }
  }
}
