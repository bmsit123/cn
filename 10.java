import java.io.*;
import java.net.*;

class UDPServer {
  public static void main(String[] args) throws IOException {
    byte buf[] = new byte[1024];
    int cport = 222, sport = 555;
    DatagramSocket serversocket = new DatagramSocket(sport);
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    InetAddress ia = InetAddress.getLocalHost();
    System.out.println("Server is Running...");
    while (true) {
      serversocket.receive(dp);
      String str2 = new String(dp.getData(), 0, dp.getLength());
      if (str2.equals("exit")) {
        System.out.println("Terminated...");
        break;
      }
      System.out.println("Client said : " + str2);
      String str3 = new String(br.readLine());
      buf = str3.getBytes();
      serversocket.send(new DatagramPacket(buf, str3.length(), ia, cport));
    }
  }
}

import java.io.*;
import java.net.*;

class UDPClient {

    public static void main(String[] args) throws IOException {
        byte buf[] = new byte[1024];
        int cport = 222, sport = 555;
        DatagramSocket clientsocket = new DatagramSocket(cport);
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println("Client is Running...");
        System.out.println("Type some text if u want to Quit type 'exit'.");
        while (true) {
            String str1 = new String(br.readLine());
            buf = str1.getBytes();
            if (str1.equals("exit")) {
                System.out.println("Terminated..");
                clientsocket.send(new DatagramPacket(buf, str1.length(), ia, sport));
                break;
            }
            clientsocket.send(new DatagramPacket(buf, str1.length(), ia, sport));
            clientsocket.receive(dp);
            String str4 = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Server said : " + str4);
        }
    }
}