package Lesson_8.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;
    @FXML
    public Button btnSend;
    @FXML
    public HBox upperPanel;
    @FXML
    public HBox bottomPanel;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public ListView<String> clientList;

    private boolean isAuthorized;

    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        //            Start of my code, home work of Lesson 8
        new Thread(()-> {
            try {
                Thread.sleep(60000);
                System.out.println("60 sec have passed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!isAuthorized) {
                try {
                    out.writeUTF("/end");
                    System.out.println("60 sec have passed without authorization");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//
    }*/

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if (isAuthorized) {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
        } else {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

//            Start of my code, home work of Lesson 8
            /*new Thread(()-> {
                try {
                    Thread.sleep(60000);
                    System.out.println("60 sec have passed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isAuthorized) {
                    try {
                        out.writeUTF("/end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();*/
//            End of my code, home work of Lesson 8

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл авторизации
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/authok")) {
                                setAuthorized(true);
                                break;
                            }
                            textArea.appendText(str + "\n");
                        }
                        //цикл работы
                        while (true) {
                            String str = in.readUTF();

                            if (str.startsWith("/")) {

                                if (str.equals("/end")) {
                                    System.out.println("Клиент отключился");
                                    break;
                                }

                                if (str.startsWith("/clientlist ")) {
                                    String[] tokens = str.split(" ");
                                    //
                                    Platform.runLater(() -> {
                                        clientList.getItems().clear();
                                        for (int i = 0; i < tokens.length; i++) {
                                            clientList.getItems().add(tokens[i]);
                                        }
                                    });
                                }

                            } else textArea.appendText(str + "\n");

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionTextFieldAndBtnSend(ActionEvent actionEvent) {
        try {
            out.writeUTF(textField.getText());
            textField.setText("");
            textField.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " "
                    + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickClientList(MouseEvent mouseEvent) {
        String receiver = clientList.getSelectionModel().getSelectedItem();
        textField.setText("/w " + receiver + " ");
    }
}