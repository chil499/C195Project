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
import model.Contact;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * adds functionality to appointment controll page
 */
public class addAppointmentController implements Initializable {

    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField endDateTextField;
    @FXML private TextField endTimeTextField;
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
    /**setting all the combo boxes
     *
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(Contact.getAllContacts());
        LocalTime start = LocalTime.of(0,0);
        LocalTime end = LocalTime.of(23,0);
        while(start.isBefore(end.minusSeconds(1))){
            startTimeComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }

    /**sends back to appointment page
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**sets end time on hour ahead of start time
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionSelectStart(ActionEvent event) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String start = startTimeComboBox.getSelectionModel().getSelectedItem().toString() ;
        String startTime = LocalTime.parse(start,formatter).plusHours(1).toString();
        endTimeTextField.setText(startTime);
    }

    /**sets end date to same day as start date
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionSelectDate(ActionEvent event) throws IOException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        endDateTextField.setText(startDatePicker.getValue().format(formatter));
    }

    /**saves the appointment and sends back to appointment screen
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException, SQLException {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = startDatePicker.getValue().toString() + " " + startTimeComboBox.getSelectionModel().getSelectedItem().toString() + ":00";
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int userID = Integer.parseInt(userIDTextField.getText());
        int customerID = Integer.parseInt(customerIDTextField.getText());
        int contactID = contactComboBox.getSelectionModel().getSelectedItem().getID();
        String createdBy = DBuser.getLoggedOnUser().getName();
        String lastUpdatedBy = DBuser.getLoggedOnUser().getName();
        Timestamp lastUpdateDate = new Timestamp(System.currentTimeMillis());
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp startDB = Timestamp.valueOf(LocalDateTime.parse(start, formatter));
        Timestamp endDB = Timestamp.valueOf(LocalDateTime.parse(start, formatter).plusHours(1));

        boolean isWithinBusinessHours = DBappointment.checkBusinessHour(start, localZone);
        boolean isOverlap = false;
        if (isWithinBusinessHours) {
            isOverlap = DBappointment.checkOverLap(start);
            if (isOverlap) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is appointment scheduled for this time already");
                alert.show();
            } else {
                DBappointment.insert(title, description, location, type, startDB, endDB, createdDate, createdBy, lastUpdateDate, lastUpdatedBy, customerID, userID, contactID);
                DBappointment.getAppointmentByMonth();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }catch(NumberFormatException e){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Please make sure all fields are filled out correctly");
        alert.show();
    }catch(NullPointerException e){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Please make sure all fields are filled out correctly");
        alert.show();
    }catch(SQLIntegrityConstraintViolationException e){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Please sure a that customer or user exists");
        alert.show();
    }
    }
}

