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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class loginController implements Initializable {

    @FXML private Label languageID;
    @FXML private Button logingID;
    @FXML private Label passwordID;
    @FXML private TextField passwordTextArea;
    @FXML private Label usernameID;
    @FXML private TextField usernameTextArea;
    @FXML private Label zoneID;

    Stage stage;
    Parent scene;



    //login checks if username password correct and sends to appointment screen
    @FXML void onActionLogin(ActionEvent event) throws SQLException, IOException {
        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if(DBuser.checkLogin(username,password)==true){
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

