package ca.ashbury.naif.calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

}
