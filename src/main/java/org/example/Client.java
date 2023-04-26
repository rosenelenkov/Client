package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String name;
        String reply;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        reply = sc.nextLine();
        name = reply;

        try (Socket socket = new Socket("localhost", 7500)) {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            ClientThread threadClient = new ClientThread(socket);
            new Thread(threadClient).start(); // start thread to receive message

            printWriter.println(reply + " : has joined");
            do {
                String message = (name + " : ");
                reply = sc.nextLine();
                if (reply.equals("exit")) {
                    printWriter.println("exit");
                    break;
                }
                printWriter.println(message + reply);
            } while (!reply.equals("exit"));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
