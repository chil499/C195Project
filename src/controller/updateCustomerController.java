package controller;

import dao.DBcountry;
import dao.DBcustomer;
import dao.DBfirstLevelDivision;
import dao.DBuser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class updateCustomerController {
//initalize text fields and combobox
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
    private int customerID;

    @FXML
    //sends back to customer page
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    //when country is selected from combo box, pulls up that countrys states in next combobox
    @FXML
    void onActionCountrySelect(ActionEvent event){
        Country selected =  countryComboBox.getSelectionModel().getSelectedItem();
        int countryID = selected.getID();
        ObservableList matchedStates = FirstLevelDivision.matchCountryId(countryID);
        stateComboBox.setItems(matchedStates);

    }
    //sets the selected customer when updating
    public void setCustomer(Customer selectedCustomer){

        customerID = Customer.getAllCustomers().indexOf(selectedCustomer);
        countryComboBox.setItems(Country.getAllCountries());
        stateComboBox.setItems(FirstLevelDivision.getAllStates());
        customerID = Customer.getAllCustomers().indexOf(selectedCustomer);
        customerIDTextField.setText(String.valueOf(selectedCustomer.getID()));
        nameTextField.setText(String.valueOf(selectedCustomer.getName()));
        phoneTextField.setText(String.valueOf(selectedCustomer.getPhone()));
        postalTextField.setText(String.valueOf(selectedCustomer.getZip()));
        addressTextField.setText(String.valueOf(selectedCustomer.getAddress()));
        for(Country p :countryComboBox.getItems()){
            if(selectedCustomer.getCountry().equals(p.getName())){
                countryComboBox.setValue(p);

            }
        }
        for(FirstLevelDivision p :stateComboBox.getItems()){

            if(selectedCustomer.getState().equals(p.getName())){
                stateComboBox.setValue(p);
            }
        }

    }
    //updates the selected customer with new values
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            FirstLevelDivision stateSelected = stateComboBox.getSelectionModel().getSelectedItem();
            int ID = Integer.parseInt(customerIDTextField.getText());
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String zip = postalTextField.getText();
            String phone = phoneTextField.getText();
            Timestamp createDate = new Timestamp(System.currentTimeMillis());
            String createBy = DBuser.getLoggedOnUser().getName();
            Timestamp lastCreateDate = DBcustomer.selectTimestamp("Last_Update", ID);
            String lastCreateBy = DBcustomer.select("Last_Updated_By", ID);
            int divisionID = stateSelected.getDivisionID();
            String country = DBcountry.returnCountry(DBcountry.returnCountryID(divisionID));
            String state = DBfirstLevelDivision.returnState(divisionID);
            DBcustomer.update(name, address, zip, phone, createDate, createBy, lastCreateDate, lastCreateBy, divisionID, ID);

            Customer temp = new Customer(ID, name, address, zip, phone, divisionID, country, state);
            Customer.updateCustomer(customerID, temp);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please make sure all fields are filled out correctly");
            alert.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please make sure all fields are filled out correctly");
            alert.show();
        }
    }
}

