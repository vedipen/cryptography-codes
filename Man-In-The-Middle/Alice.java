// File Name Alice.java
import java.net.*;
import java.io.*;

public class Alice extends Thread {
  private ServerSocket serverSocket;

  public Alice(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(30000);
  }

  public void run() {
    while(true) {
      try {
        System.out.println("Waiting for client on port " +
        serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();

        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(server.getInputStream());

        System.out.println(in.readUTF());
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
        + "\nGoodbye!");
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
      Thread t = new Alice(port);
      t.start();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
