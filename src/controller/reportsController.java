
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

public class reportsController implements Initializable {
    @FXML private ComboBox<Customer> customerComboBox;
    @FXML private TableView<Appointment> reportTableView;
    @FXML private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointment, String> titleCol;
    @FXML private TableColumn<Appointment, String> descriptionCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML private TableColumn<Appointment, Integer> customerIDCol;


    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerComboBox.setItems(Customer.getAllCustomers());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

    }

    @FXML void onActionCustomerReportSelect(ActionEvent event) throws IOException, SQLException {
        DBappointment.getAppointmentByCustomer(customerComboBox.getSelectionModel().getSelectedItem().getID());
        reportTableView.setItems(Appointment.getAllAppointments());


    }

    @FXML void onActionAppointmentSelect(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    @FXML void onActionCustomerSelect(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @FXML
    void onActionCustomerHourSelect(ActionEvent event) {

    }



    @FXML
    void onActionQuit(ActionEvent event) {

    }

    @FXML
    void onActionRunReport(ActionEvent event) {

    }


}

