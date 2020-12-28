package ca.ashbury.naif.calendar;

import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Notifier extends TimerTask {

    private Label label;

    private String message;

    public Notifier(Label label, String message) {
        this.label = label;
        this.message = message;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            label.setText(message);
        });
    }

}
