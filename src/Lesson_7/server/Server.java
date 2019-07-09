package Lesson_7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {
    Vector<ClientHandler> clients;

    public Server() throws SQLException {
        AuthService.connect();
//        System.out.println(AuthService.getNickByLoginAndPass("login1","pass1"));

        ServerSocket server = null;
        Socket socket = null;

        try {
            clients = new Vector<>();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
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
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String str, String sender) {
        for (ClientHandler o : clients) {
            o.sendMsg(sender + ": " + str);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    //    Start of my code, home work of Lesson 7, task 1
    public void sendMsgFromNickToNick(String str, String sender, String receiver) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(sender) || o.getNick().equals(receiver)) {
                o.sendMsg("private [" + sender + " ] to [ " + receiver + " ] :" + str);
            }
        }
    }
    //    End of my code, home work of Lesson 8, task 1

    //    Start of my code, home work of Lesson 7, task 2
    public boolean isNickOn(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) return true;
        }
        return false;
    }
    //    End of my code, home work of Lesson 8, task 2

}
