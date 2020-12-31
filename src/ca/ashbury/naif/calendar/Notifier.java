package ca.ashbury.naif.calendar;

import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Notifier extends TimerTask {

    private Event event;
    private Calendar calendar;

    public Notifier(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            Calendar.getInstance().getNotify().setText("Event: '" + event.getName() + "'");
        });
    }

}
