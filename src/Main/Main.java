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
import model.Country;
import model.User;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        primaryStage.setTitle("Appointment");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        DBconnection.openConnection();
        DBcountry.addCountryObjects();
        DBfirstLevelDivision.addfirstLevelDivsionObjects();
        DBcustomer.addCustomerObjects();
        launch(args);
        DBconnection.closeConnection();

    }
}
