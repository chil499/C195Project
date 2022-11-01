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
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class appointmentController implements Initializable {

//initializes all the tables
    @FXML private TableView<Appointment> appointmentTableView;
    @FXML private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointment, String> titleCol;
    @FXML private TableColumn<Appointment, String> descCol;
    @FXML private TableColumn<Appointment, String> locCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML private TableColumn<Appointment, Integer> custIDCol;
    @FXML private TableColumn<Appointment, Integer> userIDCol;
    @FXML private TableColumn<Appointment, Integer> contactIDCol;


    Stage stage;
    Parent scene;

    @Override
    //sets values to all the tables
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DBappointment.getAppointmentByMonth();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        appointmentTableView.setItems(Appointment.getAllAppointments());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }
    //sends to add appointment screen
    @FXML void onActionAddApointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
//sends to customer screen
    @FXML void onActionCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }
//shows reports my month
    @FXML void onActionMonthSelected(ActionEvent event) throws SQLException {
        Appointment.getAllAppointments().clear();
        DBappointment.getAppointmentByMonth();
    }

    @FXML void onActionAllSelected(ActionEvent event) throws SQLException {
        Appointment.getAllAppointments().clear();
        DBappointment.getAppointmentAll();
    }

    //sensds to report screen
    @FXML void onActionReport(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    //selects appointment and loads the update page
    @FXML void onActionUpdateAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateAppointment.fxml"));
        loader.load();

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();


        Appointment selected = appointmentTableView.getSelectionModel().getSelectedItem();
        if (selected == null) { return; }
        updateAppointmentController controller = loader.getController();
        controller.setAppointment(appointmentTableView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();


    }
//shows the appointments in the upcoming week
    @FXML void onActionWeekSelected(ActionEvent event) throws SQLException {
        Appointment.getAllAppointments().clear();
        DBappointment.getAppointmentByWeek();

    }

    //deletes a selected appointment
    @FXML void onActiondDeleteApoointment(ActionEvent event) throws SQLException {
        Appointment selected = appointmentTableView.getSelectionModel().getSelectedItem();
        if (selected == null) { return; }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"This will delete the Appointment from the database, do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){

            appointmentTableView.getItems().remove(selected);
            DBappointment.delete(selected.getId());
        }
    }

}

