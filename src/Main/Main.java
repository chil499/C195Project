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
    public int currentUser = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        primaryStage.setTitle("Appointment");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        DBconnection.openConnection();
        DBcountry.addCountryObjects();
        DBfirstLevelDivision.addfirstLevelDivsionObjects();
        DBcustomer.addCustomerObjects();
        //DBcustomer.insert(5,"Connor","9 dune court", "11768", "631-697-0730", ZonedDateTime.now(ZoneOffset.UTC).format(formatter).toString(),"connor",ZonedDateTime.now(ZoneOffset.UTC).format(formatter).toString() ,"Connor", 2);
        launch(args);
        DBconnection.closeConnection();

    }
}
