package controller;

import dao.DBappointment;
import dao.DBuser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;

import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML private Label languageLabel;
    @FXML private Button loginLabel;
    @FXML private Label passwordLabel;
    @FXML private TextField passwordTextArea;
    @FXML private Label usernameLabel;
    @FXML private TextField usernameTextArea;
    @FXML private Label zoneID;


    Stage stage;
    Parent scene;


    //login checks if username password correct and sends to appointment screen
    @FXML void onActionLogin(ActionEvent event) throws SQLException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language/Nat");

        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if(DBuser.checkLogin(username,password)==true){

            System.out.println(DBuser.getLoggedOnUser().getID());
            DBappointment.getAppointmentByMonth();
            DBappointment.getAppointmentSoon();
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("loginErrorLabel"));
            alert.show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId localZoneId = ZoneId.systemDefault();

        resourceBundle = ResourceBundle.getBundle("language/Nat");
        usernameLabel.setText(resourceBundle.getString("usernameLabel"));
        passwordLabel.setText(resourceBundle.getString("passwordLabel"));
        loginLabel.setText(resourceBundle.getString("loginLabel"));
        languageLabel.setText(resourceBundle.getString("languageLabel"));
        zoneID.setText(String.valueOf(localZoneId));

    }
}

