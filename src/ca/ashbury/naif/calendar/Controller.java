package ca.ashbury.naif.calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label;

    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        label.setText("OK");
//        Event lEvent = new Event(0, "WAKE UP!", LocalDateTime.now().plusSeconds(5), "Ottawa", "wake me up in 5 seconds");
        Calendar calendar = Calendar.getInstance();
        calendar.createEvent();
        calendar.saveEvents();
    }

}
