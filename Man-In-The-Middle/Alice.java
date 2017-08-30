// File Name Alice.java
import java.net.*;
import java.io.*;

public class Alice {

  public static int g=6,p=13;
  private static int x=3;

  public static int generateKey(int r1) {
      return (int)(Math.pow(r1,x))%p;
  }

  public static String encrypt(String in,int k) {
    String out="";
    for(int i=0;i<in.length();i++) {
      out+=((char)(((in.charAt(i)-'a'+k)%26)+'a')+"").toUpperCase();
    }
    return out;
  }

  public static int findR1() {
    int r1=(int)(Math.pow(g,x))%p;
    System.out.println("R1 generated is "+r1);
    return r1;
  }

  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Public Key p is : "+p);
    System.out.println("Public Key g is : "+g);
    System.out.println("Private Key of Alice is : "+x);
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
      int r1=findR1();
      int r3 = Integer.parseInt(in.readUTF());
      System.out.println("R3 recieved from Eve is "+r3);
      System.out.println("Sending R1 to Alice");
      out.writeUTF(r1+"");
      int k1 = generateKey(r1);
      System.out.println("Generated key is "+k1);
      System.out.print("Enter message to send : ");
      String input = br.readLine();
      String output = encrypt(input,k1);
      System.out.println("Generated Cipher is : " + output);
      System.out.println("Sent Cipher to Eve suceessfully");
      out.writeUTF(output);

      client.close();
    }catch(IOException e) {
      System.out.println("Errrr! Couldn't connect. Stats -->");
      e.printStackTrace();
    }
  }
}
