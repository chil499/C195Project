package Main;

import dao.DBcountry;
import dao.DBconnection;
import dao.DBcustomer;
import dao.DBfirstLevelDivision;
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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        DBconnection.openConnection();
        DBcustomer.addCustomerObjects();
        DBcountry.addCountryObjects();
        DBfirstLevelDivision.addfirstLevelDivsionObjects();
        launch(args);
        DBconnection.closeConnection();

    }
}
