
package controller;

        import dao.DBappointment;
        import dao.DBcustomer;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableArray;
        import javafx.collections.ObservableList;
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
        import java.text.ParseException;
        import java.time.LocalDateTime;
        import java.util.ResourceBundle;

public class reportsController implements Initializable {
    @FXML private ComboBox<Customer> customerComboBox;
    @FXML private ComboBox<Customer> customerMinutesComboBox;
    @FXML private ComboBox<String> monthComboBox;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TableView<Appointment> reportTableView;
    @FXML private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointment, String> titleCol;
    @FXML private TableColumn<Appointment, String> descriptionCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML private TableColumn<Appointment, Integer> customerIDCol;
    @FXML private TextField totalNumberTextField;
    @FXML private TextField totalMinutesTextField;
    @FXML private Button exitButton;


    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //LAMDA EXPRESSION FOR EXITING THE PROGRAM
        exitButton.setOnAction(e ->System.exit(0));

        ObservableList<String> months = FXCollections.observableArrayList();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        for (String value : months) {
            monthComboBox.setItems(months);
        }
        ObservableList<String> types = FXCollections.observableArrayList();
        try {
            types = DBappointment.selectType();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (String value : types) {
            typeComboBox.setItems(types);
        }

        customerComboBox.setItems(Customer.getAllCustomers());
        customerMinutesComboBox.setItems(Customer.getAllCustomers());
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
    void onActionCustomerHourSelect(ActionEvent event) throws SQLException, ParseException {
           totalMinutesTextField.setText(String.valueOf(DBappointment.getAppointmentByCustomerTimes(customerMinutesComboBox.getSelectionModel().getSelectedItem().getID())));
    }


    @FXML
    void onActionRunReport(ActionEvent event) throws SQLException {
        if (monthComboBox.getSelectionModel().getSelectedItem() == null || typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select both a month and a type");
            alert.show();
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="January"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("January", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="February"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("February", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="March"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("March", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="April"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("April", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="May"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("May", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="June"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("June", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="July"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("July", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="August"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("August", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="September"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("September", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="October"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("October", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="November"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("November", typeComboBox.getSelectionModel().getSelectedItem())));
        }else if(monthComboBox.getSelectionModel().getSelectedItem()=="December"){
            totalNumberTextField.setText(String.valueOf(DBappointment.getAppointmentTypeMonth("December", typeComboBox.getSelectionModel().getSelectedItem())));
        }

    }


}

