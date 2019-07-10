package Lesson_8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    DataOutputStream out;
    DataInputStream in;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    // цикл авторизации.
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) {
                            String[] token = str.split(" ");
                            String newNick =
                                    AuthService.getNickByLoginAndPass(token[1], token[2]);
                            //      Start of my code, home work of Lesson 7, task 2
                            if (newNick != null && !server.isNickOn(newNick))
                            //      End of my code, home work of Lesson 8, task 2
                            {
                                sendMsg("/authok");
                                nick = newNick;
                                server.subscribe(this);
                                break;
                            } else {
                                //      Start of my code, home work of Lesson 7, task 2
                                sendMsg(server.isNickOn(newNick) ? "Such User is already authorized" : "Wrong login / password");
                                //      End of my code, home work of Lesson 8, task 2
                            }
                        }

                        //            Start of my code, home work of Lesson 8
                        if (str.equals("/timeout120sec")) {
                            out.writeUTF("/timeout120sec");
                            break;
                        }
                        //            End of my code, home work of Lesson 8
                    }

                    //Цикл для работы
                    while (true) {
                        String str = in.readUTF();

                        if (str.equals("/end")) {
                            out.writeUTF("/end");
//                            System.out.println("Клиент отключился");
                            break;
                        }

//                        Start of my code, home work of Lesson 7, task 1
                        if (str.startsWith("/w")) {
                            String[] token = str.split(" ", 3);
                            server.sendMsgFromNickToNick(token[2], getNick(), token[1]);
                        } else {
                            System.out.println(str);
                            server.broadcastMsg(str, getNick());
                        }
//                        End of my code, home work of Lesson 8, task 1

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                    System.out.println("Клиент отключился");
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
