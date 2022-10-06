package controller;


import dao.DBcountry;
import dao.DBcustomer;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static model.Customer.getAllCustomers;

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
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FirstLevelDivision stateSelected = stateComboBox.getSelectionModel().getSelectedItem();
        int ID = getNewID();
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String zip = postalTextField.getText();
        String phone = phoneTextField.getText();
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        String createBy = "admin";
        Timestamp lastCreateDate = new Timestamp(System.currentTimeMillis());
        String lastCreateBy = "admin";
        int divisionID = stateSelected.getDivisionID();
        String country = DBcountry.returnCountry(DBcountry.returnCountryID(divisionID));
        String state = DBfirstLevelDivision.returnState(divisionID);
        DBcustomer.insert(ID,name,address,zip,phone,createDate,createBy,lastCreateDate,lastCreateBy,divisionID);

        Customer.addCustomer(new Customer(ID,name,address,zip,phone,divisionID,country,state));


        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionCountrySelect(ActionEvent event){
        Country selected =  countryComboBox.getSelectionModel().getSelectedItem();
        int countryID = selected.getID();
        ObservableList matchedStates = FirstLevelDivision.matchCountryId(countryID);
        stateComboBox.setItems(matchedStates);

    }
    public static int getNewID(){
        int newID = 1;
        for(int i =0; i < getAllCustomers().size();i++){
            newID++;
        }
        return newID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(Country.getAllCountries());

    }

}
