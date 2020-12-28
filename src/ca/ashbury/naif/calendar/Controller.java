package ca.ashbury.naif.calendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label;

    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        Event lEvent = new Event(0, "WAKE UP!", LocalDateTime.now().plusSeconds(5), "Ottawa", "wake me up in 5 seconds");
    }

}
