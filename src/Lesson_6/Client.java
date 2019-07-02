package Lesson_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        final String IP_ADDRESS = "localhost";
        final int PORT = 8189;

        try {
            socket = new Socket(IP_ADDRESS, PORT);

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            Thread inputThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
//                out.flush();
                        System.out.println(str);
                    }

                }
            });

            inputThread.setDaemon(true);
            inputThread.start();


            while (true) {
//                        System.out.println("Введите текст для передачи серверу");
                String inputConsole = sc.nextLine();
                out.println(inputConsole);

                if (inputConsole.equals("/end")) {
                    System.out.println("Клиент отключился");
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
