package ca.ashbury.naif.calendar;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

public class Event {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TIME = "time";
    private static final String LOCATION = "location";
    private static final String NOTE = "note";

    private long id;
    private String name;
    private LocalDateTime time;
    private String location;
    private String note;
    private JSONObject json;
    private Notifier notifier;
    private Timer timer;

    public Event(long id, String name, LocalDateTime time, String location, String note) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
        this.note = note;
        this.timer = new Timer();
        this.notifier = new Notifier(this);
        timer.schedule(notifier, Date.from(time.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public Event(String name, LocalDateTime time, String location, String note) {
        this(System.currentTimeMillis(), name, time, location, note);
    }

    public Event(JSONObject json) {
        this(json.getLong(ID), json.getString(NAME), LocalDateTime.parse(json.getString(TIME), DATE_TIME_FORMATTER), json.getString(LOCATION), json.getString(NOTE));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public JSONObject getJson() {
        if (json == null) {
            json = new JSONObject();
        }
        json.put(ID, id);
        json.put(NAME, name);
        json.put(TIME, time.format(DATE_TIME_FORMATTER));
        json.put(LOCATION, location);
        json.put(NOTE, note);
        return json;
    }

}
