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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

//initalizes text fields and date pickers and combo box
public class updateAppointmentController implements Initializable {
    @FXML
    private TextField appointmentIDTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField endDateTextField;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private Button saveButton;
    @FXML
    private TextField startDateTextField;
    @FXML
    private TextField startTimeTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField getEndDateTextField;
    @FXML
    private TextField userIDTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private TextField customerIDTextField;
    @FXML
    private ComboBox<LocalTime> startTimeComboBox;
    private int appointmentID;
    private static String localZone = ZoneId.systemDefault().getId();

    Stage stage;
    Parent scene;

    @FXML
    //sends to appointment page
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //selects start time from combobox, makes end time one hour ahead
    @FXML
    void onActionSelectStart(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String start = startTimeComboBox.getSelectionModel().getSelectedItem().toString();
        String startTime = LocalTime.parse(start, formatter).plusHours(1).toString();
        endTimeTextField.setText(startTime);
    }

    //selects start date from date picker, makes end date the same date
    @FXML
    void onActionSelectDate(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        endDateTextField.setText(startDatePicker.getValue().format(formatter));
    }

    //updates the appointment checking for overlaps
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String start = startDatePicker.getValue().toString() + " " + startTimeComboBox.getSelectionModel().getSelectedItem().toString() + ":00";

            int ID = appointmentID;
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            String location = locationTextField.getText();
            String type = typeTextField.getText();
            int userID = Integer.parseInt(userIDTextField.getText());
            int customerID = Integer.parseInt(customerIDTextField.getText());
            int contactID = contactComboBox.getSelectionModel().getSelectedItem().getID();
            String createdBy = DBappointment.select("Created_By", ID);
            String lastUpdatedBy = DBuser.getLoggedOnUser().getName();
            Timestamp lastUpdateDate = new Timestamp(System.currentTimeMillis());
            ;
            Timestamp createdDate = DBappointment.selectTimestamp("Create_Date", ID);
            Timestamp startDB = Timestamp.valueOf(LocalDateTime.parse(start, formatter));
            Timestamp endDB = Timestamp.valueOf(LocalDateTime.parse(start, formatter).plusHours(1));
            boolean isWithinBusinessHours = DBappointment.checkBusinessHour(start, localZone);
            boolean isOverlap = false;

            if (isWithinBusinessHours) {
                isOverlap = DBappointment.checkOverLapUpdate(start);
                if (isOverlap) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is appointment scheduled for this time already");
                    alert.show();
                } else {
                    DBappointment.update(title, description, location, type, startDB, endDB, createdDate, createdBy, lastUpdateDate, lastUpdatedBy, customerID, userID, contactID, appointmentID);
                    DBappointment.getAppointmentByMonth();
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please make sure all fields are filled out correctly");
            alert.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please make sure all fields are filled out correctly");
            alert.show();
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please sure that customer or user exists");
            alert.show();
        }



    }
    //sets the appointment when one is selected to be updated
    public void setAppointment(Appointment selectedAppointment){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterEndDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalTime startTime = LocalTime.parse(selectedAppointment.getStart(),formatterDate);
        LocalDate startDate = LocalDate.parse(selectedAppointment.getStart(),formatterDate);
        LocalTime endTime = LocalTime.parse(selectedAppointment.getStart(),formatterDate);
        LocalDate endDate = LocalDate.parse(selectedAppointment.getEnd(),formatterDate);

        appointmentID = selectedAppointment.getId();
        appointmentIDTextField.setText(String.valueOf(selectedAppointment.getId()));
        titleTextField.setText(String.valueOf(selectedAppointment.getTitle()));
        locationTextField.setText(String.valueOf(selectedAppointment.getLocation()));
        descriptionTextField.setText(String.valueOf(selectedAppointment.getDescription()));
        contactComboBox.getSelectionModel().select(selectedAppointment.getContactID()-1);
        typeTextField.setText(String.valueOf(selectedAppointment.getType()));
        startDatePicker.setValue(startDate);
        endTimeTextField.setText(String.format(endTime.toString(), formatterTime));
        endDateTextField.setText(endDate.format(formatterEndDate));
        startTimeComboBox.setValue(startTime);
        customerIDTextField.setText(String.valueOf(selectedAppointment.getCustomerID()));
        userIDTextField.setText(String.valueOf(selectedAppointment.getUserID()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(Contact.getAllContacts());
        LocalTime start = LocalTime.of(0,0);
        LocalTime end = LocalTime.of(23,0);
        while(start.isBefore(end.minusSeconds(1))){
            startTimeComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }
}
