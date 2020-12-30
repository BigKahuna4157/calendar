package ca.ashbury.naif.calendar;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    private Calendar calendar;

    public Controller() {
        calendar = Calendar.getInstance();
    }

    @FXML
    TextField tfTime;

    @FXML
    TextField tfName;

    @FXML
    TextArea taLocation;

    @FXML
    TextArea taNote;

    @FXML
    TextArea taNotify;

    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        System.out.println(tfTime.getText());
        LocalDateTime time = LocalDateTime.parse(tfTime.getText().replace(" ","T"));
        System.out.println(time);
        System.out.println(tfName.getText());
        System.out.println(taLocation.getText());
        System.out.println(taNote.getText());
    }
/*
    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        calendar.createEvent("WAKE UP!", LocalDateTime.now().plusSeconds(5), "Ottawa", "wake me up in 5 seconds");
    }
*/
}
