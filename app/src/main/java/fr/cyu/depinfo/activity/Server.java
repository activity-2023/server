package fr.cyu.depinfo.activity;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import com.google.common.hash.Hashing;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket sock = new ServerSocket(65432);
        System.out.println("Server listening on port " + 65432);

        while (true) {
            Socket clientSocket = sock.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress());
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Random rand = new Random();
            int nounce = rand.nextInt();
            nounce = nounce < 0 ? -nounce : nounce;
            out.println(nounce);

            String data;
            data = br.readLine();
            if (!data.matches("testRoom")) {
                out.println("False");
                out.close();
                br.close();
                clientSocket.close();
                continue;
            } else {
                out.println("True");
            }

            data = br.readLine();
            if (!data.matches("testUser")) {
                out.println("False");
                out.close();
                br.close();
                clientSocket.close();
                continue;
            } else {
                out.println("True");
            }

            data = br.readLine();
            if (!data.equals(Hashing.sha256().hashString("1234"+nounce, StandardCharsets.UTF_8).toString())) {
                out.println("False");
            } else {
                out.println("True");
            }

            out.close();
            br.close();
            System.out.println("Connection to " + clientSocket.getInetAddress() + " closed");
            clientSocket.close();
        }
    }
}
