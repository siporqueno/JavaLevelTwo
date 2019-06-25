package Lesson_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;
    @FXML
    public Button btnSend;

    public void onActionBtnSend(ActionEvent actionEvent) {
        textArea.appendText(textField.getText() + "\n");
        textField.setText("");
    }

    public void onActionTextField(ActionEvent actionEvent) {
        textArea.appendText(textField.getText() + "\n");
        textField.setText("");
    }
}
