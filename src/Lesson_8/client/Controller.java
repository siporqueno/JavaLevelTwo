package Lesson_8.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    // Added nick
    private String nick;

    private boolean isAuthorized;

    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;


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

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл авторизации
                        while (true) {
                            String str = in.readUTF();

                            //            Start of my code, home work of Lesson 8
                            if (str.equals("/timeout120sec")) {
                                break;
                            }
                            //            End of my code, home work of Lesson 8

                            // substituted equals with startsWith
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                //      Got nick
                                nick = str.split(" ")[1];
                                break;
                            }
                            textArea.appendText(str + "\n");
                        }

                        // Added nick to the title of the Stage
                        Platform.runLater(() -> {
                            Stage stage = (Stage) textArea.getScene().getWindow();
                            stage.setTitle("User: " + nick);
                        });

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

            //            Start of my code, home work of Lesson 8
            new Thread(() -> {
                try {
                    Thread.sleep(120000);
                    System.out.println("The time for authorisation (120 sec) is over.");
                    textArea.appendText("The time for authorisation (120 sec) is over.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isAuthorized) {
                    try {
                        out.writeUTF("/timeout120sec");
                        out.writeUTF("/end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            End of my code, home work of Lesson 8

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
