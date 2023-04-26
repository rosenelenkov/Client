package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = bufferedReader.readLine();
                System.out.println(message);
            }
        } catch (SocketException e) {
            System.out.println("You left");
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
