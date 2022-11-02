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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * adds functinality to login page
 */
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


    /**login checks if username password correct and sends to appointment screen
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML void onActionLogin(ActionEvent event) throws SQLException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language/Nat");
        String filename = "login_activity.txt",item;

        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        Instant now = Instant.now();

        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if(DBuser.checkLogin(username, password)){

            System.out.println(DBuser.getLoggedOnUser().getID());
            DBappointment.getAppointmentByMonth();
            DBappointment.getAppointmentSoon();
            outputFile.println("Username: " + username + " , Password: " + password + " "+ now + " Successful");
            outputFile.close();
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("loginErrorLabel"));

            alert.show();
            outputFile.println("Username: " + username + " , Password: " + password + " "+ now + " Unsuccessful");
            outputFile.close();
        }
    }


    /**sets the text labels based on language
     *
     * @param url
     * @param resourceBundle
     */
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

