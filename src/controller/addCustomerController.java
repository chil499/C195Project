package controller;

import dao.DBfirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {

    @FXML private TextField addressTextField;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Country> countryComboBox;
    @FXML private TextField customerIDTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField postalTextField;
    @FXML private Button saveButton;
    @FXML private ComboBox<FirstLevelDivision> stateComboBox;

    Stage stage;
    Parent scene;
    ObservableList<FirstLevelDivision> states = FXCollections.observableArrayList();

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
    @FXML
    void onActionCountrySelect(ActionEvent event){
        Country selected =  countryComboBox.getSelectionModel().getSelectedItem();
        int countryID = selected.getID();
        ObservableList matchedStates = FirstLevelDivision.matchCountryId(countryID);
        stateComboBox.setItems(matchedStates);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(Country.getAllCountries());


    }
}
