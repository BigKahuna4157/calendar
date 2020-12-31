package ca.ashbury.naif.calendar;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
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
    TextArea taNotify;

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
        calendar = Calendar.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEvents();
        time.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        note.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void loadEvents() {
        tvEvents.getItems().clear();
        for (Event lEvent : calendar.getEvents()) {
            tvEvents.getItems().add(lEvent);
        }
    }

    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        calendar.createEvent(tfName.getText(), LocalDateTime.parse(tfTime.getText(), formatter), taLocation.getText(), taNote.getText());
        loadEvents();
    }

    @FXML
    private void modifyEvent(ActionEvent event) {
        event.consume();
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        event.consume();
        Event levent = tvEvents.getSelectionModel().getSelectedItem();
        calendar.deleteEvent(levent.getId());
        loadEvents();
    }

}
