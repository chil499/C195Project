package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class customerController {

    @FXML private Button addCustomer;
    @FXML private Button appointmentButton;
    @FXML private TableColumn<?, ?> customerAddressCol;
    @FXML private TableColumn<?, ?> customerCountryCol;
    @FXML private TableColumn<?, ?> customerIDCol;
    @FXML private TableColumn<?, ?> customerNameCol;
    @FXML private TableColumn<?, ?> customerPhoneCol;
    @FXML private TableColumn<?, ?> customerPostalCol;
    @FXML private TableColumn<?, ?> customerStateCol;
    @FXML private Button deleteCustomer;
    @FXML private Button reportButton;
    @FXML private Button updateCustomer;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCustomerTable();

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
    void onActionAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionReport(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/updateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    private static void fillCustomerTable(){
        String sql = ""
    }


}

