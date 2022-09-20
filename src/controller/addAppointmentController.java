package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addAppointmentController {

    @FXML private TextField appointmentIDTextField;
    @FXML private Button cancelButton;
    @FXML private TextField contactTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField endDateTextField;
    @FXML private TextField endTimeTextField;
    @FXML private Button saveButton;
    @FXML private TextField startDateTextField;
    @FXML private TextField startTimeTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField userIDTextField;

    Stage stage;
    Parent scene;
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

}

