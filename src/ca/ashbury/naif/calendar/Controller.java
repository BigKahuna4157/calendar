package ca.ashbury.naif.calendar;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

public class Controller implements Initializable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Calendar calendar;

    @FXML
    TableView<Event> tvEvents;

    @FXML
    TextField tfTime;

    @FXML
    TextField tfName;

    @FXML
    TextArea taLocation;

    @FXML
    TextArea taNote;

    @FXML
    Label laNotify;

    @FXML
    TableColumn id;

    @FXML
    TableColumn name;

    @FXML
    TableColumn time;

    @FXML
    TableColumn address;

    @FXML
    TableColumn note;

    public Controller() {
        this.calendar = Calendar.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEvents();
        time.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter(formatter, formatter)));
        name.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        address.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        note.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        calendar.setNotify(laNotify);
    }

    private void loadEvents() {
        tvEvents.getItems().clear();
        for (Event lEvent : calendar.getEvents()) {
            tvEvents.getItems().add(lEvent);
        }
    }

    private void modifyEvent(Event event) {
        calendar.modifyEvent(event.getId(), event.getName(), event.getTime(), event.getLocation(), event.getNote());
    }

    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        calendar.createEvent(tfName.getText(), LocalDateTime.parse(tfTime.getText(), formatter), taLocation.getText(), taNote.getText());
        loadEvents();
    }

    @FXML
    private void modifyName(TableColumn.CellEditEvent<Event, String> event) {
        event.consume();
        tvEvents.getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
        Event lEvent = tvEvents.getSelectionModel().getSelectedItem();
        modifyEvent(lEvent);
    }

    @FXML
    private void modifyTime(TableColumn.CellEditEvent<Event, LocalDateTime> event) {
        event.consume();
        tvEvents.getItems().get(event.getTablePosition().getRow()).setTime(event.getNewValue());
        Event lEvent = tvEvents.getSelectionModel().getSelectedItem();
        modifyEvent(lEvent);
    }

    @FXML
    private void modifyLocation(TableColumn.CellEditEvent<Event, String> event) {
        event.consume();
        tvEvents.getItems().get(event.getTablePosition().getRow()).setLocation(event.getNewValue());
        Event lEvent = tvEvents.getSelectionModel().getSelectedItem();
        modifyEvent(lEvent);
    }

    @FXML
    private void modifyNote(TableColumn.CellEditEvent<Event, String> event) {
        event.consume();
        tvEvents.getItems().get(event.getTablePosition().getRow()).setNote(event.getNewValue());
        Event lEvent = tvEvents.getSelectionModel().getSelectedItem();
        modifyEvent(lEvent);
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        event.consume();
        Event lEvent = tvEvents.getSelectionModel().getSelectedItem();
        calendar.deleteEvent(lEvent.getId());
        tvEvents.getItems().removeAll(tvEvents.getSelectionModel().getSelectedItems());
    }

}
