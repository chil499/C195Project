package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class appointmentController {

    @FXML private Button addAppointment;

    @FXML private Button customerButton;

    @FXML private Button deleteAppointment;

    @FXML private RadioButton monthRadio;

    @FXML private Button reportButton;

    @FXML private Button updateAppointment;

    @FXML private ToggleGroup weekOrMonth;

    @FXML private RadioButton weekRadio;
    Stage stage;
    Parent scene;

    @FXML void onActionAddApointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML void onActionCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML void onActionMonthSelected(ActionEvent event) {

    }

    @FXML void onActionReport(ActionEvent event) throws IOException {

    }

    @FXML void onActionUpdateAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/updateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML void onActionWeekSelected(ActionEvent event) {

    }

    @FXML void onActiondDeleteApoointment(ActionEvent event) {

    }

}

