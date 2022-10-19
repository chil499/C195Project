package Main;

import dao.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.sql.SQLException;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        primaryStage.setTitle("Appointment Scheduling Application");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        DBconnection.openConnection();
        DBcountry.addCountryObjects();
        DBfirstLevelDivision.addfirstLevelDivsionObjects();
        DBcontact.addContactObjects();
        DBcustomer.getCustomerObjects();

        launch(args);
        DBconnection.closeConnection();

    }
}
