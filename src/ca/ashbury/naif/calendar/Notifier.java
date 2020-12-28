package ca.ashbury.naif.calendar;

import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Notifier extends TimerTask {

    private Event event;

    public Notifier(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        System.out.println("Event: '" + event.getName() + "'");
    }

}
