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
        label.setText("OK");
        LocalDateTime fiveSecondsLater = LocalDateTime.now().plusSeconds(5);
        Date fiveSecondsLaterAsDate = Date.from(fiveSecondsLater.atZone(ZoneId.systemDefault()).toInstant());
        new Timer().schedule(new Notifier(label, "WAKE UP!"), fiveSecondsLaterAsDate);
    }

}
