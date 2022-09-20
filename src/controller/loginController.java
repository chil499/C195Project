package controller;

import dao.DBuser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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


    @FXML void onActionLogin(ActionEvent event) throws SQLException {
        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if(DBuser.checkLogin(username,password)==true){
            System.out.println("True");
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

