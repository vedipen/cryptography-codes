// File Name Eve.java
import java.net.*;
import java.io.*;

public class Eve extends Thread {
  private ServerSocket serverSocket;

  private String sumOfDigits(String s) {
    long temp=0;
    for(char a : s.toCharArray()) {
      temp+=(int)(a-'0');
    }
    System.out.println("Generated Challenge : "+temp);
    return temp+"";
  }

  private String reverseNumber(String s) {
    String s1=new StringBuilder(s).reverse().toString();
    System.out.println("Generated Challenge : "+s1);
    return s1;
  }

  public Eve(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(30000);
  }

  public void run() {
    Boolean over=false,first=true;
    String plainText="";
    int connected=0;
    while(!over) {
      try {
        connected++;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for client on port " +
        serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();

        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(server.getInputStream());
        String choice = in.readUTF();
        System.out.println("Connected with "+choice);
        String number,result="";
        if(choice.equals("Alice")) {
          System.out.println("Reading OTP...");
          number=in.readUTF();
          System.out.println("OTP Recieved : "+number);
          result=sumOfDigits(number);
        } else if(choice.equals("Bob")) {
          System.out.println("Reading OTP...");
          number=in.readUTF();
          System.out.println("OTP Recieved : "+number);
          result=reverseNumber(number);
        } else {
          System.out.println("Unknown User.");
          number=in.readUTF();
          System.out.println("OTP Recieved : "+number);
          result=number;
        }
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF(result);
        String resultRecieved = in.readUTF();
        if(resultRecieved.equals("true")) {
          System.out.println("Response positive. \nOTP authentication successful.");
        } else {
          System.out.println("Response negative. \nOTP authentication unsuccessful.\nTry Again\n");
        }
        if(connected>=2) {
          over=true;
        }
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
