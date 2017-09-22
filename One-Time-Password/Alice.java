// File Name Alice.java
import java.net.*;
import java.io.*;

public class Alice {

  private static String sumOfDigits(String s) {
    long temp=0;
    for(char a : s.toCharArray()) {
      temp+=(int)(a-'0');
    }
    System.out.println("Generated Challenge : "+temp);
    return temp+"";
  }

  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String serverName="localhost";
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
      System.out.println("Enter the name of the user (default: Alice[Enter]) : ");
      String name=br.readLine();
      if(name.length()==0) {
        name="Alice";
      }
      out.writeUTF(name);
      System.out.println("Enter OTP : ");
      String inputOTP = br.readLine();
      System.out.println("Sending OTP...");
      out.writeUTF(inputOTP);
      String outputOTP=sumOfDigits(inputOTP);
      String recievedOutput=in.readUTF();
      System.out.println("Challenge Recieved : "+recievedOutput);
      String same="false";
      if(recievedOutput.equals(outputOTP)) {
        same="true";
      } else {
        same="false";
      }
      System.out.println("Sendng Match : "+same);
      if(same.equals("true")) {
        System.out.println("Successfully Authenticated "+name);
      } else {
        System.out.println("Couldn't Authenticate "+name+". Try Again.");
      }
      out.writeUTF(same);
      client.close();
    }catch(IOException e) {
      System.out.println("Errrr! Couldn't connect. Stats -->");
      e.printStackTrace();
    }
  }
}
