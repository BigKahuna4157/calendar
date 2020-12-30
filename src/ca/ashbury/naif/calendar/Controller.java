package ca.ashbury.naif.calendar;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    private Calendar calendar;

    @FXML
    TableView tvEvents;

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

    @FXML
    private void createEvent(ActionEvent event) {
        event.consume();
        calendar.createEvent(tfName.getText(), LocalDateTime.parse(tfTime.getText().replace(" ", "T")), taLocation.getText(), taNote.getText());
    }

    @FXML
    private void loadEvents(ActionEvent event) {
        event.consume();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        address.setCellValueFactory(new PropertyValueFactory<>("location"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        for (Event lEvent : calendar.getEvents()) {
            tvEvents.getItems().add(lEvent);
        }
    }

}
