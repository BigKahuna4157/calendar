package ca.ashbury.naif.calendar;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Calendar {

    private static final String DATABASE_FILENAME = "calendar.json";
    private static final String DATABASE_LOCATION = "";

    private static Calendar INSTANCE;

    Map<Long, Event> events;

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
        Event lEvent = events.get(id);
        lEvent.setName(name);
        lEvent.setTime(time);
        lEvent.setLocation(location);
        lEvent.setNote(note);
        saveEvents();
    }

    public void deleteEvent(Long id) {
        events.remove(id);
        saveEvents();
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
            System.out.println("loaded: " + lEvents);
        } catch (IOException ignore) {
        }
    }

    public void saveEvents() {
        try {
            Path lDatabase = Paths.get(DATABASE_LOCATION, DATABASE_FILENAME);
            JSONArray lEvents = new JSONArray(events.values().stream().map(event -> event.getJson()).collect(Collectors.toList()));
            Files.write(lDatabase, lEvents.toString(2).getBytes(StandardCharsets.UTF_8));
            System.out.println("saved: " + lEvents);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
