package controller;


import dao.DBcountry;
import dao.DBcustomer;
import dao.DBfirstLevelDivision;
import dao.DBuser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static model.Customer.getAllCustomers;

public class addCustomerController implements Initializable {

    //initalize textfelds and combo boxes
    @FXML private TextField addressTextField;
    @FXML private ComboBox<Country> countryComboBox;
    @FXML private TextField nameTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField postalTextField;
    @FXML private ComboBox<FirstLevelDivision> stateComboBox;

    Stage stage;
    Parent scene;
    ObservableList<FirstLevelDivision> states = FXCollections.observableArrayList();

    //sends back to customer page
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //savces customer and sends back to customer page
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            FirstLevelDivision stateSelected = stateComboBox.getSelectionModel().getSelectedItem();
            int ID = getNewID();
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String zip = postalTextField.getText();
            String phone = phoneTextField.getText();
            Timestamp createDate = new Timestamp(System.currentTimeMillis());
            String createBy = DBuser.getLoggedOnUser().getName();
            Timestamp lastCreateDate = new Timestamp(System.currentTimeMillis());
            String lastCreateBy = DBuser.getLoggedOnUser().getName();;
            int divisionID = stateSelected.getDivisionID();
            String country = DBcountry.returnCountry(DBcountry.returnCountryID(divisionID));
            String state = DBfirstLevelDivision.returnState(divisionID);
            DBcustomer.insert(ID, name, address, zip, phone, createDate, createBy, lastCreateDate, lastCreateBy, divisionID);

            Customer.addCustomer(new Customer(ID, name, address, zip, phone, divisionID, country, state));


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please make sure all fields are filled out correctly");
            alert.show();
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please make sure all fields are filled out correctly");
            alert.show();
        }
    }

    //when country is selected, sets the state combo box to that countries states
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

    //sets the country combobox
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(Country.getAllCountries());
    }
}
