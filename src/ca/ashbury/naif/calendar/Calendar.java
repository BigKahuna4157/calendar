package ca.ashbury.naif.calendar;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.control.Label;

public class Calendar {

    private static final String DATABASE_FILENAME = "calendar.json";
    private static final String DATABASE_LOCATION = "";

    private static Calendar INSTANCE;

    private Map<Long, Event> events;
    private Label notify;

    private Calendar() {
        events = new HashMap<>();
        loadEvents();
    }

    public static Calendar getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Calendar();
        }
        return INSTANCE;
    }

    public void createEvent(String name, LocalDateTime time, String location, String note) {
        Event lEvent = new Event(name, time, location, note);
        events.put(lEvent.getId(), lEvent);
        saveEvents();
    }

    public void modifyEvent(Long id, String name, LocalDateTime time, String location, String note) {
        Event lEvent = new Event(id, name, time, location, note);
        events.put(lEvent.getId(), lEvent);
        saveEvents();
    }

    public void deleteEvent(Long id) {
        events.remove(id);
        saveEvents();
    }

    Collection<Event> getEvents() {
        return events.values();
    }

    private void loadEvents() {
        try {
            Event lEvent;
            Path lDatabase = Paths.get(DATABASE_LOCATION, DATABASE_FILENAME);
            JSONArray lEvents = new JSONArray(Files.readString(lDatabase));
            for (int i = 0; i < lEvents.length(); i++) {
                lEvent = new Event(lEvents.getJSONObject(i));
                events.put(lEvent.getId(), lEvent);
            }
        } catch (IOException ignore) {
        }
    }

    public void saveEvents() {
        try {
            Path lDatabase = Paths.get(DATABASE_LOCATION, DATABASE_FILENAME);
            JSONArray lEvents = new JSONArray(events.values().stream().map(Event::getJson).collect(Collectors.toList()));
            Files.writeString(lDatabase, lEvents.toString(2));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Label getNotify() {
        return notify;
    }

    public void setNotify(Label notify) {
        this.notify = notify;
    }

}
