package Lesson_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");
            socket = server.accept();
            System.out.println("Клиент подключился");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

//            Start of my code
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Введите текст для передачи клиенту");
                        String inputConsole = sc.nextLine();
                        out.println(inputConsole);
                    }

                }
            }).start();
//            End of my code

            while (true) {
                String str = in.nextLine();

                if (str.equals("/end")) {
                    System.out.println("Клиент отключился");
                    break;
                }
//                out.flush();

                System.out.println(str);
//                out.println("echo : " + str);


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
