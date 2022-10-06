package controller;

import dao.DBappointment;
import dao.DBuser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;

import static model.Appointment.getAllAppointments;

public class addAppointmentController implements Initializable {

    @FXML private TextField appointmentIDTextField;
    @FXML private Button cancelButton;
    @FXML private TextField contactTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private DatePicker endDatePicker;
    @FXML private TextField endTimeTextField;
    @FXML private Button saveButton;
    @FXML public DatePicker startDatePicker;
    @FXML public ComboBox<LocalTime> startTimeComboBox;
    @FXML private TextField titleTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField userIDTextField;
    @FXML private TextField customerIDTextField;
    @FXML private ComboBox<Contact> contactComboBox;
    private static String localZone = ZoneId.systemDefault().getId();


    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(Contact.getAllContacts());
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(22,0);
        while(start.isBefore(end.minusSeconds(1))){
            start = start.plusHours(1);
            startTimeComboBox.getItems().add(start);

        }




    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException, SQLException {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String start = startDatePicker.getValue().toString() + " "+ startTimeComboBox.getSelectionModel().getSelectedItem().toString()+ ":00";

        int ID = DBappointment.getNewID();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int userID = Integer.parseInt(userIDTextField.getText());
        int customerID = Integer.parseInt(customerIDTextField.getText());
        int contactID = contactComboBox.getSelectionModel().getSelectedItem().getID();
        String createdBy = DBuser.getLoggedOnUser().getName();
        String lastUpdatedBy = DBuser.getLoggedOnUser().getName();
        Timestamp lastUpdateDate = new Timestamp(System.currentTimeMillis()); ;
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp startDB = Timestamp.valueOf(LocalDateTime.parse(start,formatter));
        Timestamp endDB= Timestamp.valueOf(LocalDateTime.parse(start,formatter).plusHours(1));




        if(DBappointment.checkBusinessHour(start,localZone)){
            DBappointment.insert(ID,title,description,location,type,startDB,endDB,createdDate,createdBy,lastUpdateDate,lastUpdatedBy,customerID,userID,contactID);
            DBappointment.getAppointmentByMonth();
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }





    }


}

