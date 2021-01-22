import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) throws Exception {
        int data = 1;
        ServerSocket sersock = new ServerSocket(5219);
        while (data == 1) {
            System.out.println("Server ready for connection....");
            Socket sock = sersock.accept();
            System.out.println("Connection Established\nWaiting for Client Request.");
            InputStream istream = sock.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(istream));
            String fname = br.readLine();
            if (fname.equals("exit")) {
                continue;
            }
            BufferedReader contentRead = new BufferedReader(new FileReader(fname));
            OutputStream ostream = sock.getOutputStream();
            PrintWriter pwrite = new PrintWriter(ostream, true);
            String str;
            while ((str = contentRead.readLine()) != null) {
                pwrite.println(str);
            }
            System.out.println("\nFile Contents sent successfully\n\n");
            pwrite.close();
            br.close();
            contentRead.close();
            sock.close();
        }
        sersock.close();
    }
}

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Server Address: ");
        String address = sc.nextLine();
        Socket sock = new Socket(address, 5219);
        System.out.println("Enter the file name ");
        String fname = sc.nextLine();

        // Send the data to server
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        // receive data
        InputStream istream = sock.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(istream));
        String FileContent = "";
        String str;

        while ((str = br.readLine()) != null) {
            System.out.println(str);
            pwrite.println(str);
            FileContent += str;
        }
        System.out.println("File : " + fname + " Received.");
        PrintWriter Writer = new PrintWriter("Client" + fname);
        Writer.write(FileContent);
        Writer.close();
        pwrite.close();
        br.close();
        sock.close();
        sc.close();
    }
}