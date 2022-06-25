package controller;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jan-Jaap Spies
 * doel
 */

public class Client {
    private final static int PORT_NUMBER = 5555;
    private final static String LOCALHOST = "localhost";
    private final static String MAGIC = "BYE";


    public static void main(String[] args) {
        System.out.println("You are on the client");
        System.out.println("Send messages to the server on the console");
        System.out.println("Type BYE to exit.");

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {

            socket = new Socket(LOCALHOST, PORT_NUMBER);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Server: " + bufferedReader.readLine());

                if (messageToSend.equalsIgnoreCase(MAGIC)) {
                    break;
                }

            }
            socket.close();
            inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null)
                        socket.close();
                    if (inputStreamReader != null)
                        inputStreamReader.close();
                    if (outputStreamWriter != null)
                        outputStreamWriter.close();
                    if (bufferedReader != null)
                        bufferedReader.close();
                    if (bufferedWriter != null)
                        bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        }
}
