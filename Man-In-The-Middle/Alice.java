// File Name Alice.java
import java.net.*;
import java.io.*;

public class Alice extends Thread {
  private ServerSocket serverSocket;
  public static int g=6,p=13;
  private static int x=3;

  public Alice(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(30000);
  }

  public int generateKey(int r2) {
      return (int)(Math.pow(r2,x))%p;
  }

  public String encrypt(String in,int k) {
    String out="";
    for(int i=0;i<in.length();i++) {
      out+=((char)(((in.charAt(i)-'a'+k)%26)+'a')+"").toUpperCase();
    }
    return out;
  }

  public int findR1() {
    int r1=(int)(Math.pow(g,x))%p;
    System.out.println("R1 generated is "+r1);
    return r1;
  }

  public void run() {
    Boolean over=false;
    while(!over) {
      try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for client on port " +
        serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();

        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(server.getInputStream());
        System.out.println("Sending R1 to Bob");
        int r1=findR1();
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF(r1+"");
        int r2=Integer.parseInt(in.readUTF());
        System.out.println("R2 recieved from Bob is " + r2);
        int k = generateKey(r2);
        System.out.println("Generated key is "+k);
        System.out.print("Enter message to send : ");
        String input = br.readLine();
        String output = encrypt(input,k);
        System.out.println("Generated Cipher is : " + output);
        System.out.println("Sending Cipher to Bob..");
        out.writeUTF(output);
        // out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
        // + "\nGoodbye!");
        over=true;
        server.close();

      } catch(SocketTimeoutException s) {
        System.out.println("Socket timed out!");
        break;
      } catch(IOException e) {
        e.printStackTrace();
        break;
      }
    }
  }

  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Public Key p is : "+p);
    System.out.println("Public Key g is : "+g);
    System.out.println("Private Key of Alice is : "+x);
    System.out.print("Enter the port you want to connect to : ");
    int port = Integer.parseInt(br.readLine());
    try {
      Thread t = new Alice(port);
      t.start();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
