package controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jan-Jaap Spies
 * doel: het kunnen communiceren met een client via een socket 
 */

public class Server {

    private final static int PORT_NUMBER = 5555;

    public static void main(String[] args) {
        Socket socket;
        ServerSocket serverSocket;
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        System.out.println("server on port " + PORT_NUMBER );
        System.out.println();

        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    String messageFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + messageFromClient);

                    bufferedWriter.write("Message received successfully");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (messageFromClient.equalsIgnoreCase("BYE")) {
                        break;
                    }
                }
                socket.close();
                inputStreamReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        
    }
}
