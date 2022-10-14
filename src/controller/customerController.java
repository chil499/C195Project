package controller;

import dao.DBappointment;
import dao.DBcustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerController implements Initializable {

    @FXML private TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer, String> customerAddressCol;
    @FXML private TableColumn<Customer, String> customerCountryCol;
    @FXML private TableColumn<Customer, Integer> customerIDCol;
    @FXML private TableColumn<Customer, String> customerNameCol;
    @FXML private TableColumn<Customer, Integer> customerPhoneCol;
    @FXML private TableColumn<Customer, Integer> customerPostalCol;
    @FXML private TableColumn<Customer, String> customerStateCol;


@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
        DBcustomer.getCustomerObjects();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    customerTableView.setItems(Customer.getAllCustomers());
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerStateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

    }

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAppointment(ActionEvent event) throws IOException, SQLException {

        DBappointment.getAppointmentByMonth();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        Customer selected = customerTableView.getSelectionModel().getSelectedItem();
        if (selected == null) { return; }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"This will delete the customer from the database, do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"Customer " + DBcustomer.select("Customer_Name", selected.getID()) + " has been deleted along with all their appointments.");
            alert2.show();
            DBcustomer.deleteCustomerAppointments(selected.getID());
            customerTableView.getItems().remove(selected);
            Customer.deleteCustomer(selected);
            DBcustomer.delete(selected.getID());

        }
    }

    @FXML
    void onActionReport(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateCustomer.fxml"));
        loader.load();

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();


        Customer selected = customerTableView.getSelectionModel().getSelectedItem();
        updateCustomerController controller = loader.getController();
        controller.setCustomer(customerTableView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }



}

