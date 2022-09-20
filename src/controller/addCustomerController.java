package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addCustomerController {

    @FXML private TextField addressTextField;
    @FXML private Button cancelButton;
    @FXML private ComboBox<?> countryComboBox;
    @FXML private TextField customerIDTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField postalTextField;
    @FXML private Button saveButton;
    @FXML private ComboBox<?> stateComboBox;

    Stage stage;
    Parent scene;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

}
