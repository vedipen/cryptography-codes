// File Name Eve.java
import java.net.*;
import java.io.*;

public class Eve extends Thread {
  private ServerSocket serverSocket;
  public static int g=6,p=13;
  private static int z=7;

  public Eve(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(30000);
  }

  public int generateKey(int r2) {
      return (int)(Math.pow(r2,z))%p;
  }

  public String encrypt(String in,int k) {
    String out="";
    for(int i=0;i<in.length();i++) {
      out+=((char)(((in.charAt(i)-'a'+k)%26)+'a')+"").toUpperCase();
    }
    return out;
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

  public int findR3() {
    int r3=(int)(Math.pow(g,z))%p;
    System.out.println("R3 generated is "+r3);
    return r3;
  }

  public void run() {
    Boolean over=false,first=true;
    String plainText="";
    while(!over) {
      try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for client on port " +
        serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();

        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(server.getInputStream());
        int r3=findR3();
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF(r3+"");
        String r="R1";
        String human="Alice";
        if(!first) {
          r="R2";
          human="Bob";
        }
        int r1=Integer.parseInt(in.readUTF());
        System.out.println(r+" recieved is " + r1);
        int k1 = generateKey(r1);
        System.out.println("Generated key is "+k1);
        if(first) {
          String cipher = in.readUTF();
          System.out.println("Cipher recieved is " + cipher);
          plainText = decrypt(cipher,k1);
          System.out.println("Message sent from "+human+" is "+plainText);
        } else {
          System.out.print("Send message to "+human+" ("+plainText+") (Hit enter to send the same message): ");
          String temp=br.readLine();
          if(temp.length()!=0) {
            plainText=temp;
          }
          String cipher1 = encrypt(plainText,k1);
          System.out.println("Generated Cipher is : " + cipher1);
          System.out.println("Sent Cipher to Bob successfully");
          out.writeUTF(cipher1);
          over=true;
        }
        first=false;
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
    System.out.println("Private Key of Eve is : "+z);
    System.out.print("Enter the port you want to connect to : ");
    int port = Integer.parseInt(br.readLine());
    try {
      Thread t = new Eve(port);
      t.start();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
