package controller;

import dao.DBuser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML private Label languageID;
    @FXML private Button logingID;
    @FXML private Label passwordID;
    @FXML private TextField passwordTextArea;
    @FXML private Label usernameID;
    @FXML private TextField usernameTextArea;
    @FXML private Label zoneID;

    User currentUser = new User(0,"default");
    Stage stage;
    Parent scene;


    //login checks if username password correct and sends to appointment screen
    @FXML void onActionLogin(ActionEvent event) throws SQLException, IOException {

        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if(DBuser.checkLogin(username,password)==true){
            currentUser.setName(username);
            currentUser.setID(7);
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId localZoneId = ZoneId.systemDefault();
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();


        zoneID.setText(String.valueOf(localTime.withNano(0)));

    }
}

